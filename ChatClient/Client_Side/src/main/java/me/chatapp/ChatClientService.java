package me.chatapp;
import SharedInterface.ChatInterface;
import me.chatapp.forms.StartedForm;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatClientService {

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        ChatInterface chatInterface = (ChatInterface) Naming.lookup("//localhost/ChatService");
            try {
                StartedForm start = new StartedForm(chatInterface);
                start.setVisible(true);
            }catch (Exception e){
                System.out.println(e);
            }
        }
}