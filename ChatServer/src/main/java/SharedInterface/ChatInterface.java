package SharedInterface;

import Data.Message;
import Data.Result;
import Data.Room;
import Data.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatInterface extends Remote {
    Result addChatRoom(String roomName, User owner) throws RemoteException;
    Result deleteChatRoom(String roomName, User owner) throws RemoteException;
    Result registerUser(String userName, String password, String firstName, String secondName, IClientChat clientChat) throws RemoteException;
    Result loginToSystem(String userName, String password) throws RemoteException;
    Result login(String userName, String password, String roomName) throws RemoteException;
    Result logout(String userName, String roomName) throws RemoteException;
    Result leaveRoom(String userName, String roomName) throws RemoteException;
    List<Room> getAvailableChatRooms(User user) throws RemoteException;
    List<User> getClientsInChatRoom(String roomName) throws RemoteException;
    void sendMessageToClient(String sender, String receiver, String message) throws RemoteException;
    void sendMessageToRoom(String sender, String roomName, String message) throws RemoteException;
    User getUserInfo(String username) throws RemoteException;
    Room getRoomByName(String roomName) throws RemoteException;
    List<Message> getChatMessages(String senderName, String receiverName) throws RemoteException;
    Result addUserToRoom(String roomName,String userName, User owner) throws RemoteException;
    Result removeUserFromRoom(String roomName,String userName, User owner) throws RemoteException;
    void setClientChatInterfaceForUsers(IClientChat clientChat , String roomName, String userName) throws RemoteException;
    void setClientChatInterfaceForUser(IClientChat clientChat, String user) throws RemoteException;
    void setLoggedInUsersForRoom(String roomName, String user) throws RemoteException;

    Result logoutFromSystem(String userName) throws RemoteException;
    List<User> getAllUsers(String userName) throws RemoteException;





}
