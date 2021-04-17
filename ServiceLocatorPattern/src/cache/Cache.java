package cache;

import service.MessagingService;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private List<MessagingService> services = new ArrayList<>();

    public MessagingService getService(String serviceName) {
        for (MessagingService service : services) {
            if(service.getServiceName().equalsIgnoreCase(serviceName))return service;
        }
        return null;
    }

    public void addService(MessagingService service) {
        services.add(service);
    }
}
