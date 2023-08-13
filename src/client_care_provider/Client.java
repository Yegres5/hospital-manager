package client_care_provider;

import clinic_interface.Clinic;
import repository.Certificate;
import repository.Diagnosis;
import repository.MedicalTest;
import schedule.Appointment;

import java.util.ArrayList;

public class Client {
    private final String name;
    private final Integer stateId;
    private final String personalData;

    public Client(String name, Integer stateId, String personalData) {
        this.name = name;
        this.personalData = personalData;
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public String getPersonalData() {
        return personalData;
    }

    public Administrator findAdministrator(Clinic clinic) {
        return clinic.getAdministrator();
    }

    public Doctor findDoctor(Clinic clinic, Appointment appointment) {
        return clinic.getDoctor(appointment);
    }

    public ArrayList<MedicalTest> recieveMedicalTestList(Administrator administrator) {
        return administrator.getMedicalTestsList();
    }

    public ArrayList<Appointment> payForMedicalTest(Administrator administrator, MedicalTest medicalTest) {
        return administrator.processMedicalTestPayment( this, medicalTest);
    }

    public Diagnosis visitAppointment(Clinic clinic, Appointment appointment) {
        Doctor doctor = findDoctor(clinic, appointment);

        return doctor.provideAppointmentTest(this, appointment);
    }

    public Certificate getCertificate(Clinic clinic, MedicalTest medicalTest) {
        return clinic.getAdministrator().generateCertificate(this, medicalTest);
    }
}
