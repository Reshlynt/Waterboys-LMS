package src;
import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private static ArrayList<User> userList = new ArrayList<User>();

    private UserList() {
    }

    /**
     * 
     * @return Instance of the UserList object.
     */
    public static UserList getInstance() {

        return userList;
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

    public found(String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    

}
