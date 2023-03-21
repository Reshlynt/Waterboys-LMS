package src;
import java.util.ArrayList;

public class Assessment extends Slide {
  private String title;
  private ArrayList<Question> questions;
  private String correctAnswers;
  private String inputtedAnswers;
  private int score;
  private Type type;

  public Assessment(String title, ArrayList<Question> questions, Type type) {
    this.title = title;
    this.questions = questions;
    this.type = type;
  }

  public void display() {
    System.out.println("|\n|\t" + title + "\n|--------------------------------------------------------------------------------------\n");
    return;
  }

  public int getScore() {
    return this.score;
  }
  // Returns the question label (perhaps the question itself)
  public String getTitle() {
    return title;
  }

  // Return the correct answers
  public String getCorrectAnswers() {
    return correctAnswers;
  }

  // Return the inputted answers
  public String getInputtedAnswers() {
    return inputtedAnswers;
  }

  public String toString() {
    String info = "label: " + title + " type" + type+"\n Questions: \n";
    for(int i = 0; i<questions.size();i++){
      info+=(questions.get(i)+"\n");
    }
    return info;   
  }
  // Return the type of assessment
  public Type getType() {
    return type;
  }
  // Return the questions
  public ArrayList<Question> getQuestions() {
    return questions;
  }

}
