package src;

import java.util.ArrayList;

public class Comment {
  private double CID;
  private String post;
  private User postingUser;
  private ArrayList<Comment> replies = new ArrayList<Comment>();

  /**
   * Constructor
   * 
   * @param post        posted comment
   * @param postingUser person who posted it
   */
  public Comment(String post, User postingUser) {
    this.post = post;
    this.postingUser = postingUser;
  }

  /**
   * Constructor
   * 
   * @param post        posted comment
   * @param postingUser person who posted it
   * @param replies     the replies
   */
  public Comment(String post, User postingUser, ArrayList<Comment> replies) {
    this.post = post;
    this.postingUser = postingUser;
    this.replies = replies;
  }

  /**
   * Recursively prints the replies
   */
  public void printComments() {
    if (replies == null)
      return;
    for (int i = 0; i < replies.size(); i++) {
      System.out.println(replies.get(i));
      for (int j = 0; j < replies.get(i).getReplies().size(); j++) {
        replies.get(i).getReplies().get(j).printComments();
      }
    }
  }

  public String toString() {
    String info = +CID + " UUID: " + postingUser.toString() + " post: " + post + "\n";
    if (replies != null) {
      info += "replies\n";
      for (int i = 0; i < replies.size(); i++) {
        info += replies.get(i).toString();
      }
    }
    return info;
  }

  public void replyToComment(String post, User posting_user) {
    Comment comment = new Comment(post, posting_user, null);
    comment.CID = this.CID + .1;
    this.replies.add(comment);
  }

  /**
   * Getter for the post
   * 
   * @return string that shows the post
   */
  public String getPost() {
    return post;
  }

  /**
   * Returns the comment's posting user
   * 
   * @return User object that posted the comment
   */
  public User getPostingUser() {
    return postingUser;
  }

  /**
   * Getter for replies
   * 
   * @return arraylist of type Comment
   */
  public ArrayList<Comment> getReplies() {
    return replies;
  }
}
