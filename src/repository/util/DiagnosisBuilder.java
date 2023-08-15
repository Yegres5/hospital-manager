package repository.util;

import repository.Diagnosis;
import repository.MedicalTest;
import repository.Patient;

public class DiagnosisBuilder {
    private static Integer lastId = 0;
    private String result;
    private Boolean positive;
    private final Patient patient;
    private final MedicalTest medicalTest;

    public DiagnosisBuilder(Patient patient, MedicalTest medicalTest) {
        this.patient = patient;
        this.medicalTest = medicalTest;
    }

    public DiagnosisBuilder setResult(String result) {
        if (result != null) {
            this.result = result;
            this.positive = true;
        } else {
            this.positive = false;
        }
        return this;
    }

    public Diagnosis build() {
        return new Diagnosis(lastId++, result, positive, patient, medicalTest);
    }
}
