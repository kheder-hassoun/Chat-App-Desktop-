package SharedInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientChat extends Remote {
    String receiveMessage(String message, String username) throws RemoteException;
}
