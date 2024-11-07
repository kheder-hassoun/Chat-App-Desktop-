package Data;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private String roomName;
    private List<User> users;
    private User owner;
    private List<String> messages;
    private List<User> loggedInUsers;



    public Room(String roomName, List<User> users, User owner,List<String> messages) {
        this.roomName = roomName;
        this.users = users;
        this.owner = owner;
        this.messages = messages;
        this.loggedInUsers = new ArrayList<>();

    }

    public List<String> getMessages() {
        return messages;
    }

    public List<User> getLoggedInUsers() {
        return loggedInUsers;
    }

    public void setLoggedInUsers(List<User> loggedInUsers) {
        this.loggedInUsers = loggedInUsers;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Room() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Room(String roomName, User owner) {
        this.roomName = roomName;
        this.owner = owner;
        this.users = new ArrayList<>();
    }

    public String getRoomName(){
        return roomName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
