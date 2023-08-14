package client_care_provider;

import clinic_interface.DoctorTerminal;
import clinic_staff.DoctorTitle;
import repository.*;
import schedule.Appointment;

import java.util.ArrayList;
import java.util.Objects;

public class Doctor extends Employee {
    private final DoctorTitle title;
    private final DoctorTerminal terminal;
    public Doctor(String name, DoctorTitle title, DoctorTerminal terminal) {
        super(name);
        this.title = title;
        this.terminal = terminal;
    }

    public DoctorTitle getTitle() {
        return title;
    }

    public Diagnosis provideAppointmentMedicalTest(Client client, Appointment appointment) {
        MedicalTest medicalTest = appointment.getMedicalTest();
        Patient patient = terminal.getPatient(client);
        String testResult = performMedicalTest(client, medicalTest);
        return terminal.createDiagnosis(patient, medicalTest, testResult);
    }

    private String performMedicalTest(Client client, MedicalTest medicalTest) {
        String diagnosis;

        if (medicalTest instanceof MedicalTestPackage) {
            diagnosis = checkComplexTest(client, (MedicalTestPackage) medicalTest);
        } else {
            diagnosis = performBasicTest(client, (BasicMedicalTest) medicalTest);
        }
        return diagnosis;
    }

    private String performBasicTest(Client client, BasicMedicalTest basicMedicalTest) {
        if (Objects.equals(client.getPersonalData(), "Sick")) {
            return "Patient is sick";
        }
        return null;
    }

    private String checkComplexTest(Client client, MedicalTestPackage medicalTestPackage) {
        Patient patient = terminal.getPatient(client);
        StringBuilder result = new StringBuilder();

        for (BasicMedicalTest basicMedicalTest : medicalTestPackage.getPackageTests()) {
            Diagnosis diagnosis = terminal.getDiagnosis(patient, basicMedicalTest);
            if (diagnosis.isPositive()) {
                result.append(diagnosis.getResult());
            }
        }

        if (result.isEmpty()) {
            return null;
        }

        return result.toString();
    }
}
