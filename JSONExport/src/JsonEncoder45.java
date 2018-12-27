import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jxl.Sheet;
import jxl.Workbook;

public class JsonEncoder45 {

	static String IPPath,OPPath;
	static String numofstudies;
	
	JsonEncoder45(){
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame("Json Spliter");
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
	        	  JsonEncode(IPPath,OPPath );

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
	
	
	public void JsonEncode(String IPPath, String OPPath) {
	        try {
	 
	        	  BufferedWriter bw =  new BufferedWriter(new FileWriter(OPPath+"\\JsonSpliter.csv"));
		          String CSV_SEPARATOR = ",";
		          StringBuffer oneLine1 = new StringBuffer();
		          ////-- Header for The columns-----
		   
			       oneLine1.append("STUDYID");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODFundusImageUrl");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODUngradableScore");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODImageId");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODNonRetinalScore");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("ODAverageDrScore");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODDrStatus");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("ODEyeLateralityType");
			       oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OSFundusImageUrl");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OSUngradableScore");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OSImageId");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OSNonRetinalScore");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OSAverageDrScore");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OSDrStatus");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OSEyeLateralityType");
	               oneLine1.append(CSV_SEPARATOR);
	              
			       oneLine1.append("UndefinedFundusList");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("ODEyeDRStatus");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OSEyeDRStatus");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("UndefinedEyeDRStatus");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("CaseDRStatus");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("ReportAdvice");
			       oneLine1.append(CSV_SEPARATOR);
	               
	            
				bw.write(oneLine1.toString());
				bw.newLine();
				
				FileInputStream FIS = new FileInputStream(IPPath);
				Workbook ws = Workbook.getWorkbook(FIS);
				Sheet sh = ws.getSheet(0);
				for (int j = 1; j <= Integer.parseInt(numofstudies); j++) {
					try {
				StringBuffer oneLine = new StringBuffer();
				System.out.println(j);
				String Json = sh.getCell(1, j).getContents();
	            Json=Json.replace("[]", "[{\r\"FundusImageUrl\": \"NULL\",\r\"UngradableScore\": 0.0,\r\"ImageId\": 0,\r\"NonRetinalScore\": 0.0,\r\"AverageDrScore\": 0.0,\r\"DrStatus\": 0,\r\"EyeLateralityType\": 0\r}]").replaceAll("null","Null").replaceAll("\n", "");
	            System.out.println(Json);
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(Json.toString().replaceAll(",", "").replaceAll(":", ""));
				
				long studyid = (long) jsonObject.get("StudyId");
			
			       oneLine.append(studyid);
	               oneLine.append(CSV_SEPARATOR);
	               
	           	JSONArray StdGrdArray = (JSONArray) jsonObject.get("ODFundusList");
				@SuppressWarnings("rawtypes")
				Iterator itr = StdGrdArray.iterator();
	            while(itr.hasNext()) {
	            	JSONObject Mediatype = (JSONObject) itr.next();
	            	String FundusImageUrl =  JSONObject.escape((String) Mediatype.get("FundusImageUrl"));
	            	double UngradableScore = (double) Mediatype.get("UngradableScore");
	            	long ImageId = (long) Mediatype.get("ImageId");
	            	double NonRetinalScore = (double) Mediatype.get("NonRetinalScore");
	            	double AverageDrScore = (double) Mediatype.get("AverageDrScore");
	            	long DrStatus = (long) Mediatype.get("DrStatus");
	            	long EyeLateralityType = (long) Mediatype.get("EyeLateralityType");
	          
	            	oneLine.append(FundusImageUrl);
		            oneLine.append(CSV_SEPARATOR);
	            	oneLine.append(UngradableScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(ImageId);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(NonRetinalScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(AverageDrScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(DrStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(EyeLateralityType);
	                oneLine.append(CSV_SEPARATOR);
	                
	            }
	            
	        	JSONArray StdGrdArray1 = (JSONArray) jsonObject.get("OSFundusList");
	        	@SuppressWarnings("rawtypes")
				Iterator itr1 = StdGrdArray1.iterator();
	            while(itr1.hasNext()) {
	            	JSONObject Mediatype = (JSONObject) itr1.next();
	            	String FundusImageUrl =  JSONObject.escape((String) Mediatype.get("FundusImageUrl"));
	            	double UngradableScore = (double) Mediatype.get("UngradableScore");
	            	long ImageId = (long) Mediatype.get("ImageId");
	            	double NonRetinalScore = (double) Mediatype.get("NonRetinalScore");
	            	double AverageDrScore = (double) Mediatype.get("AverageDrScore");
	            	long DrStatus = (long) Mediatype.get("DrStatus");
	            	long EyeLateralityType = (long) Mediatype.get("EyeLateralityType");
	            	oneLine.append(FundusImageUrl);
		            oneLine.append(CSV_SEPARATOR);
	            	oneLine.append(UngradableScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(ImageId);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(NonRetinalScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(AverageDrScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(DrStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(EyeLateralityType);
	                oneLine.append(CSV_SEPARATOR);
	                
	            }
	           
	         
	            JSONArray StdGrdArray2 = (JSONArray) jsonObject.get("UndefinedFundusList");
	        	@SuppressWarnings("rawtypes")
				Iterator itr2 = StdGrdArray2.iterator();
	            while(itr2.hasNext()) {
	            	JSONObject Mediatype = (JSONObject) itr2.next();
	            	String FundusImageUrl =  JSONObject.escape((String) Mediatype.get("FundusImageUrl"));
	            	double UngradableScore = (double) Mediatype.get("UngradableScore");
	            	long ImageId = (long) Mediatype.get("ImageId");
	            	double NonRetinalScore = (double) Mediatype.get("NonRetinalScore");
	            	double AverageDrScore = (double) Mediatype.get("AverageDrScore");
	            	long DrStatus = (long) Mediatype.get("DrStatus");
	            	long EyeLateralityType = (long) Mediatype.get("EyeLateralityType");
	            	oneLine.append(FundusImageUrl);
		            oneLine.append(CSV_SEPARATOR);
	            	oneLine.append(UngradableScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(ImageId);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(NonRetinalScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(AverageDrScore);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(DrStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(EyeLateralityType);
	                oneLine.append(CSV_SEPARATOR);
	                
	            }
	            
	            oneLine.append(CSV_SEPARATOR);
	            long ODEyeDRStatus = (long) jsonObject.get("ODEyeDRStatus");
	            long OSEyeDRStatus = (long) jsonObject.get("OSEyeDRStatus");
	            long UndefinedEyeDRStatus = (long) jsonObject.get("UndefinedEyeDRStatus");
	            long CaseDRStatus = (long) jsonObject.get("CaseDRStatus");
            	String ReportAdvice = JSONObject.escape( (String) jsonObject.get("ReportAdvice"));
            	    oneLine.append(ODEyeDRStatus);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(OSEyeDRStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(UndefinedEyeDRStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(CaseDRStatus);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(ReportAdvice.replace("[]", " ").replaceAll("null","\"null\"").replaceAll("\n", ""));
	                oneLine.append(CSV_SEPARATOR);
	            
	            bw.write(oneLine.toString());
			    bw.newLine();
	            }catch(Exception e) {
		            e.printStackTrace();}
					
				}
				
				bw.flush();
				bw.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        JOptionPane.showMessageDialog(null,"COMPLETED");
			System.exit(0);
	}

	public static void main(String[] args) {
		numofstudies =JOptionPane.showInputDialog(" Enter the number of studies: ");
		if(numofstudies.equals("")||(Integer.parseInt(numofstudies) == 0)){
			System.exit(0);}
		else{
			new JsonEncoder45();
		}
		
		
	}

}
