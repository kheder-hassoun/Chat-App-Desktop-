package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class AddUserToRoom extends JDialog {
    private String roomName;
    private JPanel addPanel;
    private JLabel lTitle;
    private JButton cancelButton;
    private JTextField tfUsername;
    private JButton addButton;
    private JLabel lUsername;

    public AddUserToRoom(ChatInterface chatInterface, String roomName) {
        this.roomName = roomName;
        setTitle("Add new user to room");
        setContentPane(addPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(addPanel, "please enter user name"
                            ,"try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = new Result();
                    result = chatInterface.addUserToRoom(roomName,username,SharedVariables.getUser());
                    if(result.isSuccess()){
                        JOptionPane.showMessageDialog(addPanel, result.getDescription()
                                ,"Success", JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        ChatRoomForm chatRoomForm = new ChatRoomForm(chatInterface,roomName);


                    }
                    else{
                        JOptionPane.showMessageDialog(addPanel, result.getDescription()
                                ,"Failed", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChatRoomForm dashboard = new ChatRoomForm(chatInterface,roomName);
            }
        });
        setVisible(true);
    }
}
