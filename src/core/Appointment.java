package core;

public class Appointment {
    public Integer appointmentId;
    public Integer doctorId;
    public Integer serviceStatusId;

    public Appointment(Integer doctorId, Integer serviceStatusId) {
        this.doctorId = doctorId;
        this.serviceStatusId = serviceStatusId;
    }
}
