package me.chatapp.forms;

import Data.Message;
import SharedInterface.ChatInterface;
import Singleton.SharedVariables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PrivateChatForm extends ChatForm{
    private JPanel ChatPanel;
    private JTextArea tfTextArea;
    private JTextField tfWriteMessage;
    private JButton sendButton;
    private JButton backButton;
    public PrivateChatForm(ChatInterface chatInterface,String receiver){
        setTitle("Chat");
        setContentPane(ChatPanel);
        setSize(800,700);
        tfTextArea.setEditable(false);
        List<Message> messageList = new ArrayList<>();
        try {
            messageList =  chatInterface.getChatMessages(SharedVariables.getUser().getUserName(), receiver);
            if(messageList != null && !messageList.isEmpty()){
                for(Message msg : messageList){
                    setTextAreaRecievedMessages(msg.getDetails());

                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = tfWriteMessage.getText();

                if(message.isEmpty() ){
                    JOptionPane.showMessageDialog(ChatPanel, "please write message to send"
                            ,"try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    tfWriteMessage.setText("");
                    chatInterface.sendMessageToClient(SharedVariables.getUser().getUserName(),receiver,message);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeForm HomeForm = new HomeForm(chatInterface);
            }
        });
        setVisible(true);
    }


    @Override
    public void setTextAreaRecievedMessages(String msg) {
        this.tfTextArea.append("\n"+msg);
    }
}
