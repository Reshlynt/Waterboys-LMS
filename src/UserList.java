package src;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static ArrayList<User> userList;
    private static UserList singletonUserList;

    private UserList() {
        userList = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if (singletonUserList == null)
            singletonUserList = new UserList();
        return singletonUserList;
    }

    /**
    * 
    * @return Instance of the UserList object.
    */

    public void addUser(User user) {
        userList.add(user);
    }
  
    public void deleteUser(User user) {
        userList.remove(user);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User getUserByUUID(UUID id) {
        for (User user : userList) {
            if (user.equals(id)) {
                return user;
            }
        }
        return null;
    }

}
