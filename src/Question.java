package src;

import java.util.ArrayList;

public class Question {
  private String question;
  private ArrayList<String> answerChoices = new ArrayList<String>();
  private String correctAnswer;
  // correct answer is a string - a, b, c, or d

  /**
   * Constructor for loading from json
   * @param question
   * @param answerChoices
   * @param correctAnswer
   */
  public Question(String question, ArrayList<String> answerChoices, String correctAnswer) {
    this.question = question;
    this.answerChoices = answerChoices;
    this.correctAnswer = "a";
    if (correctAnswer.equals("b") || correctAnswer.equals("c") || correctAnswer.equals("d")) {
      this.correctAnswer = correctAnswer;
    }
  }

  public Question() {

  }

  /**
   * Method to create a question
   */
  public boolean createQuestion(ArrayList<String> answers, String prompt, String correctAnswer) {
    return true;
  }

  /**
   * toString method to print the question
   */
  public String toString() {
    String info = "Question: " + question + "\n";
    char letter = 'a';
    info += "Answer choices: \n";
    for (int i = 0; i < answerChoices.size(); i++) {
      info += ("\t" + letter + ". " + answerChoices.get(i) + "\n");
      letter++;
    }
    info += ("\nCorrect Answer: " + correctAnswer);
    return info;
  }

  /**
   * Method to get a question
   * @return question
   */
  public Question getQuestion() {
    return new Question();
  }

  // Return the correct answer
  public String getCorrectAnswer() {
    return correctAnswer;
  }

  // Return the answer choices
  public ArrayList<String> getAnswerChoices() {
    return answerChoices;
  }

  // Return the question content
  public String getQuestionContent() {
    return question;
  }

  public int getPoints() {
    int points = 0;
    // find the number of correct answers and add that to points
    return points;

  }
}
