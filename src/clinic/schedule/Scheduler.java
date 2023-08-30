package clinic.schedule;

import java.time.LocalTime;
import java.util.ArrayList;

public class Scheduler {
    private final ArrayList<Appointment> appointments = new ArrayList<>();

    public void insertAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
