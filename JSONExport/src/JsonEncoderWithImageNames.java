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

public class JsonEncoderWithImageNames {

	static String IPPath,OPPath;
	static String numofstudies;
	
	JsonEncoderWithImageNames(){
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
			       oneLine1.append("RECOMMENDATION");
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
	               oneLine1.append("OS IMAGEURL");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS OBSERVATIONS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS GRADABLE");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OS RECOMMENDATIONMEDIA");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS CLINICALFINDINGS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS MRRVRECOMMANDATION");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS ONHRECOMMANDATION");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS COMMENTS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS DISEASE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS NOTGRADABLE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS NOIMAGE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS PARTIALLYGRADABLE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS AMD");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS DR");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS GLAUCOMA");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OS HYPERTENSIVERETINOPATHY");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("MEDIATYPE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD IMAGEURL");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD OBSERVATIONS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD GRADABLE");
			       oneLine1.append(CSV_SEPARATOR);
			       oneLine1.append("OD RECOMMENDATIONMEDIA");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD CLINICALFINDINGS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD MRRVRECOMMANDATION");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD ONHRECOMMANDATION");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD COMMENTS");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD DISEASE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD NOTGRADABLE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD NOIMAGE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD PARTIALLYGRADABLE");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD AMD");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD DR");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD GLAUCOMA");
	               oneLine1.append(CSV_SEPARATOR);
	               oneLine1.append("OD HYPERTENSIVERETINOPATHY");
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
	            Json=Json.replace("[]", "").replaceAll("null","\"null\"").replaceAll("\n", "");
	        //    System.out.println(Json.replace("[]", "").replaceAll("null","\"null\"").replaceAll("\n", ""));
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(Json.toString().replace("[]", ""));
				String Reccom = JSONObject.escape((String) jsonObject.get("recommendation"));
				long studyid = (long) jsonObject.get("studyid");
				String age = JSONObject.escape((String) jsonObject.get("age"));
				String gender =JSONObject.escape( (String) jsonObject.get("gender"));
				String delegate_comment =JSONObject.escape( (String) jsonObject
						.get("delegate_comment"));
				JSONObject StdGrd = (JSONObject) jsonObject.get("StudyGrading");
				JSONArray StdGrdArray = (JSONArray) StdGrd.get("medias");
				   oneLine.append(JobId);
			       oneLine.append(CSV_SEPARATOR);
	               oneLine.append(Reccom.replaceAll("\n", "").replaceAll(",", ""));
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
	            	String imageUrl =JSONObject.escape( (String) Mediatype.get("imageUrl"));
	            	String observations =(String) Mediatype.get("observations");
	            	Boolean gradable = (Boolean) Mediatype.get("gradable");
	            	String recommendationmedia = JSONObject.escape((String) Mediatype.get("recommendationmedia"));
	            	String clinicalFindings =JSONObject.escape( (String) Mediatype.get("clinicalFindings"));
	            	String mrrvrecommandation =JSONObject.escape( (String) Mediatype.get("mrrvrecommandation"));
	            	String onhrecommandation =JSONObject.escape( (String) Mediatype.get("onhrecommandation"));
	            	String comments =JSONObject.escape( (String) Mediatype.get("comments"));
	            	String disease =JSONObject.escape( (String) Mediatype.get("disease"));
	            	Boolean notGradable = (Boolean) Mediatype.get("notGradable");
	            	Boolean noImage = (Boolean) Mediatype.get("noImage");
	            	Boolean partiallyGradable = (Boolean) Mediatype.get("partiallyGradable");
	            	long amd = (long) Mediatype.get("amd");
	            	long dr = (long) Mediatype.get("dr");
	            	long glaucoma = (long) Mediatype.get("glaucoma");
	            	long hypertensiveRetinopathy = (long) Mediatype.get("hypertensiveRetinopathy");
	            	oneLine.append(Type);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(imageUrl.substring(imageUrl.lastIndexOf('/')+1));
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(observations.replaceAll("\n", "").replaceAll(",", ""));
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(gradable);
	 		        oneLine.append(CSV_SEPARATOR);
	 		        oneLine.append(recommendationmedia);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(clinicalFindings.replaceAll("\n", "").replaceAll(",", ""));
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(mrrvrecommandation);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(onhrecommandation);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(comments);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(disease);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(notGradable);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(noImage);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(partiallyGradable);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(amd);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(dr);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(glaucoma);
	                oneLine.append(CSV_SEPARATOR);
	                oneLine.append(hypertensiveRetinopathy);
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
			new JsonEncoderWithImageNames();
		}
		
		
	}

}
