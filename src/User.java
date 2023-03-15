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
    public User(String username, String firstName, String lastName, String email, String password, Date DOB) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }
    public boolean setPassword(String pass) {
        if (pass.length() < 8) {
            return false;
        }
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == ' ' || pass.charAt(i) == '\t' || pass.charAt(i) == '\n') {
                return false;
            }
        }
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') {
                password = pass;
                return true;
            }
        }
        return false;
    }
    public boolean setUser(String user) {
        if (user.length() < 5 || user.length() > 14) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (user.charAt(i) == ' ' || user.charAt(i) == '\t' || user.charAt(i) == '\n' || user.charAt(i) == '@' || user.charAt(i) == '\\' || user.charAt(i) == '%') {
                return false;
            }
        }
        this.username = user;
        return true;
    }
    public String toString() {
        return "User: " + id + " " + username + " " + firstName + " " + lastName + " " + email + " " + password + " " + DOB;
    }
}
