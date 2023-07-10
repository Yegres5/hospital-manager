package core;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Catalog {
    private static Integer clientLastId = 0;
    private static Integer serviceStatusLastId = 0;
    private static Integer serviceLastId = 0;

    public static HashMap<Integer, Client> clients = new HashMap<Integer, Client>();
    public static HashMap<Integer, ServiceStatus> serviceStatuses = new HashMap<Integer, ServiceStatus>();
    public static HashMap<Integer, Service> services = new HashMap<Integer, Service>();

    public static Client getClient(Integer clientId) {
        return clients.get(clientId);
    }

    public static Integer putClient(Client client) {
        Integer clientId = clientLastId++;

        client.clientId = clientId;
        clients.put(clientId, client);
        return clientId;
    }

    public static ServiceStatus getServiceStatus(Integer serviceStatusId) {
        return serviceStatuses.get(serviceStatusId);
    }

    public static Integer putServiceStatus(ServiceStatus serviceStatus) {
        Integer serviceStatusId = serviceStatusLastId++;

        serviceStatus.serviceStatusId = serviceStatusId;
        updateParentServiceStatusRelationship(serviceStatus);
        serviceStatuses.put(serviceStatusId, serviceStatus);
        return serviceStatusId;
    }

    private static void updateParentServiceStatusRelationship(ServiceStatus childServiceStatus) {
        if (childServiceStatus.parentServiceStatusId != null) {
            ServiceStatus parentServiceStatus = getServiceStatus(childServiceStatus.parentServiceStatusId);
            if (parentServiceStatus.childServiceStatusIds == null) {
                parentServiceStatus.childServiceStatusIds = new ArrayList<Integer>();
            }
            parentServiceStatus.childServiceStatusIds.add(childServiceStatus.serviceStatusId);
        }
    }

    public static Service getService(Integer serviceId) {
        return services.get(serviceId);
    }

    public static Integer putService(Service service) {
        Integer serviceId = serviceLastId++;

        service.serviceId = serviceId;
        updateParentServiceRelationship(service);
        services.put(serviceId, service);
        return serviceId;
    }

    private static void updateParentServiceRelationship(Service childService) {
        if (childService.parentServiceId != null) {
            Service parentService = getService(childService.parentServiceId);
            if (parentService.childServiceIds == null) {
                parentService.childServiceIds = new ArrayList<Integer>();
            }
            parentService.childServiceIds.add(childService.serviceId);
        }
    }

    public static List<Service> getAllServices() {
        return new ArrayList<Service>(services.values());
    }
}
