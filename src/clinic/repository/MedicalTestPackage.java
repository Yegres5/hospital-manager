package clinic.repository;

import clinic.client_care_provider.Speciality;

import java.util.ArrayList;

public class MedicalTestPackage implements MedicalTest{
    private final String name;
    private final Float cost;
    private final Speciality speciality;
    private final ArrayList<MedicalTest> medicalTests;

    public MedicalTestPackage(String name, Float cost, Speciality speciality, ArrayList<MedicalTest> medicalTests) {
        this.name = name;
        this.cost = cost;
        this.medicalTests = medicalTests;
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

    public ArrayList<MedicalTest> getMedicalTests() {
        return medicalTests;
    }
}
