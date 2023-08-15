import client_care_provider.Administrator;
import client_care_provider.Client;
import clinic_interface.Clinic;
import clinic_staff.DoctorTitle;
import clinic_staff.EmployeeManagerService;
import clinic_staff.util.AdministratorBuilder;
import clinic_staff.util.DoctorBuilder;
import clinic_staff.util.DoctorDirector;
import repository.BasicMedicalTest;
import repository.MedicalTest;
import repository.MedicalTestPackage;
import repository.RepositoryService;
import schedule.Appointment;

import java.util.*;

public class App {
        public static void main(String[] args) {
                Client client = new Client("Bob", 1, "Sick");
                Administrator administrator = initializeClinic(new Clinic("Clinic 1"));

                MedicalTest checkUpTest = client.recieveCheckUpTest(administrator);
                ArrayList<Appointment> appointments = client.payForMedicalTest(administrator, checkUpTest);
                for (Appointment appointment : appointments) {
                        client.visitAppointment(appointment);
                }
                System.out.print(client.getCertificate(administrator, checkUpTest).getCertificateInfo());
        }

        private static Administrator initializeClinic(Clinic clinic) {
                Administrator administrator = ((AdministratorBuilder) new AdministratorBuilder()
                        .setName("Admin name")
                        .setClinic(clinic)).build();
                EmployeeManagerService.addEmployee(administrator);

                DoctorBuilder doctorBuilder = new DoctorBuilder();
                doctorBuilder.setClinic(clinic)
                        .setName("Practitioner");
                DoctorDirector.constructPractitioner(doctorBuilder);
                EmployeeManagerService.addEmployee(doctorBuilder.build());

                doctorBuilder.setClinic(clinic)
                        .setName("Pathologist");
                DoctorDirector.constructPathologist(doctorBuilder);
                EmployeeManagerService.addEmployee(doctorBuilder.build());

                BasicMedicalTest bloodCheckTest = new BasicMedicalTest(DoctorTitle.PRACTITIONER,
                        100.F, "Blood check");
                RepositoryService.addMedicalTest(bloodCheckTest);
                BasicMedicalTest pressureCheckTest = new BasicMedicalTest(DoctorTitle.PRACTITIONER,
                        50.F, "Pressure check");
                RepositoryService.addMedicalTest(pressureCheckTest);

                ArrayList<BasicMedicalTest> packageTests = new ArrayList<>(List.of(bloodCheckTest, pressureCheckTest));
                MedicalTestPackage medicalTestPackage = new MedicalTestPackage(DoctorTitle.PATHOLOGIST,
                        125.F, "Check Up", packageTests);
                RepositoryService.addMedicalTest(medicalTestPackage);

                return administrator;
        }
}
