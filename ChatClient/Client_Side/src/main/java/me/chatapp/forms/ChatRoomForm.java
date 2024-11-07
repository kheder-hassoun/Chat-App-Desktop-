package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;
import me.chatapp.ChatClientImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ChatRoomForm extends JDialog{
    private JPanel roomPanel;
    private JButton addUserButton;
    private JButton showAllUsersButton;
    private JButton startChatButton;
    private JButton removeUserButton;
    private JButton cancelButton;
    private JButton leaveRoomButton;

    public ChatRoomForm(ChatInterface chatInterface,String roomName) {

        setTitle("Room dashboard");
        setContentPane(roomPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  dispose();
                  AddUserToRoom addUserToRoom = new AddUserToRoom(chatInterface,roomName);
            }
        });
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RemoveUserFromRoom removeUserFromRoom = new RemoveUserFromRoom(chatInterface,roomName);
            }
        });
        showAllUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShowAllRoomUsers showAllRoomUsers = new ShowAllRoomUsers(chatInterface,roomName);
            }
        });
        startChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PublicChatForm publicChatForm = new PublicChatForm(chatInterface, roomName);
                try {
                    ChatClientImpl chatClient = new ChatClientImpl(publicChatForm);
                    chatInterface.setClientChatInterfaceForUsers(chatClient,roomName, SharedVariables.getUser().getUserName());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeForm homeForm = new HomeForm(chatInterface);
            }
        });
        leaveRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result result = new Result();
                try {
                    result =  chatInterface.leaveRoom(SharedVariables.getUser().getUserName(), roomName);
                    if(result.isSuccess()){
                        JOptionPane.showMessageDialog(roomPanel, result.getDescription()
                                ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        HomeForm home = new HomeForm(chatInterface);
                    }
                    else{
                        JOptionPane.showMessageDialog(roomPanel, result.getDescription()
                                ,"Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
