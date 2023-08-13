package repository;

import clinic_staff.DoctorTitle;
import java.util.ArrayList;

public class MedicalTestPackage extends MedicalTest {
    private ArrayList<BasicMedicalTest> packageTests;
    public MedicalTestPackage(DoctorTitle doctorTitle, Float cost, String name, ArrayList<BasicMedicalTest> packageTest) {
        super(doctorTitle, cost, name);
        this.packageTests = packageTest;
    }

    public ArrayList<BasicMedicalTest> getPackageTests() {
        return packageTests;
    }
}
