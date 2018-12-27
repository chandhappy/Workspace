import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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


public class pdftoexcel{
	
	
	static String path[]= {"C:\\Users\\INCBASHA\\Desktop\\Sample_PATTest01_retinareport.pdf"};
	public static void main(String args[]) throws InvalidPasswordException, IOException {
		
		
		
		 Workbook wb = new HSSFWorkbook();
         //Workbook wb = new XSSFWorkbook();
         CreationHelper createHelper = wb.getCreationHelper();
         Sheet sheet = wb.createSheet("Patientsheet");
         Row row = sheet.createRow(0);
         // Create Header.
         String[] columns = {"UID", "Gender", "Age", "OD DR","OD AMD","OD ONH","OD HR","OS DR","OS AMD","OS ONH","OS HR","Advice"};
         for(int i = 0; i < columns.length; i++) {
             Cell cell1 = row.createCell(i);
             cell1.setCellValue(columns[i]);
         }

		
         
         for(int i=0;i<path.length;i++) {
		try (PDDocument document = PDDocument.load(new File(path[i]))) {                
                document.getClass();
	            if (!document.isEncrypted()) {
	         
	                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	                stripper.setSortByPosition(true);
	                PDFTextStripper tStripper = new PDFTextStripper();

	                String pdfFileInText = tStripper.getText(document);
	                //System.out.println("Text:" + st);

					// split by whitespace
	                String lines[] = pdfFileInText.split("\\r?\\n");
	                int linecount=0;
	                int count1=0,count2=0,count3=0,count4=0,count5=0;
	              	Row row1 = sheet.createRow(i+1);
	              	
	                for (String line : lines) {
	                	linecount++;
	                	
	                	
	                	if(line.contains("UID"))
	                		//System.out.println(line);	
	                		row1.createCell(0).setCellValue(line.split(": ")[1]);
	                	
	                		
	                	
	                	if(line.contains("Gender"))
	                		//System.out.println(line);
	                		row1.createCell(1).setCellValue(line.split(": ")[1]);
	                	
	                	if(line.contains("Age"))
	                		//System.out.println(line);
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
	                	
	                	if(line.contains("ONH Region")) {
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
	                	
	                	if(line.contains("Advise")) {
	                		System.out.println(line);
	                		System.out.println(lines[linecount]);
	                		if(count5==0)
	                			row1.createCell(7).setCellValue(lines[linecount]);
	                		if(count5==1)
	                			row1.createCell(11).setCellValue(lines[linecount]);
	                		count5++;
	                		}
	                	

	                    // Create a row and put some cells in it. Rows are 0 based.
	                   
	                    	
	                   
	                    

	                    // Write the output to a file
	                   
	         
	                   // System.out.println(line);
	                }
	            }
	            
	            
	            
	            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\INCBASHA\\Desktop\\Patientbook.xls");
                wb.write(fileOut);
                fileOut.close();
        
	          }

	        }

	    }
	}