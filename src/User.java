package src;

/**
 * Represents a user of the system
 * @author Waterboys
 */
import java.util.UUID;
import java.util.Date;
public abstract class User {
    protected UUID id;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected String password;
    protected Date DOB;
    
    public User(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }
    public boolean setPassword() {
        if (password.length() < 8) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ' || password.charAt(i) == '\t' || password.charAt(i) == '\n') {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                return true;
            }
        }
        return false;
        return true;
    }
    public boolean setUser() {
        return true;
    }
    public void changePassword(String newPassword) {
        /*if (checkPassword(newPassword)) {
            password = newPassword;
        }*/
        return;
    }
    public boolean getUserByUUID(UUID id) {
        if (this.id == id) {
            return true;
        }
        return false;
    }
    // Returns the user's UUID
    public UUID getID() {
        return id;
    }
    // Returns the user's username
    public String getUserName() {
        return username;
    }



    /*
        userDetails.put(FIRST_NAME, user.getFirstName());

        userDetails.put(LAST_NAME, user.getLastName());

        userDetails.put(EMAIL, user.getPhoneNumber());

        userDetails.put(DOB_DATE, user.dateToString());
     * 
     * 
     * 
     */
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    // Create an accessor method that returns the user's password
    // Additionally, create an accessor method that returns the user's DOB
    public String getPassword() {
        return password;
    }
    public Date getDOB() {
        return DOB;
    }
    /*private boolean checkPassword(String password) {
        // TODO: combine checks into one loop??
        if (password.length() < 8) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == ' ' || password.charAt(i) == '\t' || password.charAt(i) == '\n') {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                return true;
            }
        }
        return false;
    }*/
    /*private boolean checkUser(String username) {
        /*if (username.length() < 5 || username.length() > 14) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == ' ' || username.charAt(i) == '\t' || username.charAt(i) == '\n') {
                return false;
            }
        }
        return true;
    }
    */
}
 