package me.chatapp;

import SharedInterface.IClientChat;
import me.chatapp.forms.ChatForm;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements IClientChat {
    ChatForm chatForm;
    public ChatClientImpl(ChatForm chatForm) throws RemoteException {
        this.chatForm = chatForm;
    }
    @Override
    public String receiveMessage(String message, String userName) throws RemoteException {
        chatForm.setTextAreaRecievedMessages(userName+": "+message);
        return null;
    }
    @Override
    public String receiveMessageFromClient(String message, String username) throws RemoteException {

        return null;
    }
}
