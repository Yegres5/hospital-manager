package clinic.repository;

import clinic.client_care_provider.Speciality;

public interface MedicalTest {
    public Float getCost();
    public String getName();
    public Speciality getSpeciality();
}
