package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;

public class AddRoomForm extends JPanel{
    private JPanel AddRoomPanel;
    private JLabel lTitle;
    private JTextField tfRoomName;
    private JLabel lName;
    private JButton btnAdd;
    private JButton btnCancel;
  //  private JButton button1;

    public AddRoomForm(ChatInterface chatInterface , StartedForm dashboard){
        AddRoomPanel = new JPanel();
        setLayout(new BorderLayout());
        add(AddRoomPanel, BorderLayout.CENTER);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = tfRoomName.getText();
                if(roomName.isEmpty()){
                    JOptionPane.showMessageDialog(AddRoomPanel, "please enter room name"
                            ,"Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = new Result();

                    result = chatInterface.addChatRoom(roomName, SharedVariables.user);
                    if(result.isSuccess()){
                        JOptionPane.showMessageDialog(AddRoomPanel, result.getDescription()
                                ,"Success", JOptionPane.INFORMATION_MESSAGE);
                        dashboard.cardLayout.show(dashboard.mainPanel, "MyHome"); // Switch to MyHome

                    }
                    else{
                        JOptionPane.showMessageDialog(AddRoomPanel, result.getDescription()
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

        btnCancel.addMouseListener(new MouseAdapter() {
        });

//        button1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }
//        });
    }

}
