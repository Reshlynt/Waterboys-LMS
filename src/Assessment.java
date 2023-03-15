package src;
import java.util.ArrayList;

public class Assessment extends Slide {
    private String label;
    private ArrayList<Question> questions;
    private String correctAnswers;
    private String inputtedAnswers;
    private int score;
    private Type type;
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
