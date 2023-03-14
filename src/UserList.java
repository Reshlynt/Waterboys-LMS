package src;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static ArrayList<User> userList = new ArrayList<User>();

    private UserList() {
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public boolean found(UUID id) {
        for (User user : userList) {
            if (user.getUserByUUID(id)) {
                return true;
            }
        }
        return false;
    }
    

}
