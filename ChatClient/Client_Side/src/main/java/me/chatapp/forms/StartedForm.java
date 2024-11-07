package me.chatapp.forms;

import SharedInterface.ChatInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class StartedForm extends JFrame {
    private JPanel DashPanel;
    private JLabel lAdmin;
    private JButton btnRegister;
    private JButton btnLogin;
    public CardLayout cardLayout;
    public JPanel mainPanel;

    public StartedForm(ChatInterface chatInterface) throws RemoteException {
        setTitle("Dashboard");
        setMinimumSize(new Dimension(500, 650));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Add dashboard panel
        mainPanel.add(DashPanel, "Dashboard");

        // Create and add the registration form panel
        RegistrationForm registerPanel = new RegistrationForm(chatInterface, this);
        mainPanel.add(registerPanel, "Registration");

        // Create and add the login form panel
        LoginForm loginPanel = new LoginForm(chatInterface, this);
        mainPanel.add(loginPanel, "Login");

        MyHomeForm homePanel = new MyHomeForm(chatInterface, this);
        mainPanel.add(homePanel, "MyHome");
        AddRoomForm addRoomPanel = new AddRoomForm(chatInterface, this);
        mainPanel.add(addRoomPanel, "AddRoom");

        RemoveRoomForm removeRoomPanel = new RemoveRoomForm(chatInterface, this);
        mainPanel.add(removeRoomPanel, "RemoveRoom");

//        ShowRoomsForm showRoomsPanel = new ShowRoomsForm(chatInterface, this);
//        mainPanel.add(showRoomsPanel, "ShowRooms");


        setContentPane(mainPanel);
        setVisible(true);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Registration");
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });
    }
}
