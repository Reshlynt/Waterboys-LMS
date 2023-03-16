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

  public boolean createQuestion(ArrayList<String> answers, String prompt, String correctAnswer) {
    return true;
  }

  public Question getQuestion() {
    return new Question();
  }
}
