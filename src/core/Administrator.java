package core;

import util.ClinicManager;
import util.Constants;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrator extends Employee {
    public Administrator(String name, String title) {
        super(name, Constants.EmployeeTitle.ADMINISTRATOR.getTitle());
    }

    public Client checkIn(String name, Integer citizenId) {
        Client client = new Client(citizenId, name);
        Catalog.putClient(client);
        return client;
    }

    public List<ServiceStatus> recievePaymentForService(Integer serviceId, Integer clientId, Float payment) {
        if (payment >= getServiceTotalCost(serviceId)) {
            ClinicManager.getInstance().addAssets(payment);
            return createPayedServiceStatuses(serviceId, clientId);
        }

        return null;
    }

    public List<ServiceStatus> createPayedServiceStatuses(Integer serviceId, Integer clientId) {
        return createPayedServiceStatuses(serviceId, clientId, new ArrayList<ServiceStatus>(),
                new HashMap<Integer, Integer>());
    }

    public List<ServiceStatus> createPayedServiceStatuses(Integer serviceId, Integer clientId,
            List<ServiceStatus> serviceStatuses, HashMap<Integer, Integer> serviceIdToServiceStatusId) {

        Service service = Catalog.getService(serviceId);
        Integer parentServiceStatusId = null;
        ServiceStatus parentServiceStatus = Catalog.getServiceStatus(
                serviceIdToServiceStatusId.get(service.parentServiceId));
        if (parentServiceStatus != null) {
            parentServiceStatusId = parentServiceStatus.serviceStatusId;
        }
        ServiceStatus serviceStatus = new ServiceStatus(true, service.serviceId, clientId, parentServiceStatusId);
        Catalog.putServiceStatus(serviceStatus);
        serviceStatuses.add(serviceStatus);
        serviceIdToServiceStatusId.put(serviceId, serviceStatus.serviceStatusId);

        List<Service> childServices = service.getAllChildServices();
        for (Service childService : childServices) {
            createPayedServiceStatuses(childService.serviceId, clientId, serviceStatuses, serviceIdToServiceStatusId);
        }

        return serviceStatuses;
    }

    public Float getServiceTotalCost(Integer serviceId) {
        Service service = Catalog.getService(serviceId);
        Float totalCost = service.cost;
        for (Service childService : service.getAllChildServices()) {
            totalCost += childService.cost;
        }
        return totalCost;
    }

    public List<Doctor> getDoctorsForService(Integer serviceId) {
        List<Doctor> eligebleDoctors = new ArrayList<Doctor>();
        Service service = Catalog.getService(serviceId);

        for (Employee employee : ClinicManager.getInstance().getEmployeeList()) {
            if (employee.title == Constants.ServiceTypeToEmploeeTitle.get(service.type)) {
                eligebleDoctors.add((Doctor) employee);
            }
        }

        return eligebleDoctors;
    }

    public String createCertificate(Integer serviceStatusId) {
        ServiceStatus serviceStatus = Catalog.getServiceStatus(serviceStatusId);

    }

    public List<Service> getServiceList() {
        return Catalog.getAllServices();
    }

    class UnpaidServiceStatusesException extends Exception {
        private List<ServiceStatus> serviceStatuses;

        public UnpaidServiceStatusesException(List<ServiceStatus> serviceStatuses) {
            this.serviceStatuses = serviceStatuses;
        }

        public List<ServiceStatus> getUnpaidServiceStatuses() {
            return serviceStatuses;
        }
    }
}
