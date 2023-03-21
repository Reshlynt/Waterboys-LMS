package src;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  private double CID;
  private String post;
  private UUID postingUser;
  private int likes;
  private int dislikes;
  private ArrayList<Comment> replies;

  public Comment(String post, UUID postingUser, ArrayList<Comment> replies) {
    this.post = post;
    this.postingUser = postingUser;
    this.replies = replies;
  }
  // Recursively prints all comments
  public void printComments(){
    // if (user's age is 13+)
    // return null
    for(int i = 0; i<replies.size();i++){
      System.out.println(replies.get(i));
      for (int j = 0; j < replies.get(i).getReplies().size(); j++) {
        replies.get(i).getReplies().get(j).printComments();
      }
    }
    return;
  }

  public String toString() {
    String info =  "UUID: " + postingUser.toString() + " post: " + post +"\n";
    if(replies!=null){
      info+="replies\n";
      for(int i = 0; i< replies.size();i++){
        info += replies.get(i).toString();
      }
    }
    return info;
  }

  public void replyToComment() {
    Comment comment = new Comment("comment", postingUser, replies);
    comment.addPost();
  }

  public void addPost() {
    Comment comment = new Comment("comment", postingUser, null);
    Course.addComment(comment);
  }

  public void likeDislikePost(String entry) {
    if(entry.equals("likes")) {
      likes++;
    }
    else if(entry.equals("dislike")) {
      dislikes++;
    }
  }

  // create a method that 
  public void removePost() {
    
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

  // Returns the comment's repiles
  public ArrayList<Comment> getReplies() {
    return replies;
  }
}
