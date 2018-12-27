import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;


/**
 * @author INCBASHA
 *
 */
public class FileInput {
	static Date sysdate=Calendar.getInstance().getTime();
	/**
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 * 
	 * 
	 */
	static String IPPath,OPPath;
	static String numofSch,PharmaName;
	static String workingDir = System.getProperty("user.dir");
	//File Dirc = new File(workingDir + "\\resources\\Order_VISUHEALTH_IN.pdf");
	//File Dirc = new File(ResourceLoader.load("/Order_VISUHEALTH_IN.pdf"));
	
	String[] South = {"ANDHRAPRADESH","TELANGANA","KARNATAKA","TAMILNADU","KERALA"};
	String[] North = {"RAJASTHAN","UTTARPRADESH,","PUNJAB","HARYANA","UTTARAKHAND","DELHI", "JAMMU & KASHMIR"};
	String[] West = {"GUJARAT","MAHARASTRA","GOA"};
	String[] East = {"WESTBENGAL","ORRISA","ASSAM","SIKKIM","JHARKAND","BIHAR"};
	String[] Central= {"MADHYAPRADESH","CHATTISGARH"};
	

	static String Region = "South";
	FileInput(){
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("Document Maker");
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton button = new JButton("Select the .xls File");
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office excel 2003-07", ".xls"));
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          IPPath=selectedFile.getPath();
	          OPPath=fileChooser.getCurrentDirectory().toString();
   
	          DataValidate(IPPath,OPPath );
	        }
	      }
	    });
	    frame.add(button);
	    frame.pack();
	    frame.setSize(300,300);
	    frame.setResizable(false);
	    frame.setLocationRelativeTo( null );
	    frame.setVisible(true);
	   
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)  {
		numofSch =JOptionPane.showInputDialog(" Enter the number of Schedules: ");
		//PharmaName = JOptionPane.showInputDialog(" Enter the Pharma Name: ");
		//if(numofSch.equals("")||(Integer.parseInt(numofSch) == 0||PharmaName.equals(""))){
		if(numofSch.equals("")||(Integer.parseInt(numofSch) == 0)){
			System.exit(0);}
		else{
		FileInput fip = new FileInput();
		}
	}
	void DataValidate(String path2, String oPPath2) {

		String Dest = oPPath2;
		String SerialNo;
		String SalesExecutiveName;
		String SalesExecutiveEmail;
		String SalesMobileNo;
		String RBMName;
		String RBMEmail;
		String RBMContact;
		String DrName;
		String DrEmail;
		String DrContact;
		String CampLocation;
		String City;
		String State;
		String CampDate;
		String CampTime;
		String NumOfScanExp;
		////
		// TODO Auto-generated method stub

		FileInputStream FIS;
		Workbook ws;
		try {
			
			FIS = new FileInputStream(IPPath);
			ws = Workbook.getWorkbook(FIS);
			Sheet sh = ws.getSheet(0);
			for (int i = 1; i <=Integer.parseInt(numofSch) ; i++) {
				PharmaName							= sh.getCell(0, i).getContents();
				SerialNo 			                = sh.getCell(1, i).getContents();
				SalesExecutiveName 					= sh.getCell(2, i).getContents();
				SalesExecutiveEmail 				= sh.getCell(3, i).getContents();
				SalesMobileNo				        = sh.getCell(4, i).getContents();
				RBMName 				 			= sh.getCell(5, i).getContents();
				RBMEmail 							= sh.getCell(6, i).getContents();
				RBMContact 							= sh.getCell(7, i).getContents();
				DrName 								= sh.getCell(8, i).getContents();
				DrEmail 							= sh.getCell(9, i).getContents();
				DrContact 							= sh.getCell(10, i).getContents();
				CampLocation 						= sh.getCell(11, i).getContents();
				City 								= sh.getCell(12, i).getContents();
				State								= sh.getCell(13,i).getContents();
				CampDate	    					= sh.getCell(14, i).getContents();
				CampTime 							= sh.getCell(15, i).getContents();
				NumOfScanExp 						= sh.getCell(16, i).getContents();
				
				if(Arrays.asList(South).contains(State.toUpperCase().trim())) {
					Region = "South";
				}else if(Arrays.asList(North).contains(State.toUpperCase().trim().replace(" ", ""))) {
					Region = "North";
				}else if(Arrays.asList(West).contains(State.toUpperCase().trim().replace(" ", ""))) {
					Region = "West";
				}else if(Arrays.asList(East).contains(State.toUpperCase().trim().replace(" ", ""))) {
					Region = "East";
				}else if(Arrays.asList(Central).contains(State.toUpperCase().trim().replace(" ", ""))) {
					Region = "Central";
				}
				
		        File targetPdf =new File(Dest+"\\"+SerialNo+"_"+PharmaName+"_"+City.replaceAll(" ", "")+"_"+CampDate.replaceAll("/", "_")+"_"+DrName+"_"+CampTime.replaceAll(":", ".")+".pdf"); 
		        PDDocument pdfDocument = PDDocument.load(ResourceLoader.load("/Order_VISUHEALTH_IN.pdf"));
		        PDDocumentCatalog catalog = pdfDocument.getDocumentCatalog();
		        PDAcroForm form = catalog.getAcroForm();
		        PDField  field1  = form.getField("Billable"); 	field1.setValue("Yes");
		        PDField  field2  = form.getField("Region"); 	field2.setValue(Region);
		        PDField  field3  = form.getField("No of Patients"); 	field3.setValue(NumOfScanExp.replace('\u00A0',' ').trim());
		        PDField  field4  = form.getField("CampStartTime"); 	field4.setValue(CampTime.replace('\u00A0',' ').trim());
		        PDField  field5  = form.getField("Camp end time- HH:MM"); 	field5.setValue("NA");
		        PDField  field6  = form.getField("DoctorName"); 	field6.setValue(DrName.replace('\u00A0',' ').trim());
		        PDField  field7  = form.getField("Camp Address"); 	field7.setValue(CampLocation.replace('\u00A0',' ').trim());
		        PDField  field8  = form.getField("Pincode"); 	field8.setValue("NA");
		        PDField  field9  = form.getField("State"); 	field9.setValue(State.replace('\u00A0',' ').trim());
		        PDField  field10 = form.getField("Doctor's email"); 	field10.setValue(DrEmail.replace('\u00A0',' ').trim());
		        PDField  field11 = form.getField("Doctor's mobile no"); 	field11.setValue(DrContact.replace('\u00A0',' ').trim());
		        PDField  field12 = form.getField("Sales executive's name"); 	field12.setValue(SalesExecutiveName.replace('\u00A0',' ').trim());
		        PDField  field13 = form.getField("Sales Executive Mobile number"); 	field13.setValue(SalesMobileNo.replace('\u00A0',' ').trim());
		        PDField  field14 = form.getField("Sales Executive email"); 	field14.setValue(SalesExecutiveEmail.replace('\u00A0',' ').trim());
		        PDField  field15 = form.getField("Area / Regional Manager"); 	field15.setValue(RBMName.replace('\u00A0',' ').trim());
		        PDField  field16 = form.getField("Area / Regional Manager Mobile"); 	field16.setValue(RBMContact.replace('\u00A0',' ').trim());
		        PDField  field17 = form.getField("Area / Regional Manager email"); 	field17.setValue(RBMEmail.replace('\u00A0',' ').trim());
		        PDField  field22 = form.getField("NameofPharma"); 	field22.setValue(PharmaName.replace('\u00A0',' ').trim());
		        PDField  field23 = form.getField("Campdate"); 	field23.setValue(CampDate.replace('\u00A0',' ').trim());
		        PDField  field25 = form.getField("CityOrTown"); 	field25.setValue(City.replace('\u00A0',' ').trim());
		       	pdfDocument.save(targetPdf);      
		    	pdfDocument.close();
			}
			JOptionPane.showMessageDialog(null,"COMPLETED");
			System.exit(0);

		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error..! Please check the inputs provided and retry..!");
			System.exit(0);
		}
		
	}
}

