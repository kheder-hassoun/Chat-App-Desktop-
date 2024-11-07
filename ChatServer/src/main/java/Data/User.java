package Data;

import SharedInterface.IClientChat;

import java.io.Serializable;

public class User implements Serializable {
    private IClientChat clientChat;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    public User(String userName, String firstName, String lastName, String password, IClientChat clientChat) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.clientChat = clientChat;
    }
    public User() {
        this.userName = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
        this.clientChat = null;
    }
    public String getUserName() {
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IClientChat getClientChat() {
        return clientChat;
    }

    public void setClientChat(IClientChat clientChat) {
        this.clientChat = clientChat;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
