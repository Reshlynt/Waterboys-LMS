package src;
import java.util.ArrayList;

public class Assessment implements Slide {
  private String title;
  private ArrayList<Question> questions;
  private String correctAnswers;
  private String inputtedAnswers;
  private int score;
  private Type type;

  public Assessment(String title, ArrayList<Question> questions) {
    this.title = title;
    this.questions = questions;
  }
  public Assessment(String title, ArrayList<Question> questions, Type type) {
    this.title = title;
    this.questions = questions;
    this.type = type;
  }

  public void display() {
    for (int i = 0; i < questions.size(); i++) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("|\n|\t" + title + "\n|--------------------------------------------------------------------------------------\n");
      System.out.println("\t" + (i + 1) + ": " + questions.get(i).getQuestion() + "\n");
      for(int j = 0; j<questions.get(i).getAnswerChoices().size();j++){
        System.out.println("\t\t"+('a'+j)+": "+questions.get(i).getAnswerChoices().get(j));
      }
      inputtedAnswers += UI.INPUT.nextLine();
      System.out.println("|\n|--------------------------------------------------------------------------------------\n");
    }
    int numCorrect = 0;
    for (int i = 0; i < questions.size(); i++) {
      if (questions.get(i).getCorrectAnswer().equals(inputtedAnswers.charAt(i) + "")) {
        numCorrect++;
      }
    }
    score = numCorrect;
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
    String info = "title: " + title + " type" + type+"\n Questions: \n";
    for(int i = 0; i<questions.size();i++){
      info+=(questions.get(i)+"\n");
    }
    return info;   
  }
  // Return the type of assessment
  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  // Return the questions
  public ArrayList<Question> getQuestions() {
    return questions;
  }

}
