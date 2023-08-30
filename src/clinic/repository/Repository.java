package clinic.repository;

import clinic.client_care_provider.Client;

import java.util.ArrayList;
import java.util.Objects;

public class Repository {
    private final ArrayList<PatientCard> patientCards = new ArrayList<>();
    private final ArrayList<MedicalTest> medicalTests = new ArrayList<>();

    public ArrayList<MedicalTest> retrieveMedicalTests() {
        return medicalTests;
    }

    public void insertPatientCard(PatientCard patientCard) {
        patientCards.add(patientCard);
    }

    public void insertCertificate(PatientCard patientCard, Certificate certificate) {
        patientCard.addCertificate(certificate);
    }

    public void insertDiagnosis(PatientCard patientCard, Diagnosis diagnoses) {
        patientCard.addDiagnosis(diagnoses);
    }
    public void insertMedicalTest(MedicalTest medicalTest) {
        medicalTests.add(medicalTest);
    }

    public PatientCard findPatientCard(Client client) {
        return patientCards.stream()
                .filter(patientCard -> patientCard.getClient() == client)
                .findAny()
                .orElse(null);
    }

    public Certificate findCertificate(PatientCard patientCard, MedicalTest medicalTest) {
        return patientCard.getCertificates().stream()
                .filter(certificate -> certificate.getDiagnosis().getMedicalTest() == medicalTest)
                .findFirst()
                .orElse(null);
    }

    public Diagnosis findDiagnosis(PatientCard patientCard, MedicalTest medicalTest) {
        return patientCard.getDiagnoses().stream()
                .filter(diagnosis -> diagnosis.getMedicalTest() == medicalTest)
                .findFirst()
                .orElse(null);
    }
}
