package clinic.schedule;

import clinic.client_care_provider.Doctor;
import clinic.repository.MedicalTest;
import clinic.repository.PatientCard;

public class Appointment {
    PatientCard patientCard;
    Doctor doctor;
    MedicalTest medicalTest;
    String date;

    public Appointment(PatientCard patientCard, Doctor doctor, MedicalTest medicalTest, String date) {
        this.patientCard = patientCard;
        this.doctor = doctor;
        this.medicalTest = medicalTest;
        this.date = date;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public String getDate() {
        return date;
    }
}
