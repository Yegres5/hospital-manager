package core;

import util.ClinicManager;
import util.Constants;

import java.util.Objects;

public class Doctor extends Employee {
    public Doctor(String name, String title) {
        super(name, title);
    }

    public Boolean provideService(Integer appointmentId) {
        Appointment appointment = ClinicManager.getInstance().getAppointment(appointmentId);
        ServiceStatus serviceStatus = Catalog.getServiceStatus(appointment.serviceStatusId);
        Client client = Catalog.getClient(serviceStatus.clientId);
        // do something usefull
        String result = usefullWork(client, serviceStatus);

        serviceStatus.positive = !Objects.equals(result, "");
        serviceStatus.result = result;

        serviceStatus.currentStatus = Constants.ServiceStatus.FINISHED.getStatus();
        return serviceStatus.positive;
    }

    private String usefullWork(Client client, ServiceStatus serviceStatus) {
        if (serviceStatus.childServiceStatusIds.isEmpty() && Objects.equals(client.name, "Sick client")) {
            return "You are sick";
        }

        StringBuilder result = new StringBuilder();

        for (ServiceStatus childServiceStatus : serviceStatus.getChildServiceStatuses()) {
            if (childServiceStatus.positive) {
                result.append(childServiceStatus.result);
            }
        }

        return result.toString();
    }
}