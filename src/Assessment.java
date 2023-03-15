

import java.util.ArrayList;

public class Assessment extends Slide {
  private String label;
  private ArrayList<Question> questions;
  private String correctAnswers;
  private String inputtedAnswers;
  private int score;
  private Type type;

  public Assessment(String label,
      ArrayList<Question> questions, String correctAnswers,
      String inputtedAnswers, int score, Type type) {
    this.label = label;
    this.questions = questions;
    this.correctAnswers = correctAnswers;
    this.inputtedAnswers = inputtedAnswers;
    this.score = score;
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
}
