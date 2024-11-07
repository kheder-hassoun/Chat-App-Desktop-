package me.chatapp.forms;

import Data.User;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;
import me.chatapp.UserItemPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class ShowAllUsersForm extends JDialog{
    private JScrollPane scroll;
    private JPanel showPanel;
    private JButton backButton;

    private static final int SCROLL_PANE_HEIGHT = 500;

    public ShowAllUsersForm(ChatInterface chatInterface)  throws RemoteException {
        setTitle("Show all rooms");
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        List<User> users = chatInterface.getAllUsers(SharedVariables.getUser().getUserName());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserItemPanel itemPanel = new UserItemPanel(user.getUserName(), chatInterface);

            if (i != 0) {
                showPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between items
                showPanel.add(new JSeparator(SwingConstants.HORIZONTAL)); // Add horizontal line between items
            }
            showPanel.add(itemPanel);
        }
        scroll = new JScrollPane(showPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(450, SCROLL_PANE_HEIGHT));
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scroll, BorderLayout.CENTER);
        setContentPane(contentPane);
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeForm chatRoomForm = new HomeForm(chatInterface);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        scroll.getVerticalScrollBar().setValue(0);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
