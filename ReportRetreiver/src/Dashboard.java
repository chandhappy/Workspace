import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.SharedAccessBlobPermissions;
import com.microsoft.azure.storage.blob.SharedAccessBlobPolicy;

@SuppressWarnings("serial")
public class Dashboard extends JFrame implements ActionListener {
	UtilDateModel datemodel, datemodel1;
	Properties p, p1;
	JDatePanelImpl datePanel, datePanel1;
	static JDatePickerImpl datePicker, datePicker1;
	String selectedFromDate, selectedToDate;
	JLabel LblFrom, LblTo, LblchooseDate, LblAccountId, LblSearchType,LblSiteName,
			LblAccountType, LblTotal, LblPending, LblReported, LblRejected,LblSiteId;
	JLabel LblTotalCount, LblPendingCount, LblReportedCount, LblRejectedCount;
	JTextField TxtSearch;
	JButton BtnGetStats, BtnExport;
	static JTable table;
	JPanel PnlSearch,PnlSiteName, PnlTotal, PnlPending, PnlReported, PnlRejected,
			PnlCenter;
	DefaultTableModel model;
	JCheckBox checkbox;
	Date date1, date2;
	JTextArea TxtArstatus;
	String Priority;
	public static ButtonGroup Btngrprad;
	public static JRadioButton RdbtnSiteid, RdbtnSiteName;
	String Dest = "C:\\Users\\INCBASHA\\Desktop\\P0186";
	String sql;
	String selectedDate;

	Dashboard() {
		// TODO Auto-generated constructor stub
		PnlSearch = new JPanel();

		datemodel = new UtilDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(datemodel, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datemodel.setValue(new java.util.Date());

		RdbtnSiteid = new JRadioButton("Site Id", true);
		RdbtnSiteName = new JRadioButton("Site Name");
		Btngrprad = new ButtonGroup();
		Btngrprad.add(RdbtnSiteid);
		Btngrprad.add(RdbtnSiteName);

		//PnlSearch.add(RdbtnSiteid);
		//PnlSearch.add(RdbtnSiteName);
		LblSiteId = new JLabel("Enter Site ID. ");
		PnlSearch.add(LblSiteId);
		
		TxtSearch = new JTextField(8);
		PnlSearch.add(TxtSearch);

		LblchooseDate = new JLabel("Select date");
		PnlSearch.add(LblchooseDate);
		PnlSearch.add(datePicker);

		BtnGetStats = new JButton("Get Stats ");
		BtnGetStats.addActionListener(this);
		PnlSearch.add(BtnGetStats);

		BtnExport = new JButton("Download All ");
		BtnExport.addActionListener(this);
		PnlSearch.add(BtnExport);

		add(PnlSearch, BorderLayout.NORTH);

		PnlCenter = new JPanel();
		
		PnlSiteName = new JPanel();
		PnlSiteName.setBackground(Color.WHITE);
		LblSiteName = new JLabel("SITE NAME");
		LblSiteName.setFont(new Font("Times New Roman", Font.BOLD, 21));
		LblSiteName.setAlignmentX(SwingConstants.CENTER);
		PnlSiteName.add(LblSiteName);
		

		PnlTotal = new JPanel(new BorderLayout());
		PnlTotal.setSize(200, 200);
		PnlTotal.setBackground(Color.WHITE);
		LblTotal = new JLabel("Total Studies");
		LblTotal.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlTotal.add(LblTotal, BorderLayout.NORTH);
		LblTotalCount = new JLabel("000");
		LblTotalCount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlTotal.add(LblTotalCount, BorderLayout.SOUTH);
		PnlTotal.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));
		PnlCenter.add(PnlTotal);

		PnlPending = new JPanel(new BorderLayout());
		PnlPending.setSize(200, 200);
		PnlPending.setBackground(Color.orange);
		LblPending = new JLabel("Pending Studies");
		LblPending.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlPending.add(LblPending, BorderLayout.NORTH);
		LblPendingCount = new JLabel("000");
		LblPendingCount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlPending.add(LblPendingCount, BorderLayout.SOUTH);
		PnlPending.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));
		PnlCenter.add(PnlPending);

		PnlReported = new JPanel(new BorderLayout());
		PnlReported.setSize(200, 200);
		PnlReported.setBackground(Color.GREEN);
		LblReported = new JLabel("Reported Studies");
		LblReported.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlReported.add(LblReported, BorderLayout.NORTH);
		LblReportedCount = new JLabel("000");
		LblReportedCount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlReported.add(LblReportedCount, BorderLayout.SOUTH);
		PnlReported.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));
		PnlCenter.add(PnlReported);

		PnlRejected = new JPanel(new BorderLayout());
		PnlRejected.setSize(200, 200);
		PnlRejected.setBackground(Color.GRAY);
		LblRejected = new JLabel("Rejected Studies");
		LblRejected.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlRejected.add(LblRejected, BorderLayout.NORTH);
		LblRejectedCount = new JLabel("000");
		LblRejectedCount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		PnlRejected.add(LblRejectedCount, BorderLayout.SOUTH);
		PnlRejected
				.setBorder(BorderFactory.createEmptyBorder(90, 90, 90, 90));
		PnlCenter.add(PnlRejected);
		PnlSiteName.add(PnlCenter);
		
		add(PnlSiteName);
		TxtArstatus = new JTextArea(1, 1);
		TxtArstatus.setEditable(false);
		add(TxtArstatus, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setTitle("DashBoard");
		setResizable(false);
		setSize(1350, 400);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub

		UIManager.setLookAndFeel(
				"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		new Dashboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BtnExport) {
			
			new Thread() {
				public void run() {

					try {
						if(TxtSearch.getText().trim().length()==8 && !TxtSearch.getText().equals(null)) {
							TxtArstatus.setText("In Progress...!");
							DownloadReport();
							TxtArstatus.setText("Completed...!");
						}
						else {
							TxtArstatus.setText("Check the details Entered...!");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (KeyManagementException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (StorageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
		if (e.getSource() == BtnGetStats) {
			new Thread() {
				public void run() {

					try {
						if(TxtSearch.getText().trim().length()==8 && !TxtSearch.getText().trim().equals(null)) {
							TxtArstatus.setText("In Progress...!");
							ReportedCount(TxtSearch.getText().trim());
							TxtArstatus.setText("Completed...!");
						}else {
							TxtArstatus.setText("Check the details Entered...!");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	String ReportedCount(String siteid) throws SQLException {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://visuhlthinddb.database.windows.net;"
				+ "database=KJNDCPStudies;user=vhopslogin;password=vhops@123";
		
		String connectionUrl1 = "jdbc:sqlserver://visuhlthinddb.database.windows.net;"
				+ "database=KJNDCPUsers;user=vhopslogin;password=vhops@123";

		// Declare the JDBC objects.
		Connection con = null;
		Connection con1 = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		Statement stmt3 = null;
		ResultSet rs3 = null;
		Statement stmt4 = null;
		ResultSet rs4 = null;
		
		try {
			// Establish the connection.
		
			selectedDate = new SimpleDateFormat("yyyy-MM-dd")
					.format((Date) datePicker.getModel().getValue());
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con1 = DriverManager.getConnection(connectionUrl1);
			
			
			
			// Create and execute an SQL statement that returns some data.
			String SQL = "Select count(KJNDCPStudies.dbo.Studies.Status)  from KJNDCPStudies.dbo.Studies  where (KJNDCPStudies.dbo.Studies.Status = 'ReportGenerated' or KJNDCPStudies.dbo.Studies.Status = 'Rejected' or KJNDCPStudies.dbo.Studies.Status = 'ReportDownloaded') and KJNDCPStudies.dbo.Studies.AcqisitionSiteId = '"+TxtSearch.getText().trim()+"' and KJNDCPStudies.dbo.Studies.CreatedAt between '"+selectedDate+" 00:00:01' and '"+selectedDate+" 23:59:59'  ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
			// Reported Count = ReportGenerated + ReportDownloadaed + Rejected
				LblReportedCount.setText(rs.getString(1));
			}

			String SQL1 = "Select count(KJNDCPStudies.dbo.Studies.Status)  from KJNDCPStudies.dbo.Studies  where KJNDCPStudies.dbo.Studies.Status = 'Rejected' and KJNDCPStudies.dbo.Studies.AcqisitionSiteId = '"+TxtSearch.getText().trim()+"' and KJNDCPStudies.dbo.Studies.CreatedAt between '"+selectedDate+" 00:00:01' and '"+selectedDate+" 23:59:59' ";
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery(SQL1);

			// Iterate through the data in the result set and display it.
			while (rs1.next()) {
			//	Rejected Count
				LblRejectedCount.setText(rs1.getString(1));
			}

			String SQL2 = "Select count(KJNDCPStudies.dbo.Studies.Status)  from KJNDCPStudies.dbo.Studies  where (KJNDCPStudies.dbo.Studies.Status = 'Submitted' or KJNDCPStudies.dbo.Studies.Status = 'Delegated') and KJNDCPStudies.dbo.Studies.AcqisitionSiteId = '"+TxtSearch.getText().trim()+"' and KJNDCPStudies.dbo.Studies.CreatedAt between '"+selectedDate+" 00:00:01' and '"+selectedDate+" 23:59:59' ";
			stmt2 = con.createStatement();
			rs2 = stmt2.executeQuery(SQL2);

			// Iterate through the data in the result set and display it.
			while (rs2.next()) {
				
			//Pending Count =  Submitted + Delegated
				LblPendingCount.setText(rs2.getString(1));
			}

			String SQL3 = "Select count(KJNDCPStudies.dbo.Studies.Status)  from KJNDCPStudies.dbo.Studies  where (KJNDCPStudies.dbo.Studies.Status = 'Rejected' or KJNDCPStudies.dbo.Studies.Status = 'ReportGenerated' or  KJNDCPStudies.dbo.Studies.Status = 'ReportDownloaded' or  KJNDCPStudies.dbo.Studies.Status = 'Submitted' or  KJNDCPStudies.dbo.Studies.Status = 'Delegated') and KJNDCPStudies.dbo.Studies.AcqisitionSiteId = '"+TxtSearch.getText().trim()+"' and KJNDCPStudies.dbo.Studies.CreatedAt between '"+selectedDate+" 00:00:01' and '"+selectedDate+" 23:59:59'";
			stmt3 = con.createStatement();
			rs3 = stmt3.executeQuery(SQL3);

			// Iterate through the data in the result set and display it.
			while (rs3.next()) {
				
				// TotalCount = Pending Count + Reported Count + Rejected rejected
				LblTotalCount.setText(rs3.getString(1));
			}

			String SQL4 = "Select KJNDCPUsers.dbo.Sites.Name from KJNDCPUsers.dbo.Sites where KJNDCPUsers.dbo.Sites.Id ='"+TxtSearch.getText().trim()+"';";
			stmt4 = con1.createStatement();
			rs4 = stmt4.executeQuery(SQL4);

			// Iterate through the data in the result set and display it.
			while (rs4.next()) {
				
				// TotalCount = Pending Count + Reported Count + Rejected rejected
				LblSiteName.setText(rs4.getString(1));
			}
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
					rs1.close();
					rs2.close();
					rs3.close();
					rs4.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
					stmt1.close();
					stmt2.close();
					stmt3.close();
					stmt4.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
		return null;
	}

	void DownloadReport() throws URISyntaxException, StorageException,
			InvalidKeyException, IOException, NumberFormatException,
			SQLException, ClassNotFoundException, NoSuchAlgorithmException, KeyManagementException {
try {
		
		String connectionUrl = "jdbc:sqlserver://visuhlthinddb.database.windows.net;"
				+ "database=KJNDCPUsers;user=vhopslogin;password=vhops@123";
		
		String connectionUrl1 = "jdbc:sqlserver://visuhlthinddb.database.windows.net;"
				+ "database=KJNDCPStudies;user=vhopslogin;password=vhops@123";
		
		String connectionUrl2 = "jdbc:sqlserver://visuhlthinddb.database.windows.net;"
				+ "database=ReportDb;user=vhopslogin;password=vhops@123";

		// Declare the JDBC objects.
		Connection con = null;
		Connection con1 = null;
		Connection con2 = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		String accountname;
		String s;
		String path;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(connectionUrl);
		con1 = DriverManager.getConnection(connectionUrl1);
		con2 = DriverManager.getConnection(connectionUrl2);
		// Create and execute an SQL statement that returns some data.
		String SQL = "Select KJNDCPUsers.dbo.Sites.Name from KJNDCPUsers.dbo.Sites where KJNDCPUsers.dbo.Sites.Id ='"+TxtSearch.getText().trim()+"';";
		stmt = con.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			selectedDate = new SimpleDateFormat("yyyy-MM-dd")
					.format((Date) datePicker.getModel().getValue());
			new File(Dest + "\\" + rs.getString(1)).mkdir();
			// Creating Account Path
			accountname = Dest + "\\" + rs.getString(1);

			// Creating SiteId Path
			new File(accountname.trim() + "\\" + TxtSearch.getText().trim()).mkdir();
			// Creating date Path
			new File(accountname.trim() + "\\" + TxtSearch.getText().trim() + "\\"+ selectedDate.toString()).mkdir();

			String SQL1 = "Select KJNDCPStudies.dbo.Studies.Id from KJNDCPStudies.dbo.Studies where (KJNDCPStudies.dbo.Studies.AcqisitionSiteId = '"+TxtSearch.getText().trim()+"') and ((KJNDCPStudies.dbo.Studies.Status = 'ReportGenerated') or (KJNDCPStudies.dbo.Studies.Status = 'ReportDownloaded'))and KJNDCPStudies.dbo.Studies.CreatedAt between '"+selectedDate+" 00:00:01' and '"+selectedDate+" 23:59:59';";
			stmt1 = con1.createStatement();
			rs1 = stmt1.executeQuery(SQL1);
			while (rs1.next()) {
				
				String SQL2 = "Select ReportDB.dbo.Reports.Location from ReportDB.dbo.Reports where ReportDB.dbo.Reports.StudyId  in ('"+rs1.getString(1)+"');";
				stmt2 = con2.createStatement();
				rs2 = stmt2.executeQuery(SQL2);
				while (rs2.next()) {
				
				 String Reportname=rs2.getString(1).substring(
							rs2.getString(1).lastIndexOf('/') + 1); 
				 
				 
			/*	 String[] str_array= Reportname.split("_");
				 s = new String(str_array[0]);
				System.out.println(s);/// need to do modifications
				
				// string check----------Start-------
					if (!isNumeric(s) && !isAlpha(s)) {
						java.util.regex.Matcher matcher = Pattern.compile("\\d+").matcher(s);
						matcher.find();
						java.util.regex.Matcher matcher2 = Pattern.compile("[a-zA-Z]+\\.?").matcher(s);
						matcher2.find();
						String s2 = matcher2.group().toString();
						System.out.println(matcher2.group().toString());
						new File(accountname.trim() + "\\"
								+ TxtSearch.getText().trim() + "\\"
								+ selectedDate + "\\"
								+ Long.valueOf(matcher.group()) + s2)
										.mkdir();
						path = accountname.trim() + "\\"
								+ TxtSearch.getText().trim() + "\\"
								+ selectedDate + "\\"
								+ Long.valueOf(matcher.group()) + s2 + "\\"
								+ Reportname;
					} else {*/
						new File(accountname.trim() + "\\"
								+ TxtSearch.getText().trim() + "\\"
								+ selectedDate).mkdir();
						path = accountname.trim() + "\\"
								+ TxtSearch.getText().trim() + "\\"
								+ selectedDate +"\\" + Reportname;
				//	}
				// String Check------- Close-------
				String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=kjasiaapp;AccountKey=nA1XwHsi31dn7nFDnCmiWAdtbADr1DYD5OUn9R3vsZbo3FP2wibVVnlzJ4Q6/ZI01v8YdjLvZ8cycSbJweFISQ==;EndpointSuffix=core.windows.net";
				CloudStorageAccount account = CloudStorageAccount
						.parse(storageConnectionString);

				// Create a blob service client
				CloudBlobClient blobClient = account.createCloudBlobClient();
				CloudBlobContainer container = blobClient
						.getContainerReference("pdfsastest");
				if (!container.exists()) {
					System.out.println(
							String.format("Container '%s' not found", ""));
					System.exit(1);
				}

				BlobContainerPermissions permissions = new BlobContainerPermissions();

				// define a Read-only base policy for downloads
				SharedAccessBlobPolicy readPolicy = new SharedAccessBlobPolicy();
				readPolicy.setPermissions(
						EnumSet.of(SharedAccessBlobPermissions.READ));
				permissions.getSharedAccessPolicies().put("DownloadPolicy",
						readPolicy);
				container.uploadPermissions(permissions);
				// define rights you want to bake into the SAS

				SharedAccessBlobPolicy itemPolicy = new SharedAccessBlobPolicy();

				Calendar cal = Calendar.getInstance();
				cal.setTimeZone(TimeZone.getTimeZone("UTC"));

				// Define the start and end time to grant permissions.
				Date startTime = cal.getTime();
				cal.add(Calendar.HOUR, 9);
				Date expirationTime = cal.getTime();

				itemPolicy.setSharedAccessStartTime(startTime);
				itemPolicy.setSharedAccessExpiryTime(expirationTime);

				String FileToDownload = rs2.getString(1).substring(51); //need to do modifications
                //System.out.println(rs2.getString(1).substring(55));
				// get reference to the Blob you want to generate the SAS for:
				CloudBlockBlob blob = container
						.getBlockBlobReference(FileToDownload);

				// generate Download SAS
				String sasToken = blob.generateSharedAccessSignature(itemPolicy,
						"DownloadPolicy");
				// the SAS URL is actually concatenation of the blob URI and
				// the generated token:
				String sasUri = String.format("%s?%s", blob.getUri(), sasToken);
				URI url = new URI(sasUri);
				//System.out.println(url);
				//Azure Cloud Downloader
				
				/*CloudBlockBlob blob1 = new CloudBlockBlob(url);
				blob1.downloadToFile(path);*/
				
				// using regular stream downloader
				try {
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
					SSLContext sc = SSLContext.getInstance("SSL");
					sc.init(null, trustAllCerts,
							new java.security.SecureRandom());
					/*HttpsURLConnection
							.setDefaultSSLSocketFactory(sc.getSocketFactory());
					System.setProperty("http.agent", "Chrome");*/
				    URLConnection urlConn = url.toURL().openConnection();
				    //urlConn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");

				    //String contentType = urlConn.getContentType();

				    //System.out.println("contentType:" + contentType);
				   // urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				    urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				   // addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				    InputStream in = urlConn.getInputStream();
					//InputStream in = url.toURL().openStream();
					FileOutputStream fos;
					fos = new FileOutputStream(new File(path));
					int length = -1;
					byte[] buffer = new byte[2048];// buffer for portion of database
													// from connection
					while ((length = in.read(buffer)) > -1) {
						fos.write(buffer, 0, length);
					}
					fos.close();
					in.close();
				} catch (FileNotFoundException exception) {
			        // Output expected FileNotFoundExceptions.
			        System.out.println(exception);
			        CloudBlockBlob blob1 = new CloudBlockBlob(url);
					blob1.downloadToFile(path);
			        }
				catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
			        CloudBlockBlob blob1 = new CloudBlockBlob(url);
					blob1.downloadToFile(path);
				}
				}
			}
		}
		}finally {
			
		}
			
	

	}
	boolean isNumeric(String str) {
		String pattern= "^[0-9]*$";
	    return str.matches(pattern);
	}
	
	boolean isAlpha(String str) {
		String pattern= "^[a-zA-Z]*$";
	    return str.matches(pattern);
	}
}
