import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

public class ImageDirector {

	static Date sysdate = Calendar.getInstance().getTime();
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMYYYY");
	static String formattedDate = sdf.format(sysdate);
	static String formattedDate1 = sdf1.format(sysdate);
	static String IPPath, OPPath;
	static String ImagesCount;
	String JobId, Siteid;
	String sourceurl;
	String Dest,
	PatientId, 
	Lastname, 
	Firstname,
	Age, 
	Gender, 
	Diabeticstatus, 
	filename; 

	ImageDirector() {
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
					} catch (BiffException e) {
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

	protected void DataValidate(String oPPath2) throws BiffException {
			// TODO Auto-generated method stub
			
			try {
				Dest = oPPath2;
				FileInputStream FIS = new FileInputStream(IPPath);
				Workbook ws;
				ws = Workbook.getWorkbook(FIS);
				Sheet sh = ws.getSheet(0);
				for (int i = 1; i <= Integer.parseInt(ImagesCount); i++) {
					/*PatientId = sh.getCell(1, i).getContents();
					Lastname = sh.getCell(6, i).getContents();
					Firstname = sh.getCell(5, i).getContents();
					Age = sh.getCell(10, i).getContents();
					Gender = sh.getCell(9, i).getContents();
					Diabeticstatus = sh.getCell(15, i).getContents();
					filename = sh.getCell(16, i).getContents();*/
					JobId =sh.getCell(0, i).getContents();
					//filename =sh.getCell(1, i).getContents();
					Siteid =sh.getCell(1, i).getContents();
					

						File sourceFile = new File(Dest +"//"+ JobId);
						//File sourceFile = new File(Dest +"//"+ JobId+"//"+ filename.substring(filename.lastIndexOf('/')+1));
						// InputStream initialStream = new FileInputStream(sourceFile);
						//File targetFile = new File(Dest +"//"+PatientId+"_"+Lastname+"_"+Firstname+"_"+Gender+"_"+Age+"_"+Diabeticstatus+ "//");
						File targetFile = new File(Dest +"//"+Siteid);
						//sourceFile.renameTo(new File(Dest +"//"+Siteid+"//"+filename.substring(filename.lastIndexOf('/')+1)));
						if(sourceFile.exists())
						//FileUtils.moveFile(sourceFile, targetFile);
							Files.move(sourceFile.toPath(),targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							//sourceFile.renameTo(new File(Dest +"//"+Siteid+"//"+filename.substring(filename.lastIndexOf('/')+1)));		
						//sourceFile.renameTo(new File(Dest +"//"+Siteid+"//"+JobId));		
						
				}
			JOptionPane.showMessageDialog(null, "COMPLETED");
			System.exit(0);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create output directory is not exists
		ImagesCount = JOptionPane
				.showInputDialog(" Enter the number of Images: ");
		if (ImagesCount.equals("") || (Integer.parseInt(ImagesCount) == 0)) {
			System.exit(0);
		} else {
			@SuppressWarnings("unused")
			ImageDirector fnc = new ImageDirector();
		}

	}

}
