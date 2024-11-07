package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class RemoveRoomForm extends JPanel {
    private JPanel RemovePanel;
    private JLabel lTitle;
    private JLabel lRoomName;
    private JTextField tfRoomName;
    private JButton btnRemove;
    private JButton btnCancel;

    public RemoveRoomForm(ChatInterface chatInterface, StartedForm dashboard){
        RemovePanel = new JPanel();
        setLayout(new BorderLayout());
        add(RemovePanel, BorderLayout.CENTER);

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = tfRoomName.getText();
                if(roomName.isEmpty()){
                    JOptionPane.showMessageDialog(RemovePanel, "please enter room name"
                            ,"Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = new Result();

                    result = chatInterface.deleteChatRoom(roomName, SharedVariables.getUser());
                    if(result.isSuccess()){
                        JOptionPane.showMessageDialog(RemovePanel, result.getDescription()
                                ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        dashboard.cardLayout.show(dashboard.mainPanel, "MyHome"); // Switch to MyHome

//                        home.setVisible(true);

                    }
                    else{
                        JOptionPane.showMessageDialog(RemovePanel, result.getDescription()
                                ,"Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.cardLayout.show(dashboard.mainPanel, "MyHome"); // Switch to MyHome


            }
        });
        setVisible(true);
    }
}
