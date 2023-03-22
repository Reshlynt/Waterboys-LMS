package src;
import java.util.ArrayList;

public class Module {
  private String title;
  private ArrayList<TextSlide> slides;
  private ArrayList<Comment> comments;
  private Assessment lessonQuiz;

  public Module(String title, ArrayList<TextSlide> slides, ArrayList<Comment> comments, Assessment lessonQuiz) {
    this.title = title;
    this.slides = slides;
    this.comments = comments;
    this.lessonQuiz = lessonQuiz;
  }

  public void displaySlides() {
    for (int i = 0; i < slides.size(); i++) {
      slides.get(i).display();
    }
    return;
  }

  public String toString() {
    String info = title + "\n";
    info += "module_slides";
    for (int i = 0; i < slides.size(); i++) {
      info += (slides.get(i) + "\n");
    }
    info += "module_comments:\n";
    for (int i = 0; i < comments.size(); i++) {
      info += (comments.get(i) + "\n");
    }
    info += "lessonsquiz:\n";
    info += lessonQuiz;
    return info;
  }
  public void addComment(Comment comment) {
    comments.add(comment);
  }

  // returns the module's title
  public String getTitle() {
    return title;
  }

  // returns the module's slides
  public ArrayList<TextSlide> getSlides() {
    return slides;
  }

  // returns the module's comments
  public ArrayList<Comment> getComments() {
    return comments;
  }

  // returns the module's quiz
  public Assessment getQuiz() {
    return lessonQuiz;
  }

}
