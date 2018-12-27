import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
  public static void main(String args[]) {
    // Match lowercase words.
    Pattern pat = Pattern.compile("[a-z]+");
    Matcher mat = pat.matcher("www.java2s.com.");

    while (mat.find())
      System.out.println("Match: " + mat.group());
  }
}
