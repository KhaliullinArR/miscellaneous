package locator;

import cache.Cache;
import initializator.InitialContext;
import service.MessagingService;

public class ServiceLocator {
    private static Cache cache = new Cache();

    public static MessagingService getService(String receiveName) {
        MessagingService messagingService = cache.getService(receiveName);

        if (messagingService != null) {
            return messagingService;
        }

        InitialContext initialContext = new InitialContext();
        MessagingService service1 = (MessagingService) initialContext.lookup(receiveName);
        cache.addService(service1);

        return service1;
    }
}
