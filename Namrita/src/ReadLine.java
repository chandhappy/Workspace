import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
 
public class ReadLine
{
   public static void main(String[] args)
   {
      readFromFile("app.properties");
   }
 
   private static void readFromFile(String filename)
   {
      LineNumberReader lineNumberReader = null;
      try
      {
         //Construct the LineNumberReader object
         lineNumberReader = new LineNumberReader(new FileReader("C:\\Users\\INCBASHA\\Desktop\\reliance\\Auto DR 4 Arindam\\B372AJ18_retinareport.pdf"));
          
         //Print initial line number
         System.out.println("Line " + lineNumberReader.getLineNumber());
          
         //Setting initial line number
         lineNumberReader.setLineNumber(5);
          
         //Get current line number
         System.out.println("Line " + lineNumberReader.getLineNumber());
          
         //Read all lines now; Every read increase the line number by 1
         String line = null;
         while ((line = lineNumberReader.readLine()) != null)
         {
            System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      } finally
      {
         //Close the LineNumberReader
         try {
            if (lineNumberReader != null){
               lineNumberReader.close();
            }
         } catch (IOException ex){
            ex.printStackTrace();
         }
      }
   }
}