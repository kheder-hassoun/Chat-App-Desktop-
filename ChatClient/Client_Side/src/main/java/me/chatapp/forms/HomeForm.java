package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class HomeForm extends JDialog {
    private JPanel HomePanel;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnShow;
    private JButton btnLogout;
    private JButton allUsersButton;

    public HomeForm(ChatInterface chatInterface){
        setTitle("Home");
        setContentPane(HomePanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
               // AddRoomForm addRoomForm = new AddRoomForm(chatInterface);

            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
               // RemoveRoomForm removeRoomForm = new RemoveRoomForm(chatInterface);

            }
        });
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    ShowRoomsForm show = new ShowRoomsForm(chatInterface);
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

                    dispose();
                  //  LoginForm home = new LoginForm(chatInterface);

                }
                else {
                    JOptionPane.showMessageDialog(HomePanel, result.getDescription()
                            ,"Failed", JOptionPane.ERROR_MESSAGE);
                }



            }
        });

        allUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    ShowAllUsersForm showAllUsersForm = new ShowAllUsersForm(chatInterface);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }


}
