import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private String post;
    private UUID postingUser;
    private int likes;
    private int dislikes;
    private ArrayList<Comment> comments;

    public void viewPost() {
        return;
    }
    public boolean commentOnPost() {
        return true;
    }
    public boolean addPost() {
        return true;
    }
    public boolean likeDislikePost() {
        return true;
    }
    public boolean removePost() {
        return true;
    }
    public boolean checkAge() {
        return true;
    }
}