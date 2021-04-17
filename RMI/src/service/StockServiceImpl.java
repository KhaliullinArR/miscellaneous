package service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StockServiceImpl extends UnicastRemoteObject implements StockServer {

    private String price = null;
    private ArrayList<String> nasqadSymbols = new ArrayList<>();


    public StockServiceImpl(int port) throws RemoteException {
        super(port);


        nasqadSymbols.add("AAPL");
        nasqadSymbols.add("MSFT");
        nasqadSymbols.add("YHOO");
        nasqadSymbols.add("AMZN");
        nasqadSymbols.add("MOT");
    }

    @Override
    public String getQuote(String symbol) throws RemoteException {
        if(nasqadSymbols.indexOf(symbol.toUpperCase())!=-1){
            price = (new Double(Math.random() * 100)).toString();
        }
        return price;
    }

    @Override
    public List<String> getNasqadSymbols() throws RemoteException {
        return nasqadSymbols;
    }
}
