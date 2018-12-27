import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * 
 */

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
	static String numofjoinee;
	
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
   
	          try {
				DataValidate(IPPath,OPPath );
			} catch (BiffException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public static void main(String[] args) throws BiffException, IOException {
		numofjoinee =JOptionPane.showInputDialog(" Enter the number of Joinees: ");
		if(numofjoinee.equals("")||(Integer.parseInt(numofjoinee) == 0)){
			System.exit(0);}
		else{
		FileInput fip = new FileInput();
		
		}
	}
	void DataValidate(String path2, String oPPath2) throws BiffException, IOException, URISyntaxException{

		String Dest = oPPath2;
		String Reference_No;
		String Date;
		String Full_Name;
		String Address;
		String Designation;
		String Rank;
		String Grade;
		String Division;
		String Compensation;
		String Date_of_Joining;
		String Working_Location;
		String Reporting_Manager;
		String Reporting_Manager_Location;
		String Fathername;
		////
		// TODO Auto-generated method stub

		FileInputStream FIS = new FileInputStream(IPPath);
		Workbook ws = Workbook.getWorkbook(FIS);
		Sheet sh = ws.getSheet(0);
		for (int i = 1; i <=Integer.parseInt(numofjoinee) ; i++) {
			Reference_No 			= sh.getCell(0, i).getContents();
			Date 					= sh.getCell(1, i).getContents();
			Full_Name 				= sh.getCell(2, i).getContents();
			Fathername				= sh.getCell(3, i).getContents();
			Address 				= sh.getCell(4, i).getContents();
			Designation 			= sh.getCell(5, i).getContents();
			Rank 					= sh.getCell(6, i).getContents();
			Grade 					= sh.getCell(7, i).getContents();
			Division 				= sh.getCell(8, i).getContents();
			Compensation 			= sh.getCell(9, i).getContents();
			Date_of_Joining 		= sh.getCell(10, i).getContents();
			Working_Location 		= sh.getCell(11, i).getContents();
			Reporting_Manager	    = sh.getCell(12, i).getContents();
			Reporting_Manager_Location = sh.getCell(13, i).getContents();
			String Temp= Full_Name.replaceAll(" ", "");
			MKdir(Temp,Dest); 
			OFL(Reference_No, Date, Full_Name, Address, Designation, Rank,
					Grade, Division, Compensation, Date_of_Joining,
					Working_Location, Reporting_Manager,
					Reporting_Manager_Location);
			AddOFL(Reference_No, Date, Full_Name, Address, Designation, Rank,
					Grade, Division, Date_of_Joining
					);
			AnnexA(Full_Name);
	
			switch (Rank) {
			case "51":
				
				    InputStream initialStream = ResourceLoader.load("/Entitlement_51_2016.rtf");
				    File targetFile = new File(OPPath+"//"+Full_Name.replaceAll(" ", "")+"//"+ Full_Name.replaceAll(" ", "")+"_5.Entitlement_51.rtf");
				    FileUtils.copyInputStreamToFile(initialStream, targetFile);
				    break;
				    
			case "52":
				
					InputStream initialStream1 = ResourceLoader.load("/Entitlement_52_2016.rtf");
				    File targetFile1 = new File(OPPath+"//"+Full_Name.replaceAll(" ", "")+"//"+ Full_Name.replaceAll(" ", "")+"_5.Entitlement_52.rtf");
				    FileUtils.copyInputStreamToFile(initialStream1, targetFile1);
				    break;
				    
			case "53":
				
				 InputStream initialStream2 = ResourceLoader.load("/Entitlement_53_2016.rtf");
				    File targetFile2 = new File(OPPath+"//"+Full_Name.replaceAll(" ", "")+"//"+ Full_Name.replaceAll(" ", "")+"_5.Entitlement_53.rtf");
				    FileUtils.copyInputStreamToFile(initialStream2, targetFile2);
			    break;
			    
			case "54":
				
				 InputStream initialStream3 = ResourceLoader.load("/Entitlement_54_2016.rtf");
				    File targetFile3 = new File(OPPath+"//"+Full_Name.replaceAll(" ", "")+"//"+ Full_Name.replaceAll(" ", "")+"_5.Entitlement_54.rtf");
				    FileUtils.copyInputStreamToFile(initialStream3, targetFile3);
				    
			    break;

			default:
				System.out.println(Rank);
				
				break;
			}
			
			String loc= Working_Location.toUpperCase();
			if(loc.equalsIgnoreCase("Bangalore")||loc.equalsIgnoreCase("BLR")){			
				AnnexBang(Full_Name,Fathername);
						}else{
			AnnexOS(Full_Name, Fathername);
			}
		}
		JOptionPane.showMessageDialog(null,"COMPLETED");
		System.exit(0);
	}

	private static void AnnexBang(String full_Name, String fathername) throws IOException {
		// TODO Auto-generated method stub
		try {

			String opt = OPPath+"//"+full_Name.replaceAll(" ", "").replaceAll(" ", "")+"//"+full_Name.replaceAll(" ", "")+"_4.Appendex-B.docx";
			File file = new File(opt);
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());

			@SuppressWarnings("resource")
			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempparagraph = doc.createParagraph();
			XWPFRun tempruRun = tempparagraph.createRun();
			XWPFRun tempruRun1 = tempparagraph.createRun();
			XWPFRun tempruRun2 = tempparagraph.createRun();
			XWPFRun tempruRun3 = tempparagraph.createRun();
			XWPFRun tempruRun4 = tempparagraph.createRun();
			XWPFRun tempruRun5 = tempparagraph.createRun();
			XWPFRun tempruRun6 = tempparagraph.createRun();
			XWPFRun tempruRun7 = tempparagraph.createRun();
			XWPFRun tempruRun8 = tempparagraph.createRun();
			XWPFRun tempruRun9 = tempparagraph.createRun();
			XWPFRun tempruRun10 = tempparagraph.createRun();
			XWPFRun tempruRun11 = tempparagraph.createRun();
			XWPFRun tempruRun12 = tempparagraph.createRun();
			XWPFRun tempruRun13 = tempparagraph.createRun();
			XWPFRun tempruRun14 = tempparagraph.createRun();
			XWPFRun tempruRun15 = tempparagraph.createRun();
			XWPFRun tempruRun16 = tempparagraph.createRun();
			XWPFRun tempruRun17 = tempparagraph.createRun();
			XWPFRun tempruRun18 = tempparagraph.createRun();
			XWPFRun tempruRun19 = tempparagraph.createRun();
			XWPFRun tempruRun20 = tempparagraph.createRun();
			XWPFRun tempruRun21 = tempparagraph.createRun();
			XWPFRun tempruRun22 = tempparagraph.createRun();
			XWPFRun tempruRun23 = tempparagraph.createRun();
			XWPFRun tempruRun24 = tempparagraph.createRun();
			XWPFRun tempruRun25 = tempparagraph.createRun();
			XWPFRun tempruRun26 = tempparagraph.createRun();
			XWPFRun tempruRun27 = tempparagraph.createRun();
			XWPFRun tempruRun28 = tempparagraph.createRun();
			tempruRun.setBold(true);
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.setText("Annexure – B");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.setText("Terms & conditions of employment");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.setText("1.	Probation/Hours of work/Retirement");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun1.setText("a.	There will be a probationary period of six months from the date of your appointment, after which your performance will be reviewed and if found satisfactory, appointment will be confirmed.  If your performance is found to be unsatisfactory, the probation period may be extended until the management of the Company expressly confirms your employment.");  
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.setText("b.	You will be required to work for such days / hours as are necessary for proper discharge of your duties with the company.  The basic working hours are (subject to changes from time to time) 42.50 hours, from 8.30 am to 5.00 pm, Monday to Friday including a lunch break of half an hour.  As and when necessary, you may be required to work additional hours / days, as per the exigencies of work, for which no additional compensation or compensatory leave will be permissible.");
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.setText("c.	The retirement age is 62 years.");
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun2.setBold(true);
			tempruRun2.setText("2.	Assignment/Transfer/Deputation");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun3.setText("a.	You are liable to be transferred in such capacity, as the company may from time to time determine to any other location, division, function, establishment, or branch of the company or subsidiary, associate or affiliate company.  In such case you will be governed by the terms and conditions of service applicable to the new assignment in the new location including compensation, working hours, holidays, leave, people policies, etc.");
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun3.setText("b.	You may be required to undertake travel on company work for which you will be reimbursed travel expenses as per the company policy applicable.");
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun4.setBold(true);
			tempruRun4.setText("3.	Training Agreement");
			tempruRun5.addCarriageReturn();
			tempruRun5.addCarriageReturn();
			tempruRun6.setText("As the Company will be spending substantial amount of time and money for your training abroad, at the time of your first visit abroad, you will be required to sign an agreement with the Company as the Company may deem appropriate.  (This one time Agreement will consist of issues like (i) your commitment to complete the training (ii) your returning to India after completion of the training and serving the Company for a stipulated period).");
			tempruRun6.addCarriageReturn();
			tempruRun6.addCarriageReturn();
			tempruRun6.addCarriageReturn();
		
			tempruRun7.setBold(true);
			tempruRun7.setText("4.	Employment Agreement");
			tempruRun7.addCarriageReturn();
			tempruRun7.addCarriageReturn();
		
			tempruRun7.setText("Code of Conduct");
			tempruRun7.addCarriageReturn();
			tempruRun7.addCarriageReturn();
			tempruRun8.setText("In view of your position and office, you must effectively, diligently and to the best of your ability perform all responsibilities and ensure results.  You shall devote your whole time and attention to the interest of the Company and carry out duties and work as assigned to you. You shall obey and comply with all the lawful orders and directions given to you by your superior or other authorized officers of the Company.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();
			tempruRun8.setText("You shall honestly and faithfully serve the company and use your utmost endeavor to promote the interest of the Company.  It is essentially expected of you to project and maintain exemplary conduct, ethics and moral befitting to the image of the Company within as well as outside the organization.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();

			tempruRun9.setBold(true);
			tempruRun9.setText("5.	Intellectual property");
			tempruRun9.addCarriageReturn();
			tempruRun9.addCarriageReturn();
			tempruRun10.setText("In connection with your employment and during the term of your employment, you shall disclose and assign to Carl Zeiss as its exclusive property, all developments developed or conceived by you solely or jointly with others and shall comply with the policy of the company in relation to intellectual property.");
			tempruRun10.addCarriageReturn();
			tempruRun10.addCarriageReturn();
			
			tempruRun11.setBold(true);
			tempruRun11.setText("6.	Conflicts of Interest");
			tempruRun11.addCarriageReturn();
			tempruRun11.addCarriageReturn();

			tempruRun12.setText("(a)	You are required to engage yourself exclusively in the work assigned by Carl Zeiss and shall not take up any independent or individual assignments (whether the same is part time or full time, in the advisory capacity or otherwise) directly or indirectly without the expressed written consent of your supervisor.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("(b)	You shall ensure that you shall not directly or indirectly, engage in any activity or have any interest in, or perform any services for any person who is involved in activities, which are or shall be in conflict with the interests of Carl Zeiss.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("(c)	The conflict of interest policy also refers to the need on your part, during your employment and for a period of one year from the cessation of your employment with Carl Zeiss (irrespective of the circumstances, of or the reasons for, the cessation) not to solicit, induce or encourage :");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("i.	Any employee of Carl Zeiss to terminate their employment with Carl Zeiss or to accept employment with any competitor, supplier or any customer with whom you have a connection.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("ii.	Any customer or vendor of Carl Zeiss to move his existing business with Carl Zeiss to a third party or to terminate his business relationship with Carl Zeiss.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("iii.	Any existing employee to become associated with, or perform services of any type for any third party.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			
			tempruRun13.setBold(true);
			tempruRun13.setText(" 7.	Confidentiality");
			tempruRun13.addCarriageReturn();
			tempruRun13.addCarriageReturn();
			tempruRun14.setText("In consideration of the opportunities, training and access to new techniques and know-how that will be made available to you, you will be required to comply with the Confidentiality Policy of the company.  Therefore, please ensure that you maintain as secret and confidential all confidential information (as defined from time to time in the Confidentiality Policy of the Company) and shall not use or disclose any such confidential information except as may be required under obligation of law or as may be required by Carl Zeiss and in the course of your employment.  This covenant shall ensure during your employment and for a period of one year from the cessation of your employment with Carl Zeiss (irrespective of the circumstances of or the reasons for the cessation).");
			tempruRun14.addCarriageReturn();
			tempruRun14.addCarriageReturn();
			tempruRun15.setBold(true);
			tempruRun15.setText("8.	Statement of Facts");
			tempruRun15.addCarriageReturn();
			tempruRun15.addCarriageReturn();
			tempruRun16.setText("It must be specifically understood that this offer is made based on your proficiency on Technical/Professional skills you have declared to possess as per the application and on the ability to handle any assignment/job independently anywhere in India or overseas.  In case, at a later date, any of your statements/particulars furnished are found to be false or misleading, or your performance is not up to the mark or falls short of the minimum standards set by the Company, the Company shall have the right to terminate your services forthwith without giving any notice, notwithstanding any other terms and conditions stipulated herein.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("Please note that you are required to inform us if there are any agreements / bond, oral or written, ");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("which you have entered into and which may relate to or affect your commitments under this Agreement.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("Your employment terms may be specifically enforced legally, if required.  In this connection, if any of the provisions of this Agreement are declared or found to be void or unenforceable due to any reason whatsoever, the remaining provisions of the Agreement shall continue in full force and effect.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun17.setBold(true);
			tempruRun17.setText("9.  Tax");
			tempruRun17.addCarriageReturn();
			tempruRun17.addCarriageReturn();

			tempruRun18.setText("It is your responsibility to meet all requirements under the Indian tax laws including tax compliance and filing of personal tax returns in respect of all payments made to you, pursuant to this appointment.  Taxes on any payment made under this appointment will be borne by you.  The Company shall deduct tax at source (TDS) on any payment under this employment that require such deduction to be made by the employer in accordance with the Indian tax laws.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun19.setBold(true);
			tempruRun19.setText("10.  Use of Company Resources");
			tempruRun19.addCarriageReturn();
			tempruRun19.addCarriageReturn();
			tempruRun20.setText("You shall always maintain in good condition Company’s property which may be given to you for official use during the course of your employment.  You shall use the Company’s resources only for official purposes. You shall return the same to the Company immediately upon your relinquishing your services failing which the Company will recover all associated costs of such material from you as per the existing policy.");
			tempruRun20.addCarriageReturn();
			tempruRun20.addCarriageReturn();
			tempruRun21.setBold(true);
			tempruRun21.setText("11.  Unauthorized Software");
			tempruRun21.addCarriageReturn();
			tempruRun21.addCarriageReturn();

			tempruRun22.setText("You shall not install, download, copy, duplicate any unauthorized or unlicensed software, programs, games, attachments on to your computer systems.");
			tempruRun22.addCarriageReturn();
			tempruRun22.addCarriageReturn();
			tempruRun23.setBold(true);
			tempruRun23.setText("12.  Notice Period");
			tempruRun23.addCarriageReturn();
			tempruRun23.addCarriageReturn();
			tempruRun24.setText("This contract of employment is terminable, by either party giving one month notice during probationary period and three months notice on confirmation of employment.  Carl Zeiss reserves the right to pay or recover salary in lieu of notice period.  Further, the Company may at its discretion relieve you from such date as it may deem fit even prior to the expiry of the notice period.  However, if the management desires the employee to continue the employment during the notice period the employee shall do so.");
			tempruRun24.addCarriageReturn();
			tempruRun24.addCarriageReturn();
			tempruRun25.setBold(true);
			tempruRun25.setText("13.  Termination of Employment");
			tempruRun25.addCarriageReturn();
			tempruRun25.addCarriageReturn();
			tempruRun26.setText("Absence without approval by your reporting authority, from duty for a continuous period of 7 calendar days would make you loose your lien on employment.  In such case, your employment shall automatically come to an end without any notice of termination.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("In case you fail to provide adequate notice or if employment has been terminated due to absence from duty without approval or because of any other action / inaction on employee’s part, he / she will be liable to pay the following :");
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Notice pay equivalent to one month gross salary in case of probation & three months gross salary in case of confirmed employees.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("If you abandon the services within six months, in addition to the notice pay you are also liable to pay the expenses borne by the Company towards your relocation and accommodation. You are expected to assist the company in bringing your replacement at the time of leaving, so that smooth knowledge transfer can take place.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Reference check will be made from your previous employers.  In case there is any adverse report against you, which may be detrimental to the interests of the Company, or if the information furnished by you is not true, the Company reserves the right to terminate your services forthwith (notwithstanding any other provisions) on the grounds of misrepresentation of facts.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("In case of any dispute or difference arising between the Company and yourself, the same shall be attempted to be settled by mutual discussion.  In case of failure to reach a settlement, the dispute shall be referred to Arbitration to be conducted by the Managing Director of the Company or his nominee, in accordance with the Arbitration and Conciliation Act, 1996.  The venue of arbitration shall be Bangalore");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Please confirm that the above terms are acceptable to you and that you accept the appointment by signing a copy of the letter of appointment.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("This is to certify that I, ");
			tempruRun27.setBold(true);
			tempruRun27.setText(full_Name +" S/o or D/o "+fathername);
			tempruRun28.setText(" have gone through and understood all the terms and conditions mentioned in above letter and I hereby accept and agree to abide by them. (Form Q complied with Karnataka Shops & Commercial Establishment Act 1961.");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Name in full (BLOCK LETTERS)	:");
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Address		:");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Signature		:");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Date		:");

			doc.write(fos);
			fos.close();
		} finally {

		}
		
	}

	private static void AnnexOS(String full_Name, String fathername) throws IOException {
		// TODO Auto-generated method stub
		try {

			String opt = OPPath+"//"+full_Name.replaceAll(" ", "")+"//"+full_Name.replaceAll(" ", "")+"_4.Appendex-B.docx";
			File file = new File(opt);
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());

			@SuppressWarnings("resource")
			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempparagraph = doc.createParagraph();
			XWPFRun tempruRun = tempparagraph.createRun();
			XWPFRun tempruRun1 = tempparagraph.createRun();
			XWPFRun tempruRun2 = tempparagraph.createRun();
			XWPFRun tempruRun3 = tempparagraph.createRun();
			XWPFRun tempruRun4 = tempparagraph.createRun();
			XWPFRun tempruRun5 = tempparagraph.createRun();
			XWPFRun tempruRun6 = tempparagraph.createRun();
			XWPFRun tempruRun7 = tempparagraph.createRun();
			XWPFRun tempruRun8 = tempparagraph.createRun();
			XWPFRun tempruRun9 = tempparagraph.createRun();
			XWPFRun tempruRun10 = tempparagraph.createRun();
			XWPFRun tempruRun11 = tempparagraph.createRun();
			XWPFRun tempruRun12 = tempparagraph.createRun();
			XWPFRun tempruRun13 = tempparagraph.createRun();
			XWPFRun tempruRun14 = tempparagraph.createRun();
			XWPFRun tempruRun15 = tempparagraph.createRun();
			XWPFRun tempruRun16 = tempparagraph.createRun();
			XWPFRun tempruRun17 = tempparagraph.createRun();
			XWPFRun tempruRun18 = tempparagraph.createRun();
			XWPFRun tempruRun19 = tempparagraph.createRun();
			XWPFRun tempruRun20 = tempparagraph.createRun();
			XWPFRun tempruRun21 = tempparagraph.createRun();
			XWPFRun tempruRun22 = tempparagraph.createRun();
			XWPFRun tempruRun23 = tempparagraph.createRun();
			XWPFRun tempruRun24 = tempparagraph.createRun();
			XWPFRun tempruRun25 = tempparagraph.createRun();
			XWPFRun tempruRun26 = tempparagraph.createRun();
			XWPFRun tempruRun27 = tempparagraph.createRun();
			XWPFRun tempruRun28 = tempparagraph.createRun();
			tempruRun.setBold(true);
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.setText("Annexure – B");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.setText("Terms & conditions of employment");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.setText("1.	Probation/Hours of work/Retirement");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun1.setText("a.	There will be a probationary period of six months from the date of your appointment, after which your performance will be reviewed and if found satisfactory, appointment will be confirmed.  If your performance is found to be unsatisfactory, the probation period may be extended until the management of the Company expressly confirms your employment.");  
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.setText("b.	You will be required to work for such days / hours as are necessary for proper discharge of your duties with the company.  The basic working hours are (subject to changes from time to time) 42.50 hours, from 9.00 am to 5.30 pm, Monday to Friday including a lunch break of half an hour.  As and when necessary, you may be required to work additional hours / days, as per the exigencies of work, for which no additional compensation or compensatory leave will be permissible.");
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.setText("c.	The retirement age is 62 years.");
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun1.addCarriageReturn();
			tempruRun2.setBold(true);
			tempruRun2.setText("2.	Assignment/Transfer/Deputation");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun3.setText("a.	You are liable to be transferred in such capacity, as the company may from time to time determine to any other location, division, function, establishment, or branch of the company or subsidiary, associate or affiliate company.  In such case you will be governed by the terms and conditions of service applicable to the new assignment in the new location including compensation, working hours, holidays, leave, people policies, etc.");
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun3.setText("b.	You may be required to undertake travel on company work for which you will be reimbursed travel expenses as per the company policy applicable.");
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun3.addCarriageReturn();
			tempruRun4.setBold(true);
			tempruRun4.setText("3.	Training Agreement");
			tempruRun5.addCarriageReturn();
			tempruRun5.addCarriageReturn();
			tempruRun6.setText("As the Company will be spending substantial amount of time and money for your training abroad, at the time of your first visit abroad, you will be required to sign an agreement with the Company as the Company may deem appropriate.  (This one time Agreement will consist of issues like (i) your commitment to complete the training (ii) your returning to India after completion of the training and serving the Company for a stipulated period).");
			tempruRun6.addCarriageReturn();
			tempruRun6.addCarriageReturn();
			tempruRun6.addCarriageReturn();
		
			tempruRun7.setBold(true);
			tempruRun7.setText("4.	Employment Agreement");
			tempruRun7.addCarriageReturn();
			tempruRun7.addCarriageReturn();
		
			tempruRun7.setText("Code of Conduct");
			tempruRun7.addCarriageReturn();
			tempruRun7.addCarriageReturn();
			tempruRun8.setText("In view of your position and office, you must effectively, diligently and to the best of your ability perform all responsibilities and ensure results.  You shall devote your whole time and attention to the interest of the Company and carry out duties and work as assigned to you. You shall obey and comply with all the lawful orders and directions given to you by your superior or other authorized officers of the Company.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();
			tempruRun8.setText("You shall honestly and faithfully serve the company and use your utmost endeavor to promote the interest of the Company.  It is essentially expected of you to project and maintain exemplary conduct, ethics and moral befitting to the image of the Company within as well as outside the organization.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();

			tempruRun9.setBold(true);
			tempruRun9.setText("5.	Intellectual property");
			tempruRun9.addCarriageReturn();
			tempruRun9.addCarriageReturn();
			tempruRun10.setText("In connection with your employment and during the term of your employment, you shall disclose and assign to Carl Zeiss as its exclusive property, all developments developed or conceived by you solely or jointly with others and shall comply with the policy of the company in relation to intellectual property.");
			tempruRun10.addCarriageReturn();
			tempruRun10.addCarriageReturn();
			
			tempruRun11.setBold(true);
			tempruRun11.setText("6.	Conflicts of Interest");
			tempruRun11.addCarriageReturn();
			tempruRun11.addCarriageReturn();

			tempruRun12.setText("(a)	You are required to engage yourself exclusively in the work assigned by Carl Zeiss and shall not take up any independent or individual assignments (whether the same is part time or full time, in the advisory capacity or otherwise) directly or indirectly without the expressed written consent of your supervisor.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("(b)	You shall ensure that you shall not directly or indirectly, engage in any activity or have any interest in, or perform any services for any person who is involved in activities, which are or shall be in conflict with the interests of Carl Zeiss.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("(c)	The conflict of interest policy also refers to the need on your part, during your employment and for a period of one year from the cessation of your employment with Carl Zeiss (irrespective of the circumstances, of or the reasons for, the cessation) not to solicit, induce or encourage :");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("i.	Any employee of Carl Zeiss to terminate their employment with Carl Zeiss or to accept employment with any competitor, supplier or any customer with whom you have a connection.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("ii.	Any customer or vendor of Carl Zeiss to move his existing business with Carl Zeiss to a third party or to terminate his business relationship with Carl Zeiss.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			tempruRun12.setText("iii.	Any existing employee to become associated with, or perform services of any type for any third party.");
			tempruRun12.addCarriageReturn();
			tempruRun12.addCarriageReturn();
			
			tempruRun13.setBold(true);
			tempruRun13.setText(" 7.	Confidentiality");
			tempruRun13.addCarriageReturn();
			tempruRun13.addCarriageReturn();
			tempruRun14.setText("In consideration of the opportunities, training and access to new techniques and know-how that will be made available to you, you will be required to comply with the Confidentiality Policy of the company.  Therefore, please ensure that you maintain as secret and confidential all confidential information (as defined from time to time in the Confidentiality Policy of the Company) and shall not use or disclose any such confidential information except as may be required under obligation of law or as may be required by Carl Zeiss and in the course of your employment.  This covenant shall ensure during your employment and for a period of one year from the cessation of your employment with Carl Zeiss (irrespective of the circumstances of or the reasons for the cessation).");
			tempruRun14.addCarriageReturn();
			tempruRun14.addCarriageReturn();
			tempruRun15.setBold(true);
			tempruRun15.setText("8.	Statement of Facts");
			tempruRun15.addCarriageReturn();
			tempruRun15.addCarriageReturn();
			tempruRun16.setText("It must be specifically understood that this offer is made based on your proficiency on Technical/Professional skills you have declared to possess as per the application and on the ability to handle any assignment/job independently anywhere in India or overseas.  In case, at a later date, any of your statements/particulars furnished are found to be false or misleading, or your performance is not up to the mark or falls short of the minimum standards set by the Company, the Company shall have the right to terminate your services forthwith without giving any notice, notwithstanding any other terms and conditions stipulated herein.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("Please note that you are required to inform us if there are any agreements / bond, oral or written, ");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("which you have entered into and which may relate to or affect your commitments under this Agreement.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun16.setText("Your employment terms may be specifically enforced legally, if required.  In this connection, if any of the provisions of this Agreement are declared or found to be void or unenforceable due to any reason whatsoever, the remaining provisions of the Agreement shall continue in full force and effect.");
			tempruRun16.addCarriageReturn();
			tempruRun16.addCarriageReturn();
			tempruRun17.setBold(true);
			tempruRun17.setText("9.  Tax");
			tempruRun17.addCarriageReturn();
			tempruRun17.addCarriageReturn();

			tempruRun18.setText("It is your responsibility to meet all requirements under the Indian tax laws including tax compliance and filing of personal tax returns in respect of all payments made to you, pursuant to this appointment.  Taxes on any payment made under this appointment will be borne by you.  The Company shall deduct tax at source (TDS) on any payment under this employment that require such deduction to be made by the employer in accordance with the Indian tax laws.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun19.setBold(true);
			tempruRun19.setText("10.  Use of Company Resources");
			tempruRun19.addCarriageReturn();
			tempruRun19.addCarriageReturn();
			tempruRun20.setText("You shall always maintain in good condition Company’s property which may be given to you for official use during the course of your employment.  You shall use the Company’s resources only for official purposes. You shall return the same to the Company immediately upon your relinquishing your services failing which the Company will recover all associated costs of such material from you as per the existing policy.");
			tempruRun20.addCarriageReturn();
			tempruRun20.addCarriageReturn();
			tempruRun21.setBold(true);
			tempruRun21.setText("11.  Unauthorized Software");
			tempruRun21.addCarriageReturn();
			tempruRun21.addCarriageReturn();

			tempruRun22.setText("You shall not install, download, copy, duplicate any unauthorized or unlicensed software, programs, games, attachments on to your computer systems.");
			tempruRun22.addCarriageReturn();
			tempruRun22.addCarriageReturn();
			tempruRun23.setBold(true);
			tempruRun23.setText("12.  Notice Period");
			tempruRun23.addCarriageReturn();
			tempruRun23.addCarriageReturn();
			tempruRun24.setText("This contract of employment is terminable, by either party giving one month notice during probationary period and three months notice on confirmation of employment.  Carl Zeiss reserves the right to pay or recover salary in lieu of notice period.  Further, the Company may at its discretion relieve you from such date as it may deem fit even prior to the expiry of the notice period.  However, if the management desires the employee to continue the employment during the notice period the employee shall do so.");
			tempruRun24.addCarriageReturn();
			tempruRun24.addCarriageReturn();
			tempruRun25.setBold(true);
			tempruRun25.setText("13.  Termination of Employment");
			tempruRun25.addCarriageReturn();
			tempruRun25.addCarriageReturn();
			tempruRun26.setText("Absence without approval by your reporting authority, from duty for a continuous period of 7 calendar days would make you loose your lien on employment.  In such case, your employment shall automatically come to an end without any notice of termination.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("In case you fail to provide adequate notice or if employment has been terminated due to absence from duty without approval or because of any other action / inaction on employee’s part, he / she will be liable to pay the following :");
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Notice pay equivalent to one month gross salary in case of probation & three months gross salary in case of confirmed employees.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("If you abandon the services within six months, in addition to the notice pay you are also liable to pay the expenses borne by the Company towards your relocation and accommodation. You are expected to assist the company in bringing your replacement at the time of leaving, so that smooth knowledge transfer can take place.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Reference check will be made from your previous employers.  In case there is any adverse report against you, which may be detrimental to the interests of the Company, or if the information furnished by you is not true, the Company reserves the right to terminate your services forthwith (notwithstanding any other provisions) on the grounds of misrepresentation of facts.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("In case of any dispute or difference arising between the Company and yourself, the same shall be attempted to be settled by mutual discussion.  In case of failure to reach a settlement, the dispute shall be referred to Arbitration to be conducted by the Managing Director of the Company or his nominee, in accordance with the Arbitration and Conciliation Act, 1996.  The venue of arbitration shall be Bangalore");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("Please confirm that the above terms are acceptable to you and that you accept the appointment by signing a copy of the letter of appointment.");
			tempruRun26.addCarriageReturn();
			tempruRun26.addCarriageReturn();
			tempruRun26.setText("This is to certify that I, ");
			tempruRun27.setBold(true);
			tempruRun27.setText(full_Name +" S/o or D/o "+fathername);
			tempruRun28.setText(" have gone through and understood all the terms and conditions mentioned in above letter and I hereby accept and agree to abide by them. (Form Q complied with Karnataka Shops & Commercial Establishment Act 1961.");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Name in full (BlOCK LETTERS)	:");
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Address		:");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Signature		:");
			tempruRun28.addCarriageReturn();
			tempruRun28.addCarriageReturn();
			tempruRun28.setText("Date		:");
			
			doc.write(fos);
			fos.close();
		} finally {

		}
		
	}


	private static void AnnexA(String full_Name) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
				InputStream initialStream = ResourceLoader.load("/Annexure_A.rtf");
			    File targetFile = new File(OPPath+"//"+full_Name.replaceAll(" ", "")+"//"+ full_Name.replaceAll(" ", "")+"_3.Annexure-A.rtf");
			    FileUtils.copyInputStreamToFile(initialStream, targetFile);
	}

	private static void MKdir(String full_Name, String oPPath2) {
		// TODO Auto-generated method stub
		File dir = new File(oPPath2+"//"+full_Name);
        dir.mkdir();
        
	}
	private static void AddOFL(String reference_No, String date,
			String full_Name, String address, String designation, String rank,
			String grade, String division, String date_of_Joining) throws IOException {
		
		try {

			String opt = OPPath+"//"+full_Name.replaceAll(" ", "")+"//"+ full_Name.replaceAll(" ", "")+"_2.AddendumLetter.docx";
			File file = new File(opt);
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());

			@SuppressWarnings("resource")
			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempparagraph = doc.createParagraph();
			XWPFRun tempruRun = tempparagraph.createRun();
			XWPFRun tempruRun1 = tempparagraph.createRun();
			XWPFRun tempruRun2 = tempparagraph.createRun();
			tempparagraph.setSpacingAfterLines(1);
			tempparagraph.setWordWrapped(true);
			tempruRun.setFontFamily("Times New Roman");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.setText("Reference #: 201516/" + division + "/" + rank + "/" + "ADD1001");
			tempruRun.addCarriageReturn();
			tempruRun.setText(date);
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			
			tempruRun1.setFontFamily("Times New Roman");
			tempruRun1.setBold(true);
			tempruRun1.setUnderline(UnderlinePatterns.SINGLE);
			tempruRun1.setText("Addendum to the Offer of Appointment");
			
			tempruRun2.setFontFamily("Times New Roman");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText(" Mr. / Ms. " + full_Name + ",");
			tempruRun2.addCarriageReturn();
			tempruRun2.setText(address);
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("Dear " + full_Name + ",");
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("With reference to the offer of appointment letter bearing ref number"+"Ref#: 201516/" + division + "/" + rank + "/" + "ADD1001"+" we hereby confirm, your joining date is changed to "+date_of_Joining+". All other terms and conditions remain unchanged.");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("We welcome you to Carl Zeiss India (Bangalore) Private Limited and look forward to a long and mutually beneficial association.");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("For Carl Zeiss India (Bangalore) Pvt. Ltd.");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("Sudipto Mandal");
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("Divisional Manager - HR");
			
			doc.write(fos);
			fos.close();
		}finally{} 
		// TODO Auto-generated method stub
		
	}

	private static void OFL(String reference_No, String date, String full_Name,
			String address, String designation, String rank, String grade,
			String division, String compensation, String date_of_Joining,
			String working_Location, String reporting_Manager,
			String reporting_Manager_Location) throws IOException {
		// TODO Auto-generated method stub

		try {

			String Temp=full_Name.replaceAll(" ", "") ;
			String opt = OPPath+"//"+Temp+"//"+Temp+"_1.OfferLetter.docx";
			File file = new File(opt);
			FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());

			@SuppressWarnings("resource")
			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempparagraph = doc.createParagraph();
			XWPFRun tempruRun = tempparagraph.createRun();
			XWPFRun tempruRun1 = tempparagraph.createRun();
			XWPFRun tempruRun2 = tempparagraph.createRun();
			XWPFRun tempruRun3 = tempparagraph.createRun();
			XWPFRun tempruRun4 = tempparagraph.createRun();
			XWPFRun tempruRun5 = tempparagraph.createRun();
			XWPFRun tempruRun6 = tempparagraph.createRun();
			XWPFRun tempruRun7 = tempparagraph.createRun();
			XWPFRun tempruRun8 = tempparagraph.createRun();
			XWPFRun tempruRun9 = tempparagraph.createRun();
			XWPFRun tempruRun10 = tempparagraph.createRun();
			XWPFRun tempruRun11 = tempparagraph.createRun();
			XWPFRun tempruRun12 = tempparagraph.createRun();
			XWPFRun tempruRun13 = tempparagraph.createRun();
			XWPFRun tempruRun14 = tempparagraph.createRun();
			XWPFRun tempruRun15 = tempparagraph.createRun();
			XWPFRun tempruRun16 = tempparagraph.createRun();
			XWPFRun tempruRun17 = tempparagraph.createRun();
			XWPFRun tempruRun18 = tempparagraph.createRun();
			tempparagraph.setSpacingAfterLines(1);
			tempparagraph.setWordWrapped(true);
			tempruRun.setFontFamily("Times New Roman");
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			tempruRun.setText("Reference #: 201516/" + division + "/" + rank + "/" + "OFF1001");
			tempruRun.addCarriageReturn();
			tempruRun.setText(date);
			tempruRun.addCarriageReturn();
			tempruRun.addCarriageReturn();
			
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			tempruRun.addTab();
			
			tempruRun1.setFontFamily("Times New Roman");
			tempruRun1.setBold(true);
			tempruRun1.setUnderline(UnderlinePatterns.SINGLE);
			tempruRun1.setText("Offer of Appointment");
			
			tempruRun2.setFontFamily("Times New Roman");
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText(" Mr. / Ms. " + full_Name + ",");
			tempruRun2.addCarriageReturn();
			tempruRun2.setText(address);
			tempruRun2.addCarriageReturn();
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("Dear " + full_Name + ",");
			tempruRun2.addCarriageReturn();
			tempruRun2.setText("1. Based on your interview with us and subsequent discussions, we are pleased to appoint you as ");
			
			tempruRun3.setFontFamily("Times New Roman");
			tempruRun3.setBold(true);
			tempruRun3.setText( designation + " in Rank "+ rank+ "/ Grade "+ grade);
			
			tempruRun4.setFontFamily("Times New Roman");
			tempruRun4.setText(" in the ");
			
			tempruRun5.setFontFamily("Times New Roman");
			tempruRun5.setBold(true);
			tempruRun5.setText(division);
			
			tempruRun6.setFontFamily("Times New Roman");
			tempruRun6.setText(" in Carl Zeiss India (Bangalore) Pvt. Ltd.");
			tempruRun6.addCarriageReturn();
			tempruRun6.addCarriageReturn();
			tempruRun6.setText("2. Your annual total / fixed compensation will be ");
			
			tempruRun7.setFontFamily("Times New Roman");
			tempruRun7.setBold(true);
			tempruRun7.setText("INR. "+compensation);
			
			tempruRun8.setFontFamily("Times New Roman");
			tempruRun8.setText(" only. The break–up is attached in Annexure-A.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();
			tempruRun8.setText("3.	Your employment with us will be governed by terms and conditions referred in Annexure-B.");
			tempruRun8.addCarriageReturn();
			tempruRun8.addCarriageReturn();
			tempruRun8.setText("4. You are required to join us on ");
			
			tempruRun9.setFontFamily("Times New Roman");
			tempruRun9.setBold(true);
			tempruRun9.setText(date_of_Joining+".");
			tempruRun9.addCarriageReturn();
			tempruRun9.addCarriageReturn();
			
			tempruRun10.setFontFamily("Times New Roman");
			tempruRun10.setText("5. Your current location of posting would be ");
			
			tempruRun11.setFontFamily("Times New Roman");
			tempruRun11.setBold(true);
			tempruRun11.setText(working_Location+".");
			tempruRun11.addCarriageReturn();
			tempruRun11.addCarriageReturn();
			
			tempruRun12.setFontFamily("Times New Roman");
			tempruRun12.setText("6. You would be reporting to ");
			
			tempruRun13.setFontFamily("Times New Roman");
			tempruRun13.setBold(true);
			tempruRun13.setText(reporting_Manager);
			
			tempruRun14.setFontFamily("Times New Roman");
			tempruRun14.setText(" in the ");
			
			tempruRun15.setFontFamily("Times New Roman");
			tempruRun15.setBold(true);
			tempruRun15.setText(division);
			
			tempruRun16.setFontFamily("Times New Roman");
			tempruRun16.setText(" based in ");
			
			tempruRun17.setFontFamily("Times New Roman");
			tempruRun17.setBold(true);
			tempruRun17.setText(reporting_Manager_Location+".");
			
			tempruRun18.setFontFamily("Times New Roman");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText("7.	Please note that your joining is subject to submission of all the relevant documents.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText("8.	Please sign the duplicate copy of the offer of appointment letter on all sheets and return to us.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText("9.	We welcome you to Carl Zeiss India (Bangalore) Private Limited and look forward to a long and mutually beneficial association.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText( "For Carl Zeiss India (Bangalore) Pvt. Ltd.");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText( "Sudipto Mandal");
			tempruRun18.addCarriageReturn();
			tempruRun18.setText(	"Divisional Manager - HR");
			tempruRun18.addCarriageReturn();
			tempruRun18.addCarriageReturn();
			tempruRun18.setText( "Enclosures:");
			tempruRun18.addTab();
			tempruRun18.setText("Annexure – A (Salary Breakup)");
			tempruRun18.addCarriageReturn();
			tempruRun18.addTab();
			tempruRun18.addTab();
			tempruRun18.setText( "Annexure – B (Terms & Conditions of Employment)");
			tempruRun18.addCarriageReturn();
			tempruRun18.addTab();
			tempruRun18.addTab();
			tempruRun18.setText("Annexure – C (Entitlement Sheet)");
			tempruRun18.addCarriageReturn();
			doc.write(fos);
			fos.close();
		} finally {

		}
	}
	
}

