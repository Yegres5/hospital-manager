package core;

import util.ClinicManager;
import util.Constants;

public class Doctor extends Employee {
    public Doctor(String name, String title) {
        super(name, title);
    }

    public Boolean provideService(Integer appointmentId) {
        Appointment appointment = ClinicManager.getInstance().getAppointment(appointmentId);
        ServiceStatus serviceStatus = Catalog.getServiceStatus(appointment.serviceStatusId);
        Client client = Catalog.getClient(serviceStatus.clientId);
        // do something usefull
        String result = uesfullWork(client, serviceStatus);

        serviceStatus.positive = result != "";
        serviceStatus.result = result;

        serviceStatus.currentStatus = Constants.ServiceStatus.FINISHED.getStatus();
        return serviceStatus.positive;
    }

    private String uesfullWork(Client client, ServiceStatus serviceStatus) {
        if (serviceStatus.childServiceStatusIds.isEmpty() && client.name == "Sick client") {
            return "You are sick";
        }

        String result = "";

        for (ServiceStatus childServiceStatus : serviceStatus.getChildServiceStatuses()) {
            if (childServiceStatus.positive) {
                result += childServiceStatus.result;
            }
        }

        return result;
    }
}
