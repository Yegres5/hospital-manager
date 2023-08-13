package repository;

import clinic_staff.DoctorTitle;

public abstract class MedicalTest {
    private DoctorTitle requiredDoctor;
    private Float cost;
    private String name;

    public MedicalTest(DoctorTitle doctorTitle, Float cost, String name) {
        this.requiredDoctor = doctorTitle;
        this.cost = cost;
    }

    public DoctorTitle getRequiredDoctor() {
        return requiredDoctor;
    }

    public Float getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
