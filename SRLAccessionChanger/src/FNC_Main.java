import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FNC_Main {
	
	static Date sysdate=Calendar.getInstance().getTime();
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMYYYY");
	static String formattedDate = sdf.format(sysdate);
	static String formattedDate1 = sdf1.format(sysdate);
	static String IPPath, OPPath;
	static String PatientsCount;

	public FNC_Main() {
		// TODO Auto-generated constructor stub

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Choose WorkList File");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Select the .xls File");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
						"MS Office excel 2003-07", ".xls"));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					IPPath = selectedFile.getPath();
					OPPath = fileChooser.getCurrentDirectory().toString();
					try {
						DataValidate(OPPath);
					} catch (BiffException | IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		frame.add(button);
		frame.pack();
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientsCount = JOptionPane
				.showInputDialog(" Enter the number of Patients: ");
		if (PatientsCount.equals("") || (Integer.parseInt(PatientsCount) == 0)) {
			System.exit(0);
		} else {
			FNC_Main fnc = new FNC_Main();
		}

	}

	void DataValidate(String path2) throws BiffException, IOException,
			URISyntaxException {

		String Dest = path2;
		String TargetFileName;
		String NewfileName;

		// //
		// TODO Auto-generated method stub

		FileInputStream FIS = new FileInputStream(IPPath);
		Workbook ws = Workbook.getWorkbook(FIS);
		Sheet sh = ws.getSheet(0);
		MKdir(Dest);
		File Logfile = new File(Dest+"//Logs"+formattedDate1+"//Trasactionlog_"+formattedDate+".log"); 
		Logfile.createNewFile();
		FileWriter writer = new FileWriter(Logfile); 
		
		for (int i = 1; i <= Integer.parseInt(PatientsCount); i++) {
			TargetFileName = sh.getCell(1, i).getContents();
			NewfileName = sh.getCell(2, i).getContents();
			
			try{
			//--- File Copy with New Name---//	
			File sourceFile = new File(Dest + "//" + TargetFileName+ "_retinareport.pdf");
			InputStream initialStream = new FileInputStream(sourceFile);
			File targetFile = new File(Dest + "//Updated"+formattedDate1+"//" + NewfileName+ "_retinareport.pdf");
			FileUtils.copyInputStreamToFile(initialStream, targetFile);
			
			//---File proccessed-----//
			
			File PrcdFile = new File(Dest + "//Processed"+formattedDate1+"//" + TargetFileName+ "_retinareport.pdf");
			FileUtils.moveFile(sourceFile, PrcdFile);
			
			//--- Logging the Transation--//
			writer.write(sysdate+"\t"+TargetFileName+"_retinareport.pdf"+"\t"+"Processed Successfully"); 
			writer.write(System.lineSeparator());
			}catch(IOException e){
				
			//--- Logging the Transation--//
				writer.write(sysdate+"\t"+TargetFileName+"_retinareport.pdf"+"\t"+"file not found");
				writer.write(System.lineSeparator());
			}	
		}
		writer.flush();
	    writer.close();
		JOptionPane.showMessageDialog(null, "COMPLETED");
		System.exit(0);
	}


	private static void MKdir(String oPPath2) {
		//---- creating the Directory to dump---//
		File dir = new File(oPPath2 + "//Updated"+formattedDate1+"//");
		dir.mkdir();
		File dir1 = new File(oPPath2 + "//Processed"+formattedDate1+"//");
		dir1.mkdir();
		File dir2 = new File(oPPath2 + "//Logs"+formattedDate1+"//");
		dir2.mkdir();

	}

}
