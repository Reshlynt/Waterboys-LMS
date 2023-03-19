import java.util.ArrayList;

public class Module {
  private String title;
  private ArrayList<TextSlide> slides;
  private ArrayList<Comment> comments;
  private Assessment lessonQuiz;
  // Ask portia if we should add Comment IDs for replying
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
    String info = title +"\n";
    for (int i = 0; i < slides.size(); i++) {
      info += (slides.get(i) + "\n");
    }
    for(int i = 0; i<comments.size();i++){
      info += (comments.get(i) + "\n");
    }
    info+=lessonQuiz;
    return info;
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
  public void printComments(Comment comment){
    for(int i = 0; i<comments.size();i++){
      System.out.println(comments.get(i));
      for (int j = 0; j < comments.get(i).getComments().size(); j++) {
        printComments(comments.get(i).getComments().get(j));
      }
    }
  }
  // returns the module's quiz
  public Assessment getQuiz() {
    return lessonQuiz;
  }

}
