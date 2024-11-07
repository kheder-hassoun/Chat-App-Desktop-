package me.chatapp.forms;

import Data.Room;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class PublicChatForm extends ChatForm{
    private JPanel ChatAppPanel;
    private JTextArea tfTextArea;
    private JTextField tfWriteMessage;
    private JButton sendButton;
    private JButton backButton;

    public PublicChatForm(ChatInterface chatInterface, String roomName){

         setTitle("Chat");
         setContentPane(ChatAppPanel);
         setSize(800,700);
        tfTextArea.setEditable(false);
        System.out.println("1");
        try {
            Room room = chatInterface.getRoomByName(roomName);
            if (room != null) {
                List<String> messages = room.getMessages();
                if (messages != null && !messages.isEmpty()) {
                    for (String message : messages) {
                        setTextAreaRecievedMessages(message);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(ChatAppPanel, "Room not found", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                return;
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }



        System.out.println("1");

         sendButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String message = tfWriteMessage.getText();

                 if(message.isEmpty() ){
                     JOptionPane.showMessageDialog(ChatAppPanel, "please write message to send"
                             ,"try again", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 try {
                     tfWriteMessage.setText("");
                     chatInterface.sendMessageToRoom(SharedVariables.getUser().getUserName(),roomName,message);
                 } catch (RemoteException ex) {
                     throw new RuntimeException(ex);
                 }
             }
         });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    chatInterface.setLoggedInUsersForRoom(roomName,SharedVariables.getUser().getUserName());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                ChatRoomForm chatRoomForm = new ChatRoomForm(chatInterface,roomName);
            }
        });
        setVisible(true);
    }
    public void setTextAreaRecievedMessages(String msg) {
        this.tfTextArea.append("\n"+msg);
    }
}
