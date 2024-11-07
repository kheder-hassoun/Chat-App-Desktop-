package me.chatapp;


//import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String[] args) throws RemoteException {
        System.setProperty("java.security.policy", "./ChatServicePolicy.policy");
        //System.setSecurityManager(new RMISecurityManager());
        Registry reg = LocateRegistry.createRegistry(1099);


        ChatServiceImpl chatServiceImp = new ChatServiceImpl();

        String ChatSVC = "ChatService";
        reg.rebind(ChatSVC, chatServiceImp);
        System.out.println("Chat Server Ready and Use default port for RMI Service called " + ChatSVC);


    }
}
