package repository;

import clinic_staff.DoctorTitle;

public abstract class MedicalTest {
    private final DoctorTitle requiredDoctor;
    private final Float cost;
    private final String name;

    public MedicalTest(DoctorTitle doctorTitle, Float cost, String name) {
        this.requiredDoctor = doctorTitle;
        this.cost = cost;
        this.name = name;
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
