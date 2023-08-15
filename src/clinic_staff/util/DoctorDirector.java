package clinic_staff.util;

import clinic_staff.DoctorTitle;

public class DoctorDirector {
    public static void constructPathologist(EmployeeBuilder builder) {
        builder.setTitle(DoctorTitle.PATHOLOGIST);
    }

    public static void constructPractitioner(EmployeeBuilder builder) {
        builder.setTitle(DoctorTitle.PRACTITIONER);
    }
}
