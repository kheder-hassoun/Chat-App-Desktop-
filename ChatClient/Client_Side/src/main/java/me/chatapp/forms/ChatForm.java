package me.chatapp.forms;

import javax.swing.*;

public abstract class ChatForm extends JDialog {
    public abstract void setTextAreaRecievedMessages(String msg);
}
