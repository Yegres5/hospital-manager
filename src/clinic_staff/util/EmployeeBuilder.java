package clinic_staff.util;

import clinic_interface.Clinic;
import clinic_staff.DoctorTitle;

public interface EmployeeBuilder {
    public EmployeeBuilder setName(String name);
    public EmployeeBuilder setClinic(Clinic clinic);
    public EmployeeBuilder setTitle(DoctorTitle title);
}
