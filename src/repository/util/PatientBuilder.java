package repository.util;

import client_care_provider.Client;
import repository.Patient;

public class PatientBuilder {
    private static Integer lastId = 0;
    private final Client client;
    private String additionalInfo;

    public PatientBuilder(Client client) {
        this.client = client;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Patient build() {
        return new Patient(lastId++, client, additionalInfo);
    }
}
