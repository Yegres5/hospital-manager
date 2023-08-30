package clinic.repository;

import clinic.client_care_provider.Client;

import java.util.ArrayList;

public class PatientCard {
    private final Client client;
    private final ArrayList<Certificate> certificates = new ArrayList<>();
    private final ArrayList<Diagnosis> diagnoses = new ArrayList<>();

    public PatientCard(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }

    public ArrayList<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        diagnoses.add(diagnosis);
    }
}
