package me.chatapp.forms;

import Data.User;
import SharedInterface.ChatInterface;
import me.chatapp.UserRoomItemPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class ShowAllRoomUsers extends JDialog{
    private JPanel panel1;
    private JScrollPane scroll;
    private JPanel showPanel;
    private JButton backButton;
    private static final int SCROLL_PANE_HEIGHT = 500;

    public ShowAllRoomUsers(ChatInterface chatInterface, String roomName){
        setTitle("Show all rooms");
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        List<User> users = null;
        try {
            users = chatInterface.getRoomByName(roomName).getUsers();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserRoomItemPanel itemPanel = new UserRoomItemPanel(user.getUserName(), chatInterface);

            if (i != 0) {
                showPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                showPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
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
                ChatRoomForm chatRoomForm = new ChatRoomForm(chatInterface,roomName);
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
