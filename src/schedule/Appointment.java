package schedule;

import client_care_provider.Doctor;
import repository.MedicalTest;
import repository.Patient;

public class Appointment {
    private String date;
    private Patient patient;
    private MedicalTest medicalTest;
    private Doctor doctor;
    public Appointment(String date, Patient patient, MedicalTest medicalTest, Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.medicalTest = medicalTest;
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
