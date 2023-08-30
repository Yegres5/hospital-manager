package clinic.repository;

import clinic.client_care_provider.Doctor;

public class Diagnosis {
    private final MedicalTest medicalTest;
    private final Doctor doctor;
    private final String date;
    private final Boolean positive;
    private final String medicalReport;

    public Diagnosis(MedicalTest medicalTest, Doctor doctor, String date, Boolean positive, String medicalReport) {
        this.medicalTest = medicalTest;
        this.doctor = doctor;
        this.date = date;
        this.positive = positive;
        this.medicalReport = medicalReport;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public Boolean getPositive() {
        return positive;
    }

    public String getMedicalReport() {
        return medicalReport;
    }
}
