package me.chatapp.forms;

import Data.Room;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;
import me.chatapp.ItemPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class ShowRoomsForm extends JDialog {
    private JScrollPane scroll;
    private JPanel ShowPanel;
    private JButton backButton;
    private static final int SCROLL_PANE_HEIGHT = 500;

    public ShowRoomsForm(ChatInterface chatInterface) throws RemoteException {
        setTitle("Show all rooms");
        setMinimumSize(new Dimension(500, 650));
        ShowPanel = new JPanel();
        ShowPanel.setLayout(new BoxLayout(ShowPanel, BoxLayout.Y_AXIS));
        List<Room> roomList = chatInterface.getAvailableChatRooms(SharedVariables.getUser());

        for (int i = 0; i < roomList.size(); i++) {
            Room room = roomList.get(i);
            ItemPanel itemPanel = new ItemPanel(room.getRoomName(), chatInterface);

            if (i != 0) {
                ShowPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between items
                ShowPanel.add(new JSeparator(SwingConstants.HORIZONTAL)); // Add horizontal line between items
            }

            ShowPanel.add(itemPanel);
        }

        scroll = new JScrollPane(ShowPanel);
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
                HomeForm homeForm = new HomeForm(chatInterface);
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
