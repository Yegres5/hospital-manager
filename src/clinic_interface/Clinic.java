package clinic_interface;

import client_care_provider.Administrator;
import client_care_provider.Doctor;
import clinic_staff.EmployeeManagerService;
import schedule.Appointment;

public class Clinic {
    private final String name;
    public Clinic(String name) {
        this.name = name;
    }
}
