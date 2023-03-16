import java.util.ArrayList;

public class Module {
    private String title;
    private ArrayList<Slide> slides;
    private ArrayList<Comment> comments;
    private Assessment lessonQuiz;

    public Module(String title, ArrayList<Slide> slides, ArrayList<Comment> comments, Assessment lessonQuiz){
      this.title = title;
      this.slides = slides;
      this.comments = comments;
      this.lessonQuiz = lessonQuiz;
    }
    public void present() {
        return;
    }
}
