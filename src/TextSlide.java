package src;

public class TextSlide implements Slide {

  private String title;

  private String contents;

  public TextSlide(String title, String contents) {
    this.title = title;
    this.contents = contents;
    //formatText();

  }

  // Displays the slide
  public void display() {
    System.out.println("|\n|\t" + title
        + "\n|--------------------------------------------------------------------------------------\n\n");
    System.out.println("\t\t" + contents);
    System.out.println(
        "|\n|--------------------------------------------------------------------------------------\n");
    return;
  }

  public String toString() {
    return "\t\t" + title + ":\n" + contents;
  }

  // returns the slide's title
  public String getTitle() {
    return title;
  }

  // returns the slide's contents
  public String getContents() {
    return contents;
  }

  /**
   * Using the contents of the slide, this method will replace a ' ' with a
   * newline character every
   * 87 characters. This is to ensure that the text is not too long to fit on the
   * screen.
   */
  // private void formatText() {
  //   if (contents.length() < 87)
  //     return;
  //   // Prime the loop
  //   int i = 74;
  //   i = contents.indexOf(' ', i);
  //   contents = contents.substring(0, i) + "\n" + contents.substring(i + 1, contents.length());
  //   i += 87;
  //   while (i < contents.length()) {
  //     if (contents.indexOf(' ', i) != -1) {
  //       i = contents.indexOf(' ', i);
  //       contents = contents.substring(0, i) + "\n" + contents.substring(i + 1, contents.length());
  //     }
  //     i += 87;
  //   }
  // }
}