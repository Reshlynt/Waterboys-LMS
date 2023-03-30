package src;

import java.util.ArrayList;

public class Assessment {
  private String title;
  private ArrayList<Question> questions;
  private int score;
  private Type type;

  /**
   * Constructor
   * 
   * @param title     assessment title
   * @param questions questions
   */
  public Assessment(String title, ArrayList<Question> questions) {
    this.title = title;
    this.questions = questions;
  }

  /**
   * Constructor
   * 
   * @param title     assessment title
   * @param questions questions
   * @param type      quiz or test
   */
  public Assessment(String title, ArrayList<Question> questions, Type type) {
    this.title = title;
    this.questions = questions;
    this.type = type;
  }

  /**
   * getter for score
   * 
   * @return score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * getter for title
   * 
   * @return string specifying the title of the assessment
   */
  public String getTitle() {
    return title;
  }

  public void addQuestion(int index, Question question) {
    questions.add(index, question);
  }

  /**
   * To String method for printing the assessment(debugging method)
   * 
   * @return String with title, type, questions
   */
  public String toString() {
    String info = "title: " + title + " type" + type + "\n Questions: \n";
    for (int i = 0; i < questions.size(); i++) {
      info += ( (i + 1) + ". " + questions.get(i) + "\n");
    }
    return info;
  }

  /**
   * Getter for assessment type
   * 
   * @return Type specifying quiz or test
   */
  public Type getType() {
    return type;
  }

  /**
   * Sets the type of assessment, quiz or test
   * 
   * @param type QUIZ or TEST
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * Getter for ArrayList of Questions
   * 
   * @return ArrayList of Questions
   */
  public ArrayList<Question> getQuestions() {
    return questions;
  }

}
