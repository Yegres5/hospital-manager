package clinic.repository;

import clinic.client_care_provider.Speciality;

public class BasicMedicalTest implements MedicalTest {
    private final String name;
    private final Float cost;
    private final Speciality speciality;

    public BasicMedicalTest(String name, Float cost, Speciality speciality) {
        this.name = name;
        this.cost = cost;
        this.speciality = speciality;
    }

    @Override
    public Float getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Speciality getSpeciality() {
        return speciality;
    }
}
