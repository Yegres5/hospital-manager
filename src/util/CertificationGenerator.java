package util;

import core.ServiceStatus;
import core.Catalog;
import java.util.List;
import java.util.ArrayList;

public class CertificationGenerator {
    private ServiceStatus serviceStatus;
    private List<ServiceStatus> failedServiceStatuses = new ArrayList<ServiceStatus>();
    private String diagnosis;
    private Boolean positive;

    public CertificationGenerator(Integer serviceStatusId) {
        serviceStatus = Catalog.getServiceStatus(serviceStatusId);
        positive = serviceStatus.positive;
        diagnosis = serviceStatus.result;
    }

    public String generateCertificate() throws Exception {
        if (isServiceStatusCompleted(serviceStatus.serviceStatusId)) {
            return Certificate
                    .createNewCertificate()
                    .setServiceResultOutcome(positive)
                    .addDetails(diagnosis)
                    .printCertificate();
        }
        throw new Exception("Service is not completed");
    }

    private boolean isServiceStatusCompleted(Integer serviceStatusId) {
        ServiceStatus serviceStatus = Catalog.getServiceStatus(serviceStatusId);
        Boolean allChildServicesCompleted = true;

        for (Integer childServiceId : serviceStatus.childServiceStatusIds) {
            allChildServicesCompleted &= isServiceStatusCompleted(childServiceId);
        }

        if (!verifyServiceStatus(serviceStatus)) {
            failedServiceStatuses.add(serviceStatus);
            return false;
        }

        return allChildServicesCompleted;
    }

    private boolean verifyServiceStatus(ServiceStatus serviceStatus) {
        return serviceStatus.payed == true
                && serviceStatus.currentStatus == Constants.ServiceStatus.FINISHED.getStatus();
    }
}
