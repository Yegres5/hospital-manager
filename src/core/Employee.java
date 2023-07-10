package core;

import util.ClinicManager;

public abstract class Employee {
    public Integer employeeId;

    public String name;
    public String title;

    public Employee(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Appointment scheduleAppointment(Integer doctorId, Integer serviceStatusId) {
        Appointment appointment = new Appointment(doctorId, serviceStatusId);
        ClinicManager.getInstance().addNewAppointment(appointment);

        return appointment;
    }
}
