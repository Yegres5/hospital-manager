package core;

import java.util.List;
import java.util.ArrayList;

public class Service {
    public Integer serviceId;
    public String type;
    public String name;
    public Float cost;
    public Integer parentServiceId;
    public List<Integer> childServiceIds;

    public Service(String type, String name, Float cost, Integer parentServiceId) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.parentServiceId = parentServiceId;
    }

    public List<Service> getAllChildServices() {
        List<Service> services = new ArrayList<Service>();

        if (childServiceIds != null) {
            for (Integer serviceId : childServiceIds) {
                Service childService = Catalog.getService(serviceId);
                services.addAll(childService.getAllChildServices());
                services.add(childService);
            }
        }

        return services;
    }
}
