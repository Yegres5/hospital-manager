package clinic_staff.util;

import client_care_provider.Doctor;
import clinic_interface.Clinic;
import clinic_interface.DoctorTerminal;
import clinic_staff.DoctorTitle;

public class DoctorBuilder implements EmployeeBuilder {
    private String name;
    private Clinic clinic;
    private DoctorTitle title;

    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DoctorBuilder setClinic(Clinic clinic) {
        this.clinic = clinic;
        return this;
    }

    public EmployeeBuilder setTitle(DoctorTitle title) {
        this.title = title;
        return this;
    }

    public Doctor build() {
        DoctorTerminal terminal = new DoctorTerminal(clinic);
        Doctor doctor = new Doctor(name, title, terminal);
        cleanParams();
        return doctor;
    }

    private void cleanParams() {
        this.name = null;
        this.title = null;
        this.clinic = null;
    }
}
