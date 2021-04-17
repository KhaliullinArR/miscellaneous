package client;

import service.StockServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("\nUsage: java -Djava.security.policy=security.policy Client AAPL");
            System.exit(0);
        }

        try{
            Registry registry = LocateRegistry.getRegistry("192.168.10.95", 123);
            StockServer myServer = (StockServer) registry.lookup("QuoteService");

            String price = myServer.getQuote(args[0]);

            if (price!=null){
                System.out.println("The price of" + args[0] + "is: $"+price);
            }else {
                System.out.println("Invalid Nasqad symbol. " + "Please use one of these: " + myServer.getNasqadSymbols());
            }

        } catch (RemoteException | NotBoundException  e) {
            e.printStackTrace();
        }
    }
}
