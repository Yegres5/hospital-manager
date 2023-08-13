package clinic_interface;

import client_care_provider.Client;
import repository.Patient;
import repository.RepositoryService;

public abstract class BasicTerminal {
    private final Clinic clinic;
    public BasicTerminal(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient(Client client) {
        return RepositoryService.getPatient(client);
    }
}
