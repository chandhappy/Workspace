import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class Pdf2Excel{
static String Folder="C:\\Users\\INCBASHA\\Documents\\My Received Files\\New folder\\";
@SuppressWarnings("unused")
public static void main(String args[]) throws InvalidPasswordException, IOException {

File folder = new File(Folder);
File[] listOfFiles = folder.listFiles();
for(int in=0;in<listOfFiles.length;in++) {
String path=Folder+listOfFiles[in].getName();
 Workbook wb = new HSSFWorkbook();
         //Workbook wb = new XSSFWorkbook();
         CreationHelper createHelper = wb.getCreationHelper();
         Sheet sheet = wb.createSheet("Patient sheet"+in);
         Row row = sheet.createRow(0);
         // Create Header.
         String[] columns = {"UID", "Gender", "Age", "OD DR","OD AMD","OD ONH","OD HR","OS DR","OS AMD","OS ONH","OS HR","Advice"};
         for(int i = 0; i < columns.length; i++) {
             Cell cell1 = row.createCell(i);
             cell1.setCellValue(columns[i]);
         }

         
        
        try (PDDocument document = PDDocument.load(new File(path))) {                
                document.getClass();
            if (!document.isEncrypted()) {
         
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                //System.out.println("Text:" + st);

// split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                int linecount= 0;
                int count1=0,count2=0,count3=0,count4=0;
                int rowCount=1;
                Row row1 = null;
                
               
               
                for (String line : lines) {
                 linecount++;
                 if(line.contains("UID")) {
                 //System.out.println(line); 
                 row1= sheet.createRow(rowCount);
                 System.out.println(row1);
                 row1.createCell(0).setCellValue(line.split(": ")[1]);
                 }
                 
                 
                 
                 if(line.contains("Gender"))
                 //System.out.println(line);
                	 System.out.println(row1);
                 row1.createCell(1).setCellValue(line.split(": ")[1]);
                 
                 if(line.contains("Age"))
                 //System.out.println(line);
                	 System.out.println(row1);
                 row1.createCell(2).setCellValue(line.split(": ")[1]);
                 
                 
                 if(line.contains("DR")) {
                 System.out.println(line);
                 System.out.println(lines[linecount]);
                 if(count1==0)
                 row1.createCell(3).setCellValue(lines[linecount]);
                 if(count1==1)
                 row1.createCell(7).setCellValue(lines[linecount]);
                 count1++;
                 }
                 
                 if(line.contains("AMD")) {
                 System.out.println(line);
                 System.out.println(lines[linecount]);
                 if(count2==0)
                 row1.createCell(4).setCellValue(lines[linecount]);
                 if(count2==1)
                 row1.createCell(8).setCellValue(lines[linecount]);
                 count2++;}
                 
                 if(line.contains("ONH region")) {
                 System.out.println(line);
                 System.out.println(lines[linecount]);
                 if(count3==0)
                 row1.createCell(5).setCellValue(lines[linecount]);
                 if(count3==1)
                 row1.createCell(9).setCellValue(lines[linecount]);
                 count3++;}
                 
                 if(line.contains("Hypertensive Retinopathy")) {
                 System.out.println(line);
                 System.out.println(lines[linecount]);
                 if(count4==0)
                 row1.createCell(6).setCellValue(lines[linecount]);
                 if(count4==1)
                 row1.createCell(10).setCellValue(lines[linecount]);
                 count4++;
                 }
                 
                 if(line.contains("Advice")) {
                 System.out.println(line);
                 row1.createCell(11).setCellValue(lines[linecount]);
                 rowCount++;
                 count1=count2=count3=count4=0;
                 }
                 
                 

                    // Write the output to a file
                   
         
                    //System.out.println(line);
                }
            
            
          }
            
            
            
            FileOutputStream fileOut = new FileOutputStream(Folder+"Patientbook"+in+".xls");
                wb.write(fileOut);
                fileOut.close();
                wb.close();
                
        
          }

        }

    }
}

