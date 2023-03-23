package src;
public class TextSlide implements Slide {
    private String title;
    private String contents;
    public TextSlide(String title, String contents){
      this.title = title;
      this.contents = contents;
    }
    public void display() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("|\n|\t" + title + "\n|--------------------------------------------------------------------------------------\n\n");
      System.out.println("\t\t" + contents);
      // TODO: if the text is too long, split into multiple lines    
      System.out.println("|\n|--------------------------------------------------------------------------------------\n");
      return;
    }
    public String toString(){
      return "TEXT SLIDE: " + title + ":\n" + contents;
    }
    // returns the slide's title
    public String getTitle() {
      return title;
    }
    // returns the slide's contents
    public String getContents() {
      return contents;
    }
}
