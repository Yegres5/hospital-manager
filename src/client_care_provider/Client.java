package client_care_provider;

import clinic_interface.Clinic;
import repository.Certificate;
import repository.Diagnosis;
import repository.MedicalTest;
import schedule.Appointment;

import java.util.ArrayList;

public class Client {
    private final String name;
    private final Integer stateId;
    private final String personalData;

    public Client(String name, Integer stateId, String personalData) {
        this.name = name;
        this.personalData = personalData;
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public String getPersonalData() {
        return personalData;
    }

    public ArrayList<MedicalTest> recieveMedicalTestList(Administrator administrator) {
        return administrator.getMedicalTests();
    }

    public MedicalTest recieveCheckUpTest(Administrator administrator) {
        return administrator.getCheckUpTest();
    }

    public ArrayList<Appointment> payForMedicalTest(Administrator administrator, MedicalTest medicalTest) {
        return administrator.processMedicalTestPayment( this, medicalTest);
    }

    public Diagnosis visitAppointment(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();

        return doctor.provideAppointmentMedicalTest(this, appointment);
    }

    public Certificate getCertificate(Administrator administrator, MedicalTest medicalTest) {
        return administrator.generateCertificate(this, medicalTest);
    }
}
