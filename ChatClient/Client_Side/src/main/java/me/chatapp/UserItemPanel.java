package me.chatapp;

import SharedInterface.ChatInterface;
import Singleton.SharedVariables;
import me.chatapp.forms.PrivateChatForm;
import me.chatapp.forms.ShowAllUsersForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class UserItemPanel extends JPanel {
    private static final int PANEL_HEIGHT = 30;
    private String itemName;
    private JLabel nameLabel;

    public UserItemPanel(String itemName, ChatInterface chatInterface) {
        this.itemName = itemName;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, PANEL_HEIGHT));

        nameLabel = new JLabel(itemName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        add(nameLabel, BorderLayout.WEST);

        addMouseListener(new ItemClickListener(chatInterface,itemName));
    }

    private class ItemClickListener extends MouseAdapter {
        ChatInterface chatInterface;
        String userName;

        public ItemClickListener(ChatInterface chatInterface, String userName) {
            this.chatInterface = chatInterface;
            this.userName = userName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ShowAllUsersForm showForm = (ShowAllUsersForm) SwingUtilities.getWindowAncestor(UserItemPanel.this);
            showForm.dispose();
            PrivateChatForm privateChatForm = new PrivateChatForm(chatInterface, userName);
            try {
                ChatClientImpl chatClient = new ChatClientImpl(privateChatForm);
                chatInterface.setClientChatInterfaceForUser(chatClient, SharedVariables.getUser().getUserName());
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}