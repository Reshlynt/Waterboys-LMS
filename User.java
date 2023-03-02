/**
 * Represents a user of the system
 * @author Waterboys
 */
public abstract class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String DOB;
    
    public User(String username, String firstName, String lastName, String email, String password, String DOB) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }
    private boolean checkPassword(String password) {
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
    }
    private boolean checkUser(String username) {
        if (username.length() < 5 || username.length() > 14) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == ' ' || username.charAt(i) == '\t' || username.charAt(i) == '\n') {
                return false;
            }
        }
    }
    public void changePassword(String newPassword) {
        if (checkPassword(newPassword)) {
            password = newPassword;
        }
    }
}
 