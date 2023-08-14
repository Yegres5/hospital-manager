package client_care_provider;

import clinic_interface.AdministratorTerminal;
import repository.*;
import schedule.Appointment;

import java.util.ArrayList;

public class Administrator extends Employee {
    private final AdministratorTerminal terminal;

    public Administrator(String name, AdministratorTerminal terminal) {
        super(name);
        this.terminal = terminal;
    }

    public ArrayList<MedicalTest> getMedicalTests() {
        return terminal.getMedicalTests();
    }

    public MedicalTest getCheckUpTest() {
        return terminal.getCheckUpMedicalTest();
    }

    public ArrayList<Appointment> processMedicalTestPayment(Client client, MedicalTest medicalTest) {
        Float testCost = medicalTest.getCost();
        terminal.addFunds(testCost);

        return scheduleAppointment(medicalTest, client);
    }

    public Certificate generateCertificate(Client client, MedicalTest medicalTest) {
        return terminal.generateCertificate(
                terminal.getPatient(client),
                medicalTest
        );
    }

    private ArrayList<Appointment> scheduleAppointment(MedicalTest medicalTest, Client client) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Patient patient = terminal.getPatient(client);

        if (medicalTest instanceof BasicMedicalTest) {
            Doctor doctor = terminal.getDoctorForMedicalTest(medicalTest);
            appointments.add(terminal.scheduleAppointment(patient, medicalTest, doctor));
        } else {
            MedicalTestPackage medicalTestPackage = (MedicalTestPackage) medicalTest;
            for (BasicMedicalTest basicMedicalTest : medicalTestPackage.getPackageTests()) {
                Doctor doctor = terminal.getDoctorForMedicalTest(medicalTest);
                appointments.add(terminal.scheduleAppointment(patient, basicMedicalTest, doctor));
            }

            Doctor doctor = terminal.getDoctorForMedicalTest(medicalTestPackage);
            appointments.add(terminal.scheduleAppointment(patient, medicalTestPackage, doctor));
        }

        return appointments;
    }
}
