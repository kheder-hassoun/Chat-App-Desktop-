package me.chatapp;

import SharedInterface.ChatInterface;
import me.chatapp.forms.ChatRoomForm;
import me.chatapp.forms.ShowRoomsForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemPanel extends JPanel {
    private static final int PANEL_HEIGHT = 30;
    private String itemName;
    private JLabel nameLabel;

    public ItemPanel(String itemName, ChatInterface chatInterface) {
        this.itemName = itemName;

        // Set layout and preferred size
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, PANEL_HEIGHT));

        // Set background color for dark mode
        setBackground(new Color(45, 45, 45));  // Dark gray
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Initialize and style the label
        nameLabel = new JLabel(itemName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);  // White text
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(nameLabel, BorderLayout.WEST);

        // Add hover effect
        addMouseListener(new HoverEffectAdapter());
        addMouseListener(new ItemClickListener(chatInterface, itemName));
    }

    // Inner class for handling item clicks
    private class ItemClickListener extends MouseAdapter {
        ChatInterface chatInterface;
        String roomName;

        public ItemClickListener(ChatInterface chatInterface, String roomName) {
            this.chatInterface = chatInterface;
            this.roomName = roomName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ShowRoomsForm showRoomsForm = (ShowRoomsForm) SwingUtilities.getWindowAncestor(ItemPanel.this);
            showRoomsForm.dispose();
            ChatRoomForm chatRoomForm = new ChatRoomForm(chatInterface, roomName);
        }
    }

    // Inner class for adding hover effect
    private class HoverEffectAdapter extends MouseAdapter {
        Color hoverColor = new Color(60, 60, 60);  // Slightly lighter dark gray
        Color defaultColor = getBackground();

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(defaultColor);
        }
    }
}
