package repository;

public class Diagnosis {
    private final Integer Id;
    private final String result;
    private final Boolean positive;
    private final Patient patient;
    private final MedicalTest medicalTest;

    public Diagnosis(Integer Id, String result, Boolean positive, Patient patient, MedicalTest medicalTest) {
        this.Id = Id;
        this.result = result;
        this.positive = positive;
        this.patient = patient;
        this.medicalTest = medicalTest;
    }

    public Boolean isPositive() {
        return positive;
    }

    public MedicalTest getMedicalTest() {
        return medicalTest;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getResult() {
        return result;
    }

    public Integer getId() {
        return Id;
    }
}
