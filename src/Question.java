package src;
import java.util.ArrayList;

public class Question {
  String question;
  ArrayList<String> answerChoices = new ArrayList<String>();
  String correctAnswer;

  public Question(String question, ArrayList<String> answerChoices, String correctAnswer) {
    this.question = question;
    this.answerChoices = answerChoices;
    this.correctAnswer = correctAnswer;
  }

  public Question() {
  }

  public boolean createQuestion(ArrayList<String> answers, String prompt, String correctAnswer) {
    return true;
  }

  public String toString() {
    String info = "Question: " + question + "\n";
    info += "Answer choices: ";
    for (int i = 0; i < answerChoices.size(); i++) {
      info += (i + answerChoices.get(i) + "\n");
    }
    info += ("Correct Answer: " + correctAnswer);
    return info;
  }

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
}
