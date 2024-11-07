package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class MyHomeForm extends JPanel {
    private JPanel HomePanel;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnShow;
    private JButton btnLogout;
    private JButton allUsersButton;

    public MyHomeForm(ChatInterface chatInterface, StartedForm dashboard){
        // Initialize the components and layout
        HomePanel = new JPanel();
        setLayout(new BorderLayout());
        add(HomePanel, BorderLayout.CENTER);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.cardLayout.show(dashboard.mainPanel, "AddRoom"); // Switch to AddRoom

            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.cardLayout.show(dashboard.mainPanel, "RemoveRoom"); // Switch to RemoveRoom

            }
        });

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  dashboard.cardLayout.show(dashboard.mainPanel, "ShowRooms"); // Switch to ShowRooms


                try {
                    ShowRoomsForm show = new ShowRoomsForm(chatInterface);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
        allUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ShowAllUsersForm showAllUsersForm = new ShowAllUsersForm(chatInterface);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result result = new Result();
                try {
                    result =  chatInterface.logoutFromSystem(SharedVariables.getUser().getUserName());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                if(result.isSuccess()){
                    JOptionPane.showMessageDialog(HomePanel, result.getDescription()
                            ,"Success", JOptionPane.INFORMATION_MESSAGE);

                    dashboard.cardLayout.show(dashboard.mainPanel, "Dashboard"); // Switch to Dashboard


                }
                else {
                    JOptionPane.showMessageDialog(HomePanel, result.getDescription()
                            ,"Failed", JOptionPane.ERROR_MESSAGE);
                }



            }
        });
        setVisible(true);
    }


}
