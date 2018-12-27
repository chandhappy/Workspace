import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.pdmodel.interactive.form.*;
import java.io.File;
import java.util.*;

public class pdf_form_filler {

    public static void listFields(PDDocument doc) throws Exception {
        PDDocumentCatalog catalog = doc.getDocumentCatalog();
        PDAcroForm form = catalog.getAcroForm();
        List<PDField> fields = form.getFields();

        for(PDField field: fields) {
            Object value = field.getValueAsString();
            String name = field.getFullyQualifiedName();
            System.out.print(name);
            System.out.print(" = ");
            System.out.print(value);
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\INCBASHA\\Desktop\\Order_VISUHEALTH_IN.pdf");
        File targetPdf =new File("C:\\Users\\INCBASHA\\Desktop\\VIS.pdf"); 
        PDDocument doc = PDDocument.load(file);
        listFields(doc);
        ///------------------------
        
        ///----
        PDDocument pdfDocument = PDDocument.load(file);
        PDDocumentCatalog catalog = pdfDocument.getDocumentCatalog();
        PDAcroForm form = catalog.getAcroForm();
        PDField  field1  = form.getField("Billable"); 	field1.setValue("Yes");
        PDField  field2  = form.getField("Region"); 	field2.setValue("North");
        PDField  field3  = form.getField("No of Patients"); 	field3.setValue("100");
        PDField  field4  = form.getField("Camp Start Time - HH:MM"); 	field4.setValue("05:00");
        PDField  field5  = form.getField("Camp end time- HH:MM"); 	field5.setValue("02:00");
        PDField  field6  = form.getField("Doctor's Name"); 	field6.setValue("Hello");
        PDField  field7  = form.getField("Camp Address"); 	field7.setValue("Hello");
        PDField  field8  = form.getField("Pincode"); 	field8.setValue("123456");
        PDField  field9  = form.getField("State"); 	field9.setValue("Hello");
        PDField  field10 = form.getField("Doctor's email"); 	field10.setValue("Hello");
        PDField  field11 = form.getField("Doctor's mobile no"); 	field11.setValue("1234567890");
        PDField  field12 = form.getField("Sales executive's name"); 	field12.setValue("Hello");
        PDField  field13 = form.getField("Sales Executive Mobile number"); 	field13.setValue("1234567890");
        PDField  field14 = form.getField("Sales Executive email"); 	field14.setValue("Hello");
        PDField  field15 = form.getField("Area / Regional Manager"); 	field15.setValue("Hello");
        PDField  field16 = form.getField("Area / Regional Manager Mobile"); 	field16.setValue("Hello");
        PDField  field17 = form.getField("Request tracker Ticket no"); 	field17.setValue("Hello");
        PDField  field18 = form.getField("Name of the Application specialist"); 	field18.setValue("Hello");
        PDField  field19 = form.getField("ST"); 	field19.setValue("AM");
        PDField  field20 = form.getField("ET"); 	field20.setValue("PM");
        PDField  field21 = form.getField("DD-MMM-YY"); 	field21.setValue("25-Aug-17");
        PDField  field22 = form.getField("NameofPharma"); 	field22.setValue("Hello");
        PDField  field23 = form.getField("Campdate"); 	field23.setValue("25-Aug-17");
        PDField  field24 = form.getField("Submit"); 	field24.setFieldFlags(0);
        PDField  field25 = form.getField("CityOrTown"); 	field25.setValue("Hello");
        
        PDPushButton SubmitBtn = (PDPushButton) form.getField("Submit");
        SubmitBtn.setPushButton(true);
        
    	pdfDocument.save(targetPdf);
    	
    	 String javaScript = "var CampName = this.getField(\"NameofPharma\").value;\r\n" + 
    	 		"var CampDate =  this.getField(\"Campdate\").value;\r\n" + 
    	 		"var CampLocation =  this.getField(\"CityOrTown\").value;\r\n" + 
    	 		"var cBody= \" \";\r\n" + 
    	 		"var cSubLine = \"Form: \"+CampName+\"_\"+CampLocation+\"_\"+CampDate;\r\n" + 
    	 		"var cEmailURL = \"mailto:Order_visuhealth.IN@Zeiss.com?subject=\" + cSubLine + \"&body=\" + cBody;\r\n" + 
    	 		"this.submitForm({cURL: encodeURI(cEmailURL), cSubmitAs:\"PDF\", cCharSet:\"utf-8\"});";

    	      //Creating PDActionJavaScript object 
    	      PDActionJavaScript PDAjavascript = new PDActionJavaScript(javaScript);

    	      //Embedding java script
    	      pdfDocument.getDocumentCatalog().setOpenAction(PDAjavascript);
    	      
    	pdfDocument.close();
        
    }

}