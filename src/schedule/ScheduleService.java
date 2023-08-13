package schedule;

import client_care_provider.Doctor;
import repository.MedicalTest;
import repository.Patient;

import java.util.ArrayList;

public class ScheduleService {
    private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    public static Appointment createAppointment(Patient patient, MedicalTest medicalTest, Doctor doctor){
        Appointment appointment = new Appointment(
                generateAppropriateDate(),
                patient,
                medicalTest,
                doctor
        );
        appointments.add(appointment);
        return appointment;
    }

    private static String generateAppropriateDate() {
        return "9999-12-31";
    }
}
