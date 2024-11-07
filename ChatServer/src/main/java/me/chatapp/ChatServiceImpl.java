package me.chatapp;
import Data.Message;
import Data.Result;
import Data.Room;
import Data.User;
import SharedInterface.ChatInterface;
import SharedInterface.IClientChat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatInterface {
    private List<Room> chatRooms;
    private List<User> registeredUsers;
    private List<User> loggedInUsers;
    private List<Message> messages;

    protected ChatServiceImpl() throws RemoteException {
        super();
        chatRooms = new ArrayList<>();
        registeredUsers = new ArrayList<>();
        loggedInUsers = new ArrayList<>();
    }

    @Override
    public Result addChatRoom(String roomName, User owner) throws RemoteException {
      Result result = new Result();
      for(Room room : chatRooms){
          if(room.getRoomName().equals(roomName)){
              result.setDescription("Chat room "+roomName+" is already exist");
              result.setSuccess(false);
              return result;
          }
      }

        Room room = new Room(roomName,owner);
        room.getUsers().add(owner);
        System.out.println("user: "+owner.getUserName()+" "+owner.getFirstName()+" add chat room named: "+roomName);
        chatRooms.add(room);
        for (Room room1 : chatRooms){
            System.out.println("Room name: "+room1.getRoomName()+" the owner is:" + owner.getUserName());
        }
        result.setDescription("Chat room "+roomName+" is created successfully");
        result.setSuccess(true);
        return result;

    }

    @Override
    public Result deleteChatRoom(String roomName,User owner ) throws RemoteException {
        Result result = new Result();
        for(Room room : chatRooms){
            if(room.getRoomName().equals(roomName)){
                if(room.getOwner().getUserName().equals(owner.getUserName())&&room.getOwner().getPassword().equals(owner.getPassword())){
                    chatRooms.remove(room);
                    result.setDescription("Chat room "+roomName+" is deleted successfully");
                    result.setSuccess(true);
                }
                else{
                    result.setDescription("Permission denied!!!");
                    result.setSuccess(false);
                }
                return result;
            }

        }
        result.setDescription("Chat room "+roomName+" does not exist");
        result.setSuccess(false);
        return result;
    }

    @Override
    public Result registerUser(String userName, String password, String firstName, String secondName, IClientChat clientChat) throws RemoteException {
        System.out.println("there is user want to register");
        Result result = new Result();
        for(User user : registeredUsers){
          if(user.getUserName().equals(userName)) {
              result.setSuccess(false);
              result.setDescription("UserName is already exist");
              return result;
          }
        }
        User newuser = new User(userName,firstName,secondName,password,clientChat);
        registeredUsers.add(newuser);
        result.setSuccess(true);
        result.setDescription("registration done successfully");
        System.out.println("Done");
        for(User user: registeredUsers){
            System.out.println(user.getUserName()+"  "+user.getPassword()+ "  "+user.getFirstName()+"  "+user.getLastName());
        }

        return result;
    }

    @Override
    public Result loginToSystem(String userName, String password) throws RemoteException {
        Result result = new Result();
        for(User user : registeredUsers){
            if(user.getUserName().equals(userName)){
                if(user.getPassword().equals(password)){
                    loggedInUsers.add(user);
                    result.setSuccess(true);
                    result.setDescription("log in done successfully");
                }
                else{
                    result.setSuccess(false);
                    result.setDescription("user name and password does not match !!!");
                }
                return result;
            }
        }
        result.setSuccess(false);
        result.setDescription("user name does not exist");
        return result;
    }

    @Override
    public Result login(String userName, String password, String roomName) throws RemoteException {
       Result result = new Result();

       for(Room room : chatRooms){
           if(room.getRoomName()==roomName){
               for (User user : room.getUsers()){
                   if(user.getUserName() == userName && user.getPassword() == password){
                       result.setDescription("Logged in successfully");
                       result.setSuccess(true);
                       return result;
                   }
               }
               result.setDescription("Permission denied !!!");
               result.setSuccess(false);
               return result;

           }

       }
        result.setDescription("Room name does not exist !!!");
        result.setSuccess(false);
        return result;



    }

    @Override
    public Result logout(String userName, String roomName) throws RemoteException {
        Result result = new Result();

        for(Room room : chatRooms){
            if(room.getRoomName() == roomName){
                for(User user : room.getUsers()){
                    if(user.getUserName() == userName){
                        room.getUsers().remove(user);
                        result.setDescription("logout done successfully");
                        result.setSuccess(true);
                    }
                    else{
                        result.setDescription("User name does not exist in this room !!!");
                        result.setSuccess(false);
                    }
                    return result;
                }

            }
        }
        result.setDescription("Room name does not exist!!!");
        result.setSuccess(false);
        return result;


    }

    @Override
    public Result leaveRoom(String userName, String roomName) throws RemoteException {
        Result result = new Result();
        for (Room room : chatRooms){
            if(room.getRoomName().equals(roomName)){
                for (User usr : room.getUsers()){
                    if(usr.getUserName().equals(userName)){
                        room.getUsers().remove(usr);
                        result.setDescription("you left this room");
                        result.setSuccess(true);
                        return result;
                    }
                }
                result.setDescription("you are not in this room");
                result.setSuccess(false);
                return result;
            }
        }
        result.setDescription("room not found");
        result.setSuccess(false);
        return result;    }

    @Override
    public List<Room> getAvailableChatRooms(User user) throws RemoteException {
        List<Room>  availableRooms= new ArrayList<Room>();
        for (Room room : chatRooms){
            if(room.getOwner().getUserName().equals(user.getUserName())){
                availableRooms.add(room);
            }
            else{
                for (User usr : room.getUsers()){
                    if(usr.getUserName().equals(user.getUserName())){
                        availableRooms.add(room);
                    }
                }
            }
        }


        return availableRooms;
    }

    @Override
    public List<User> getClientsInChatRoom(String roomName) throws RemoteException {
        for(Room room : chatRooms) {
            if(room.getRoomName().equals(roomName)){
                return room.getUsers();
            }
        }
        return null;
    }

    @Override
    public void sendMessageToClient(String sender, String receiver, String message) throws RemoteException {
        List<User> users = new ArrayList<>();
        if(messages == null){
            messages = new ArrayList<>();
        }
        Message msg = new Message();
        msg.setDetails(sender+" : "+message);
        msg.setReceiver(receiver);
        msg.setSender(sender);
        messages.add(msg);
        for (User usr : registeredUsers) {
            if(usr.getUserName().equals(receiver) || usr.getUserName().equals(sender)){
               users.add(usr);
            }
        }
        List<User> deadUsers = new ArrayList<>(users.stream()
                .filter(e -> {
                    try {
                        if(e.getClientChat() != null){
                            e.getClientChat().receiveMessage(message, sender);
                            return false;
                        }
                        else {
                            return true;
                        }
                    } catch (RemoteException ex) {
                        return true;
                    }
                })
                .collect(Collectors.toList()));

        users.removeAll(deadUsers);

    }

    @Override
    public void sendMessageToRoom(String sender, String roomName, String message) throws RemoteException {

        Room rm = new Room();
        for(Room room : chatRooms){
            if(room.getRoomName().equals(roomName)){
                rm = room;
            }
        }
        List<String> messages;
        messages= rm.getMessages();
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(sender+" : "+message);
        rm.setMessages(messages);
        List<User> deadUsers = new ArrayList<>(rm.getLoggedInUsers().stream()
                .filter(e -> {
                    try {
                        e.getClientChat().receiveMessage(message, sender);
                        return false;
                    } catch (RemoteException ex) {

                        return true;

                    }
                })
                .collect(Collectors.toList()));
        rm.getLoggedInUsers().removeAll(deadUsers);
    }

    @Override
    public User getUserInfo(String username) throws RemoteException {
        for (User user : registeredUsers){
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public Room getRoomByName(String roomName) throws RemoteException {
        for (Room room : chatRooms)
        {
            if(room.getRoomName().equals(roomName)){
                return room;
            }
        }
        return null;
    }

    @Override
    public List<Message> getChatMessages(String senderName, String receiverName) throws RemoteException {
        List<Message> messageList = new ArrayList<>();
        if (messages != null) {
            for (Message msg : messages) {
                if ((msg.getReceiver().equals(receiverName) || msg.getReceiver().equals(senderName) )&& (msg.getSender().equals(senderName) || msg.getSender().equals(receiverName))) {
                    messageList.add(msg);
                }
            }
        }
        return messageList;
    }

    @Override
    public Result addUserToRoom(String roomName, String userName, User owner) throws RemoteException {
        System.out.println("roomname: "+roomName+"\n userName: "+userName+"\n owner "+owner.getUserName());
        Result result = new Result();
        for(Room room : chatRooms){
            if(room.getRoomName().equals(roomName)){
                if(room.getOwner().getUserName().equals(owner.getUserName())){
                    System.out.println("1");
                    for(User usr : room.getUsers()){
                        if(usr.getUserName().equals(userName)){
                            System.out.println("1");

                            result.setDescription(userName+" already exist");
                            result.setSuccess(false);
                            return result;
                        }
                    }
                    System.out.println("3");
                    for(User user : registeredUsers){
                        if(user.getUserName().equals(userName)){
                            System.out.println("4");
                            room.getUsers().add(user);
                            result.setDescription(userName+" added successfully");
                            result.setSuccess(true);
                            return result;
                        }
                    }
                    System.out.println("5");
                    result.setDescription(userName+" not registered in the system");
                    result.setSuccess(false);
                }
                else{
                    System.out.println("6");
                    result.setDescription("Permission denied");
                    result.setSuccess(false);
                }
                return result;

            }

        }
        result.setDescription("Room not found");
        result.setSuccess(false);
        return result;

    }

    @Override
    public Result removeUserFromRoom(String roomName, String userName, User owner) throws RemoteException {
        Result result = new Result();
        for(Room room : chatRooms){
            if(room.getRoomName().equals(roomName)){
                if(room.getOwner().getUserName().equals(owner.getUserName())){
                    for(User user : room.getUsers()){
                        if(user.getUserName().equals(userName)){
                            room.getUsers().remove(user);
                            result.setDescription(userName+" added successfully");
                            result.setSuccess(true);
                            return result;

                        }
                    }
                    result.setDescription(userName+" not found in this room");
                    result.setSuccess(false);
                }
                else{
                    result.setDescription("Permission denied");
                    result.setSuccess(false);
                }
                return result;
            }

        }
        result.setDescription("Room not found");
        result.setSuccess(false);
        return result;
    }

    @Override
    public void setClientChatInterfaceForUsers(IClientChat clientChat, String roomName, String userName) throws RemoteException {

        for (Room rm : chatRooms) {
            if (rm.getRoomName().equals(roomName)) {
                for (User user : rm.getUsers()) {
                    if (user.getUserName().equals(userName)) {
                        user.setClientChat(clientChat);
                        System.out.println("1");
                        List<User> users = rm.getLoggedInUsers();
                        if (users == null) {
                            users = new ArrayList<>();
                        }
                        users.add(user);
                        rm.setLoggedInUsers(users);
                        System.out.println(users.get(0).getUserName());
                        System.out.println("2");
                    }
                }
            }
        }

    }

    @Override
    public void setClientChatInterfaceForUser(IClientChat clientChat, String user) throws RemoteException {
        for (User usr : registeredUsers) {
            if(usr.getUserName().equals(user)){
                usr.setClientChat(clientChat);
            }
        }
    }

    @Override
    public void setLoggedInUsersForRoom(String roomName, String user) throws RemoteException {
        for (Room rm : chatRooms) {
            if (rm.getRoomName().equals(roomName)) {
                for(User usr: rm.getUsers()){
                    if(usr.getUserName().equals(user)){
                        rm.getLoggedInUsers().remove(usr);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Result logoutFromSystem(String userName) throws RemoteException {
        Result result = new Result();
        for (User user : loggedInUsers){
            if(user.getUserName().equals(userName)){
                loggedInUsers.remove(user);
                result.setSuccess(true);
                result.setDescription("logout done successfully");
                return result;
            }
        }
        result.setSuccess(false);
        result.setDescription("user not found");
        return result;
    }
    @Override
    public List<User> getAllUsers(String userName) throws RemoteException {
        List<User> users = new ArrayList<>();
        for(User usr : registeredUsers){
            if(!usr.getUserName().equals(userName)){
                users.add(usr);
            }
        }
        return users;
    }

}