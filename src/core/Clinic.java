package core;

import java.util.HashMap;
import java.util.List;

import util.Constants;

import java.util.ArrayList;

public class Clinic {
    public String name;
    public String address;
    private Float assets;
    private final HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
    private final HashMap<Integer, Appointment> appointments = new HashMap<Integer, Appointment>();
    private Integer lastEmployeeId = 0;
    private Integer lastAppointmentId = 0;

    public Clinic(String name, String address, Float assets) {
        this.name = name;
        this.address = address;
        this.assets = assets;
    }

    public void addAssets(Float assets) {
        this.assets += assets;
    }

    public Integer addNewEmplooyee(Employee employee) {
        Integer employeeId = lastEmployeeId++;

        employee.employeeId = employeeId;
        this.employees.put(employeeId, employee);

        return employeeId;
    }

    public Employee getEmployee(Integer employeeId) {
        return this.employees.get(employeeId);
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<Employee>(employees.values());
    }

    public Integer addNewAppointment(Appointment appointment) {
        Integer appointmentId = lastAppointmentId++;

        appointment.appointmentId = appointmentId;
        this.appointments.put(appointmentId, appointment);

        Catalog.getServiceStatus(appointment.serviceStatusId).currentStatus = Constants.ServiceStatus.SCHEDULED
                .getStatus();

        return appointmentId;
    }

    public Appointment getAppointment(Integer appointmentId) {
        return this.appointments.get(appointmentId);
    }
}
