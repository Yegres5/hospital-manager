package core;

import util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ServiceStatus {
    public Integer serviceStatusId;
    public String currentStatus;
    public Boolean payed;
    public Boolean positive;
    public String result;
    public Integer serviceId;
    public Integer clientId;
    public Integer parentServiceStatusId;
    public List<Integer> childServiceStatusIds = new ArrayList<Integer>();

    public ServiceStatus(Boolean payed, Integer serviceId, Integer clientId, Integer parentServiceStatusId) {
        this.currentStatus = Constants.ServiceStatus.NEW.getStatus();
        this.payed = payed;
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.parentServiceStatusId = parentServiceStatusId;
    }

    public List<ServiceStatus> getAllChildServiceStatuses() {
        List<ServiceStatus> serviceStatuses = new ArrayList<ServiceStatus>();

        if (childServiceStatusIds != null) {
            for (Integer serviceStatusId : childServiceStatusIds) {
                ServiceStatus childServiceStatus = Catalog.getServiceStatus(serviceStatusId);
                serviceStatuses.addAll(childServiceStatus.getAllChildServiceStatuses());
                serviceStatuses.add(childServiceStatus);
            }
        }

        return serviceStatuses;
    }

    public List<ServiceStatus> getChildServiceStatuses() {
        List<ServiceStatus> serviceStatuses = new ArrayList<ServiceStatus>();

        if (childServiceStatusIds != null) {
            for (Integer serviceStatusId : childServiceStatusIds) {
                serviceStatuses.add(Catalog.getServiceStatus(serviceStatusId));
            }
        }

        return serviceStatuses;
    }
}
