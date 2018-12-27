import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataHandler {

	static Date sysdate = Calendar.getInstance().getTime();
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
	static SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMYYYY");
	static String formattedDate = sdf.format(sysdate);
	static String formattedDate1 = sdf1.format(sysdate);
	static String IPPath, OPPath;
	static String ImagesCount;
	String JobId;
	String sourceurl;
	String Dest;

	DataHandler() {
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
				JobId = sh.getCell(0, i).getContents();
				sourceurl = sh.getCell(1, i).getContents();
				// System.out.println(sourceurl.lastIndexOf('/'));
				// System.out.println(sourceurl.substring(sourceurl.lastIndexOf('/')+1));
				File folder = new File(oPPath2 + "//" + JobId);
				if (!folder.exists()) {
					folder.mkdir();
				}

				System.out.println("opening connection");
				System.setProperty("https.proxyHost", "trproxy.rwe.com");
				System.setProperty("https.proxyPort", "443");

				// Create a new trust manager that trust all certificates
				TrustManager[] trustAllCerts = new TrustManager[] {
						new X509TrustManager() {
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return null;
							}

							public void checkClientTrusted(
									java.security.cert.X509Certificate[] certs,
									String authType) {
							}

							public void checkServerTrusted(
									java.security.cert.X509Certificate[] certs,
									String authType) {
							}
						} };

				// Activate the new trust manager
				try {
					SSLContext sc = SSLContext.getInstance("SSL");
					sc.init(null, trustAllCerts,
							new java.security.SecureRandom());
					HttpsURLConnection
							.setDefaultSSLSocketFactory(sc.getSocketFactory());
				} catch (Exception e) {
				}

				try {
					URL url = new URL(sourceurl);
					InputStream in = url.openStream();
					FileOutputStream fos;
					fos = new FileOutputStream(new File(
							Dest + "//" + JobId + "//" + sourceurl.substring(
									sourceurl.lastIndexOf('/') + 1)));
					int length = -1;
					byte[] buffer = new byte[2048];// buffer for portion of data
													// from connection
					while ((length = in.read(buffer)) > -1) {
						fos.write(buffer, 0, length);
					}
					fos.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "COMPLETED");
		System.exit(0);
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
			DataHandler fnc = new DataHandler();
		}

	}

}
