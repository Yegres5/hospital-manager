package repository;

import client_care_provider.Client;
public class Patient {
    private final Integer Id;
    private final Client client;
    private final String additionalInfo;
    public Patient(Integer Id, Client client, String additionalInfo) {
        this.Id = Id;
        this.client = client;
        this.additionalInfo = additionalInfo;
    }

    public Client getClient() {
        return client;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Integer getId() {
        return Id;
    }
}
