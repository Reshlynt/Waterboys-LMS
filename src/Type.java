package src;

public enum Type {
  QUIZ {
    public String toString() {
      return "Quiz";
    }
  },
  EXAM {
    public String toString() {
      return "Exam";
    }
  }
}
