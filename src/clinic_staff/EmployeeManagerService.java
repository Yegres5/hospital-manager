package clinic_staff;

import client_care_provider.Administrator;
import client_care_provider.Doctor;
import client_care_provider.Employee;
import repository.MedicalTest;

import java.util.ArrayList;

public class EmployeeManagerService {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static ArrayList<Doctor> getDoctorsList() {
        ArrayList<Doctor> doctors = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee instanceof Doctor) {
                doctors.add((Doctor) employee);
            }
        }

        return doctors;
    }

    public static ArrayList<Doctor> getDoctorsForMedicalTest(MedicalTest medicalTest) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        for (Employee employee : employees) {
            if (
                    employee instanceof Doctor &&
                    ((Doctor) employee).getTitle() == medicalTest.getRequiredDoctor()
            ) {
                doctors.add((Doctor) employee);
            }
        }

        return doctors;
    }

    public static Administrator getAdministrator() {
        return (Administrator) employees.stream()
                .filter(employee -> employee instanceof Administrator)
                .findAny()
                .orElse(null);
    }
}
