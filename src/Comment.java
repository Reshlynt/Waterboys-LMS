
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  private String post;
  private UUID postingUser;
  private int likes;
  private int dislikes;
  private ArrayList<Comment> comments;

  public Comment(String post, UUID postingUser, ArrayList<Comment> comments) {
    this.post = post;
    this.postingUser = postingUser;
    this.comments = comments;
  }
  // Recursively prints all comments
  public void printComments(){
    for(int i = 0; i<comments.size();i++){
      System.out.println(comments.get(i));
      for (int j = 0; j < comments.get(i).getComments().size(); j++) {
        comments.get(i).getComments().get(j).printComments();
      }
    }
  }

  public String toString() {
    return "UUID: " + postingUser.toString() + " post: " + post;
  }

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

  // returns the comment's post
  public String getPost() {
    return post;
  }

  // Returns the comment's posting user id
  public UUID getPostingUser() {
    return postingUser;
  }

  // Returns the comment's likes
  public int getLikes() {
    return likes;
  }

  // Returns the comment's dislikes
  public int getDislikes() {
    return dislikes;
  }

  // Returns the comment's comments
  public ArrayList<Comment> getComments() {
    return comments;
  }
}
