package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class LoginForm extends JPanel {
    private JLabel lUserName;
    private JTextField tfUserName;
    private JLabel lPassword;
    private JPasswordField tfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel LoginPanel;

    public LoginForm(ChatInterface chatInterface, StartedForm dashboard) {
        // Initialize components and layout
        LoginPanel = new JPanel();
        setLayout(new BorderLayout());
        add(LoginPanel, BorderLayout.CENTER);

        // (initialize your UI components here like lUserName, tfUserName, btnOK, etc.)

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUserName.getText();
                String password = String.valueOf(tfPassword.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPanel, "Please enter all fields",
                            "Try Again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = chatInterface.loginToSystem(username, password);
                    if (result.isSuccess()) {
                        JOptionPane.showMessageDialog(LoginPanel, result.getDescription(),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        SharedVariables.setUser(chatInterface.getUserInfo(username));
                       // dispose();
                       // HomeForm home = new HomeForm(chatInterface);
                        dashboard.cardLayout.show(dashboard.mainPanel, "MyHome"); // Switch to home form
                    } else {
                        JOptionPane.showMessageDialog(LoginPanel, result.getDescription(),
                                "Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboard.cardLayout.show(dashboard.mainPanel, "Dashboard"); // Switch back to dashboard
            }
        });
    }
}
