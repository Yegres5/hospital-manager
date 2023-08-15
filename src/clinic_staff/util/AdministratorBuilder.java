package clinic_staff.util;

import client_care_provider.Administrator;
import clinic_interface.AdministratorTerminal;
import clinic_interface.Clinic;
import clinic_staff.DoctorTitle;

public class AdministratorBuilder implements EmployeeBuilder {
    private String name;
    private Clinic clinic;

    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder setClinic(Clinic clinic) {
        this.clinic = clinic;
        return this;
    }
    public EmployeeBuilder setTitle(DoctorTitle title) {
        return this;
    }

    public Administrator build() {
        AdministratorTerminal terminal = new AdministratorTerminal(clinic);
        Administrator administrator = new Administrator(name, terminal);
        cleanParams();
        return administrator;
    }

    private void cleanParams() {
        this.name = null;
        this.clinic = null;
    }
}
