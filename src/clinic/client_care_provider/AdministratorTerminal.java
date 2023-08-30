package clinic.client_care_provider;

import clinic.repository.*;
import clinic.repository.util.CertificateBuilder;
import clinic.schedule.Appointment;
import clinic.schedule.Scheduler;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdministratorTerminal {
    private final Repository repository;
    private final Scheduler scheduler;
    private final EmployeeManagement employeeManagement;

    public AdministratorTerminal(Repository repository, Scheduler scheduler, EmployeeManagement employeeManagement) {
        this.repository = repository;
        this.scheduler = scheduler;
        this.employeeManagement = employeeManagement;
    }

    public void registerPatientCard(PatientCard patientCard) {
        repository.insertPatientCard(patientCard);
    }

    public ArrayList<MedicalTest> getMedicalTests() {
        return repository.retrieveMedicalTests();
    }

    public ArrayList<Doctor> getAvailableDoctors(Speciality speciality) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        for (Employee employee : employeeManagement.retrieveEmployees()) {
            if (employee instanceof Doctor doctor && doctor.getSpeciality() == speciality){
                doctors.add(doctor);
            }
        }

        return doctors;
    }

    public Appointment scheduleMedicalTest(PatientCard patientCard, MedicalTest medicalTest, Doctor doctor, String date) {
        Appointment appointment = new Appointment(patientCard, doctor, medicalTest, date);
        scheduler.insertAppointment(appointment);
        return appointment;
    }

    public Certificate getCertificate(PatientCard patientCard, MedicalTest medicalTest) {
        Certificate certificate = repository.findCertificate(patientCard, medicalTest);
        if (certificate == null) {
            Diagnosis diagnosis = repository.findDiagnosis(patientCard, medicalTest);
            certificate = new CertificateBuilder()
                    .setDiagnosis(diagnosis)
                    .setDate(getTodaysDate())
                    .build();
            repository.insertCertificate(patientCard, certificate);
        }
        return certificate;
    }

    public PatientCard getPatientCard(Client client) {
        return repository.findPatientCard(client);
    }

    public String getTodaysDate() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }
}
