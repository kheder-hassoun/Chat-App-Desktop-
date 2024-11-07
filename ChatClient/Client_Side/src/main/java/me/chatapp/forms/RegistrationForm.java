package me.chatapp.forms;

import Data.Result;
import SharedInterface.ChatInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class RegistrationForm extends JPanel {
    private JPasswordField tfPass;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel RegisterPanel;
    private JTextField tfLastname;
    private JLabel lLastname;
    private JTextField tfFirstname;
    private JTextField tfUsername;
    private JLabel lUsername;
    private JLabel lFirstname;
    private JLabel lPassword;
    private JLabel lTitle;

    public RegistrationForm(ChatInterface chatInterface, StartedForm dashboard) {
        // Initialize the components and layout
        RegisterPanel = new JPanel();
        setLayout(new BorderLayout());
        add(RegisterPanel, BorderLayout.CENTER);

        // (initialize your UI components here like tfPass, tfLastname, etc.)

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String lastname = tfLastname.getText();
                String firstname = tfFirstname.getText();
                String password = String.valueOf(tfPass.getPassword());
                if (username.isEmpty() || lastname.isEmpty() || firstname.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterPanel, "Please enter all fields",
                            "Try Again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Result result = chatInterface.registerUser(username, password, firstname, lastname, null);
                    if (result.isSuccess()) {
                        JOptionPane.showMessageDialog(RegisterPanel, result.getDescription(),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dashboard.cardLayout.show(dashboard.mainPanel, "Login"); // Switch to login
                    } else {
                        JOptionPane.showMessageDialog(RegisterPanel, result.getDescription(),
                                "Error", JOptionPane.ERROR_MESSAGE);
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
