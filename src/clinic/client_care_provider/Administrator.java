package clinic.client_care_provider;

import clinic.Clinic;
import clinic.repository.*;
import clinic.schedule.Appointment;

import java.util.ArrayList;

public class Administrator extends Employee{
    public Administrator(String name, Clinic clinic) {
        super(name, clinic);
    }

    public PatientCard createPatientCard(Client client) {
        PatientCard patientCard = new PatientCard(client);
        getTerminal().registerPatientCard(patientCard);
        return patientCard;
    }

    public ArrayList<MedicalTest> getMedicalTests() {
        return getTerminal().getMedicalTests();
    }

    public ArrayList<Appointment> processMedicalTestRequest(Client client, MedicalTest medicalTest) {
        addAssets(medicalTest.getCost());
        PatientCard patientCard = getTerminal().getPatientCard(client);

        ArrayList<Appointment> appointments = new ArrayList<>();
        if (medicalTest instanceof BasicMedicalTest basicMedicalTest) {
            appointments.add(scheduleMedicalTest(patientCard, basicMedicalTest, getTerminal().getTodaysDate()));
        } else if (medicalTest instanceof  MedicalTestPackage medicalTestPackage) {
            appointments.addAll(scheduleMedicalTestPackage(patientCard, medicalTestPackage, getTerminal().getTodaysDate()));
        }

        return appointments;
    }

    public Certificate generateCertificate(Client client, MedicalTest medicalTest) {
        PatientCard patientCard = getTerminal().getPatientCard(client);
        return getTerminal().getCertificate(patientCard, medicalTest);
    }

    private Appointment scheduleMedicalTest(PatientCard patientCard, MedicalTest medicalTest, String date) {
        Doctor doctor = getTerminal().getAvailableDoctors(medicalTest.getSpeciality()).get(0);
        return getTerminal().scheduleMedicalTest(patientCard, medicalTest, doctor, date);
    }

    private ArrayList<Appointment> scheduleMedicalTestPackage(PatientCard patientCard, MedicalTestPackage medicalTestPackage, String date) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (MedicalTest childMedicalTest : medicalTestPackage.getMedicalTests()) {
            if (childMedicalTest instanceof BasicMedicalTest basicMedicalTest) {
                appointments.add(scheduleMedicalTest(patientCard, basicMedicalTest, date));
            } else if (childMedicalTest instanceof MedicalTestPackage childMedicalTestPackage) {
                appointments.addAll(scheduleMedicalTestPackage(patientCard, childMedicalTestPackage, date));
            }
        }
        appointments.add(scheduleMedicalTest(patientCard, medicalTestPackage, date));
        return appointments;
    }

    private void addAssets(Float assets) {
        clinic.addAssets(assets);
    }

    private AdministratorTerminal getTerminal() {
        return clinic.getAdministratorTerminal();
    }



}
