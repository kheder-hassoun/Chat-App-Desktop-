package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class RemoveUserFromRoom extends JDialog{
    private String roomName;
    private JButton removeButton;
    private JButton cancelButton;
    private JTextField tfUsername;
    private JLabel lTitle;
    private JLabel lUsername;
    private JPanel removePanel;

    public RemoveUserFromRoom(ChatInterface chatInterface, String roomName) {
        this.roomName = roomName;
        setTitle("Remove user from room");
        setContentPane(removePanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChatRoomForm dashboard = new ChatRoomForm(chatInterface,roomName);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(removePanel, "please enter user name"
                            ,"try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = new Result();
                    result = chatInterface.removeUserFromRoom(roomName,username,SharedVariables.getUser());
                    if(result.isSuccess()){
                        JOptionPane.showMessageDialog(removePanel, result.getDescription()
                                ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        ChatRoomForm chatRoomForm = new ChatRoomForm(chatInterface,roomName);

                    }
                    else{
                        JOptionPane.showMessageDialog(removePanel, result.getDescription()
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
