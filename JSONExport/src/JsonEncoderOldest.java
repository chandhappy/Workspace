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

public class JsonEncoderOldest {

	static String IPPath,OPPath;
	static String numofstudies;
	
	JsonEncoderOldest(){
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
		          oneLine1.append("JOBID");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OBSERVATIONS");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("ADVICE");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("STUDYID");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("AGE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("GENDER");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("DELEGATE_COMMENT");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("MEDIATYPE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS GRADABLE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS CLINICALFINDINGS");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OS COMMENTS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS DISEASE");
	               oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OS IMAGEURL");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("MEDIATYPE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD GRADABLE");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OD CLINICALFINDINGS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD COMMENTS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD DISEASE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD IMAGEURL");
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
				String JobId = sh.getCell(0, j).getContents();
				String Json = sh.getCell(1, j).getContents();
	            Json=Json.replace("[]", " ").replaceAll("null","\"null\"").replaceAll("\n", "");
	          //  System.out.println(new StringBuilder(Json).insert(241, "***"));
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(Json.toString().replace("[]", ""));
				//String observations =(String) Mediatype.get("observations");
				String observations = JSONObject.escape((String) jsonObject.get("observations"));
				String Advice = JSONObject.escape((String) jsonObject.get("advice"));
				long studyid = (long) jsonObject.get("studyid");
				String age = JSONObject.escape((String) jsonObject.get("age"));
				String gender =JSONObject.escape( (String) jsonObject.get("gender"));
				String delegate_comment =JSONObject.escape( (String) jsonObject
						.get("delegate_comment"));
				JSONObject StdGrd = (JSONObject) jsonObject.get("StudyGrading");
				JSONArray StdGrdArray = (JSONArray) StdGrd.get("medias");
				   oneLine.append(JobId);
			       oneLine.append(CSV_SEPARATOR);
			       oneLine.append(observations.replaceAll("\n", "").replaceAll(",", " "));
			       oneLine.append(CSV_SEPARATOR);
	               oneLine.append(Advice);
			       oneLine.append(CSV_SEPARATOR);
			       oneLine.append(studyid);
	               oneLine.append(CSV_SEPARATOR);
	               oneLine.append(age);
	               oneLine.append(CSV_SEPARATOR);
	               oneLine.append(gender);
	               oneLine.append(CSV_SEPARATOR);
	               oneLine.append(delegate_comment);
	               oneLine.append(CSV_SEPARATOR);
	            
				@SuppressWarnings("rawtypes")
				Iterator itr = StdGrdArray.iterator();
	            while(itr.hasNext()) {
	            	JSONObject Mediatype = (JSONObject) itr.next();
	            	String Type =JSONObject.escape( (String) Mediatype.get("type"));
	            	Boolean gradable = (Boolean) Mediatype.get("gradable");
	            	JSONArray ClinFindArray = (JSONArray) Mediatype.get("clinicalFindings");
	            	
	            	String comments =JSONObject.escape( (String) Mediatype.get("comments"));
	            	String disease =JSONObject.escape( (String) Mediatype.get("disease"));
	            	String imageUrl =JSONObject.escape( (String) Mediatype.get("imageUrl"));
	            	oneLine.append(Type);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(gradable);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(ClinFindArray.toString().replaceAll(",", " ").replaceAll("\n", " "));
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(comments);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(disease);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(imageUrl);
	                oneLine.append(CSV_SEPARATOR);
	 		      
	            }
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
			new JsonEncoderOldest();
		}
		
		
	}

}
