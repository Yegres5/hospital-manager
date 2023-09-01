package clinic.repository.util;

import clinic.client_care_provider.Doctor;
import clinic.repository.Diagnosis;
import clinic.repository.MedicalTest;

public class DiagnosisBuilder {
    private MedicalTest medicalTest;
    private Doctor doctor;
    private String date;
    private String medicalReport;
    private Boolean positive = false;

    public DiagnosisBuilder setMedicalTest(MedicalTest medicalTest) {
        this.medicalTest = medicalTest;
        return this;
    }

    public DiagnosisBuilder setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public DiagnosisBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public DiagnosisBuilder setMedicalReport(String medicalReport) {
        this.medicalReport = medicalReport;
        if (this.medicalReport != null) this.positive = true;
        return this;
    }

    public Diagnosis build() {
        return new Diagnosis(medicalTest, doctor, date, positive, medicalReport);
    }
}
