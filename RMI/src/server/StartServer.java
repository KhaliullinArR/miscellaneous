package server;

import service.StockServer;
import service.StockServiceImpl;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartServer {
    public static void main(String[] args) {
        try {
            StockServiceImpl ssi = new StockServiceImpl(13599);
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry registry = LocateRegistry.createRegistry(123);

            registry.bind("rmi://localhost:5565/QuoteService", ssi);
            /*
            Registry localRegistry = LocateRegistry.createRegistry(123);*/
            //localRegistry.bind("rmi//:192.168.10.95:123/QuoteService",ssi);
            System.out.println("<QuoteServer> is ready.");
        } catch (RemoteException  | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
