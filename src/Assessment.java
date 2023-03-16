
import java.util.ArrayList;

public class Assessment extends Slide {
  private String label;
  private ArrayList<Question> questions;
  private String correctAnswers;
  private String inputtedAnswers;
  private int score;
  private Type type;

  public Assessment(String label, ArrayList<Question> questions, Type type) {
    this.label = label;
    this.questions = questions;
    this.type = type;
  }

  public void display() {
    return;
  }

  public int getScore() {
    return 0;
  }

  public String getLabel() {
    return "";
  }

  // Return the correct answers
  public String getCorrectAnswers() {
    return correctAnswers;
  }

  // Return the inputted answers
  public String getInputtedAnswers() {
    return inputtedAnswers;
  }

}
