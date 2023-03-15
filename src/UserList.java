
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static ArrayList<User> userList;

    private UserList() {}
    /*
     * Singleton pattern. Only one instance of UserList can be created.
     */
    public static UserList getInstance() {
        if ( userList == null)  {
            userList = new ArrayList<User>();
        }
        return new UserList();
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

    public User getUserByUUID(UUID id) {
        for (User user : userList) {
            if (user.equals(id)) {
                return user;
            }
        }
        return null;
    }
    

}
