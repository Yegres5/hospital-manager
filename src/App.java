import clinic.Clinic;
import clinic.client_care_provider.*;
import clinic.repository.*;
import clinic.schedule.Appointment;
import clinic.schedule.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {
        public static void main(String[] args) {
                Administrator administrator = initializeClinic();
                Client client = new Client("Bob", "Sick");

                administrator.createPatientCard(client);

                ArrayList<MedicalTest> medicalTests = administrator.getMedicalTests();
                MedicalTest checkUpTest = medicalTests.stream()
                        .filter(medicalTest -> Objects.equals(medicalTest.getName(), "Check up"))
                        .findAny()
                        .orElse(null);

                ArrayList<Appointment> appointments = administrator.processMedicalTestRequest(client, checkUpTest);
                for (Appointment appointment : appointments) {
                        appointment.getDoctor()
                                .provideAppointmentMedicalTest(appointment, client);
                }

                Certificate certificate = administrator.generateCertificate(client, checkUpTest);
                System.out.print(certificate.print());
        }

        private static Administrator initializeClinic() {
                Repository repository = new Repository();
                Scheduler scheduler = new Scheduler();
                EmployeeManagement employeeManagement = new EmployeeManagement();
                Clinic clinic = new Clinic(repository, scheduler, employeeManagement);

                Administrator administrator = new Administrator("Admin name", clinic);
                employeeManagement.insertEmployee(administrator);

                Doctor doctorPractitioner = new Doctor("Doctor Practitioner", clinic, Speciality.PRACTITIONER);
                employeeManagement.insertEmployee(doctorPractitioner);

                Doctor doctorPathologist = new Doctor("Doctor Pathologist", clinic, Speciality.PATHOLOGIST);
                employeeManagement.insertEmployee(doctorPathologist);

                BasicMedicalTest bloodCheck = new BasicMedicalTest(
                        "Blood check",
                        100.F,
                        Speciality.PRACTITIONER
                );
                repository.insertMedicalTest(bloodCheck);

                BasicMedicalTest pressureCheck = new BasicMedicalTest(
                        "Pressure check",
                        50.F,
                        Speciality.PRACTITIONER
                );
                repository.insertMedicalTest(pressureCheck);

                MedicalTestPackage medicalTestPackage = new MedicalTestPackage(
                        "Check up",
                        200.F,
                        Speciality.PATHOLOGIST,
                        new ArrayList<>(
                                List.of(bloodCheck,
                                        pressureCheck
                                )
                        )
                );
                repository.insertMedicalTest(medicalTestPackage);

                return administrator;
        }
}
