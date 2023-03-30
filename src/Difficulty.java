package src;

public enum Difficulty {
  BEGINNER {
    public String toString() {
      return "Beginner";
    }
  },
  INTERMEDIATE {
    public String toString() {
      return "Intermediate";
    }
  },
  EXPERT {
    public String toString() {
      return "Expert";
    }
  },
  INSTANT_DEATH {
    public String toString() {
      return "Instant Death";
    }
  }
}
