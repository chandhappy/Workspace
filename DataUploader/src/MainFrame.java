import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	JScrollPane scrollTree;
	DefaultMutableTreeNode child;
	File file;
	public static final String storageConnectionString = "DefaultEndpointsProtocol=http;"
			+ "AccountName=dcpkgn2014;"
			+ "AccountKey=Jk1noVG8EPe/058Suz0SDtUei5Ova8Xe6w+CxVRGmHaefk5JHv6fE3SuXt4rjTRhms3K2PKMZlSjZS/raxKlIg==";
	public static MainFrame fe = null;
	JLabel LblDate, LblFolder, LblPatientId, LblFirstName, LblLastName, LblAge,
			LblGender, Lblempty1, Lblempty2, Lblempty3, LblDiabeticStatus,
			Lbladdcomments;
	static JTextField TxtFolder, TxtPatientId, TxtFirstName, TxtLastName,
			TxtAge;
	JButton BtnClear, BtnSave, BtnUpload, BtnFetchImages, BtnShowPatients,
			BtnShwPatients, BtnCheck;
	Font FntLbl = new Font("Times New Roman", Font.BOLD, 14);
	Font FntTable = new Font("Arial", Font.PLAIN, 14);
	JScrollPane ScrollPaneStatus, TableContainer;
	JTabbedPane Tabpane;
	JPanel PnlTop, PnlBottom, PnlPatientCreation, PnlDataUpload, PnlUploadBtn,
			PnlDemographics, PnlPatBtn, Pnlleft, PnlRight, PnlGender,
			PnlCmplUpload, PnlShowBtn;
	public static JTextArea TxtAreaComments, Txtaddcomments;
	static JTable table;
	JTable table1;
	String[] columnNames = { "Select", "StudyDate", "PatientID", "FirstName",
			"LastName", "Pat.Folder ID", "ImagesCount" };
	String[] columnNames1 = { "StudyDate", "PatientID", "FirstName",
			"LastName", "Pat.Folder ID" };
	Object[][] data = null;
	File[] drives;
	FileSystemView fsv;
	static JComboBox<String> ageComboBox, diabeticstat;
	DefaultTableModel model, model1;
	ButtonGroup bG;
	JRadioButton Male, Female, Other;
	UtilDateModel datemodel;
	Properties p;
	JDatePanelImpl datePanel;
	static JDatePickerImpl datePicker;
	String selectedDate;
	static String selectedDate1;
	static ArrayList<Integer> SelectedCell;
	static String Patfol, Patid, Patdate;
	String[] age = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
			"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43",
			"44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
			"55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65",
			"66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76",
			"77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
			"88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98",
			"99", "100" };

	String[] diabeticstatus = { "", "None", "Not Known", "Diabetic",
			"Diabetic Suspect", "Diabetic Suspect with Family history" };
	static String Gender;
	File sourceDirectory, rootFile;
	DefaultMutableTreeNode sourceRoot;
	DefaultTreeModel sourceModel;
	JTree sourceTree;
	int count;
	static String workingDir = System.getProperty("user.dir");
	File Dirc = new File(workingDir + "\\Resources\\Archive\\");

	public MainFrame() throws URISyntaxException {
		// TODO Auto-generated constructor stub
		//

		// ---Top Panel with Date-----------------------------//
		PnlTop = new JPanel();
		LblDate = new JLabel("Date* : ");
		LblDate.setFont(FntLbl);
		PnlTop.add(LblDate);
		datemodel = new UtilDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(datemodel, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datemodel.setValue(new java.util.Date());
		PnlTop.add(datePicker);
		add(PnlTop, BorderLayout.NORTH);
		// ------------PnlCenter-------------------------//
		Tabpane = new JTabbedPane();
		Tabpane.setBorder(BorderFactory.createTitledBorder(" "));
		PnlPatientCreation = new JPanel(new BorderLayout());
		Tabpane.add("Patient Creation", PnlPatientCreation);
		PnlDemographics = new JPanel();
		PnlDemographics.setBorder(BorderFactory
				.createTitledBorder("Demographics"));
		PnlDemographics.setLayout(new GridLayout(10, 2));

		LblFirstName = new JLabel("First Name");
		LblFirstName.setFont(FntLbl);
		PnlDemographics.add(LblFirstName);
		TxtFirstName = new JTextField(24);
		PnlDemographics.add(TxtFirstName);

		LblLastName = new JLabel("Last Name*");
		LblLastName.setFont(FntLbl);
		PnlDemographics.add(LblLastName);
		TxtLastName = new JTextField(24);
		PnlDemographics.add(TxtLastName);

		LblAge = new JLabel("Age*");
		LblAge.setFont(FntLbl);
		PnlDemographics.add(LblAge);

		ageComboBox = new JComboBox<String>(age);
		PnlDemographics.add(ageComboBox);

		LblGender = new JLabel("Gender");
		LblGender.setFont(FntLbl);
		PnlDemographics.add(LblGender);
		PnlGender = new JPanel();
		Male = new JRadioButton("Male", true);
		Male.setFont(FntLbl);
		Female = new JRadioButton("Female");
		Female.setFont(FntLbl);
		Other = new JRadioButton("Other");
		Other.setFont(FntLbl);
		bG = new ButtonGroup();
		bG.add(Male);
		bG.add(Female);
		bG.add(Other);
		PnlGender.add(Male);
		PnlGender.add(Female);
		PnlGender.add(Other);
		PnlDemographics.add(PnlGender);

		LblPatientId = new JLabel("Patient ID*");
		LblPatientId.setFont(FntLbl);
		PnlDemographics.add(LblPatientId);
		TxtPatientId = new JTextField(16);
		PnlDemographics.add(TxtPatientId);

		LblFolder = new JLabel("Image Folder ID*(ex:P0001)* ");
		LblFolder.setFont(FntLbl);
		PnlDemographics.add(LblFolder);
		TxtFolder = new JTextField(5);
		PnlDemographics.add(TxtFolder);

		LblDiabeticStatus = new JLabel("Diabetic Status *");
		LblDiabeticStatus.setFont(FntLbl);
		PnlDemographics.add(LblDiabeticStatus);
		diabeticstat = new JComboBox<String>(diabeticstatus);
		PnlDemographics.add(diabeticstat);

		Lbladdcomments = new JLabel("Additional Comments");
		Lbladdcomments.setFont(FntLbl);
		PnlDemographics.add(Lbladdcomments);
		Txtaddcomments = new JTextArea(10, 30);
		PnlDemographics.add(Txtaddcomments);

		PnlPatientCreation.add(PnlDemographics, BorderLayout.CENTER);

		PnlPatBtn = new JPanel(new GridLayout(1, 5));
		Lblempty1 = new JLabel();
		PnlPatBtn.add(Lblempty1);
		BtnClear = new JButton("Clear");
		BtnClear.addActionListener(this);
		BtnClear.setFont(FntLbl);
		PnlPatBtn.add(BtnClear);
		Lblempty2 = new JLabel();
		PnlPatBtn.add(Lblempty2);
		BtnSave = new JButton("Save");
		BtnSave.addActionListener(this);
		BtnSave.setFont(FntLbl);
		PnlPatBtn.add(BtnSave);
		Lblempty3 = new JLabel();
		PnlPatBtn.add(Lblempty3);
		PnlPatientCreation.add(PnlPatBtn, BorderLayout.SOUTH);
		Pnlleft = new JPanel();
		Lblempty3 = new JLabel("");
		Pnlleft.add(Lblempty3);
		PnlPatientCreation.add(Pnlleft, BorderLayout.EAST);
		PnlRight = new JPanel();
		Lblempty3 = new JLabel("");
		PnlRight.add(Lblempty3);
		PnlPatientCreation.add(PnlRight, BorderLayout.WEST);

		// -----PnlDataUpload-----//
		PnlDataUpload = new JPanel(new BorderLayout());
		Tabpane.add("Pending Studies", PnlDataUpload);
		// model.setColumnIdentifiers(columnNames);
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer,
					int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(
						renderer, rowIndex, columnIndex);

				if ((Integer.parseInt(getValueAt(rowIndex, 6).toString()) < 1)
						|| (Integer
								.parseInt(getValueAt(rowIndex, 6).toString()) > 8)) {
					component.setBackground(new Color(255, 111, 111));
				} else {
					component.setBackground(table.getBackground());
					component.setForeground(table.getForeground());
				}
				return component;
			}
		};
		model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (getColumnName(columnIndex).equals("Select")) {
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 0)
					return true;
				else
					return false;
			}
		};

		table.setModel(model);
		table.getColumn("Select").setPreferredWidth(60);
		table.getColumn("Select").setMaxWidth(60);
		table.getColumn("Select").setMinWidth(60);
		table.setFont(FntTable);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setEditingColumn(6);
		table.setRowHeight(30);
		table.setSelectionBackground(Color.blue);
		JScrollPane TableContainer = new JScrollPane(table);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableContainer.setMinimumSize(new Dimension(300, 100));
		PnlDataUpload.add(TableContainer);

		// ----------------------------------------------------------
		PnlUploadBtn = new JPanel(new GridLayout(1, 5));

		BtnCheck = new JButton("(Un)Check All");
		BtnCheck.setFont(FntLbl);
		BtnCheck.addActionListener(this);
		PnlUploadBtn.add(BtnCheck);

		BtnShowPatients = new JButton("ShowPending Patients");
		BtnShowPatients.setFont(FntLbl);
		BtnShowPatients.addActionListener(this);
		PnlUploadBtn.add(BtnShowPatients);

		BtnFetchImages = new JButton(" Fetch Images");
		BtnFetchImages.setFont(FntLbl);
		BtnFetchImages.addActionListener(this);
		PnlUploadBtn.add(BtnFetchImages);

		BtnUpload = new JButton("UploadData");
		BtnUpload.setFont(FntLbl);
		BtnUpload.addActionListener(this);
		PnlUploadBtn.add(BtnUpload);
		PnlDataUpload.add(PnlUploadBtn, BorderLayout.SOUTH);
		// ------Completed Studies-------------------///

		PnlCmplUpload = new JPanel(new BorderLayout());
		Tabpane.add("Uploded Studies", PnlCmplUpload);
		table1 = new JTable();
		model1 = new DefaultTableModel(data, columnNames1);
		table1.setModel(model1);
		table1.getColumn("StudyDate").setPreferredWidth(10);
		table1.getColumn("PatientID").setPreferredWidth(16);
		table1.getColumn("FirstName").setPreferredWidth(16);
		table1.getColumn("LastName").setPreferredWidth(16);
		table1.getColumn("Pat.Folder ID").setPreferredWidth(5);
		table1.setFont(FntTable);
		table1.setRowSelectionAllowed(true);
		table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table1.setFillsViewportHeight(true);
		table1.setEditingColumn(6);
		JScrollPane TableContainer1 = new JScrollPane(table1);
		PnlCmplUpload.add(TableContainer1);
		PnlShowBtn = new JPanel(new GridLayout(1, 3));

		Lblempty1 = new JLabel();
		PnlShowBtn.add(Lblempty1);

		BtnShwPatients = new JButton("Show Uploaded Patients");
		BtnShwPatients.setFont(FntLbl);
		BtnShwPatients.addActionListener(this);
		PnlShowBtn.add(BtnShwPatients);

		PnlCmplUpload.add(PnlShowBtn, BorderLayout.SOUTH);
		add(Tabpane);
		sourceDirectory = new File(workingDir + "\\Resources\\Archive\\");
		sourceRoot = new DefaultMutableTreeNode(sourceDirectory);
		sourceModel = new DefaultTreeModel(sourceRoot);
		sourceTree = new JTree();
		sourceTree.setExpandsSelectedPaths(true);
		sourceTree.setModel(sourceModel);
		sourceTree.setShowsRootHandles(true);
		sourceTree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {

				TreePath tp = e.getNewLeadSelectionPath();
				if (tp != null) {
					Desktop desktop = Desktop.getDesktop();
					File dirToOpen = null;
					try {
						dirToOpen = new File(workingDir
								+ "\\Resources\\Archive\\");
						desktop.open(dirToOpen);
					} catch (IllegalArgumentException | IOException iae) {
						System.out.println("File Not Found");
					}
				}

			}
		});
		sourceModel.reload();
		rootFile = (File) sourceRoot.getUserObject();
		// System.out.println(rootFile);
		addFiles(rootFile, sourceModel, sourceRoot);
		scrollTree = new JScrollPane(sourceTree);
		scrollTree.setViewportView(sourceTree);

		scrollTree.getHorizontalScrollBar().setValue(0);
		scrollTree.setBorder(BorderFactory.createTitledBorder(" "));
		add(scrollTree, BorderLayout.EAST);

		// ------------PnlBottom--------------------------//
		PnlBottom = new JPanel();
		PnlBottom.setBorder(BorderFactory.createTitledBorder(" "));
		TxtAreaComments = new JTextArea(1, 80);
		ScrollPaneStatus = new JScrollPane(TxtAreaComments);
		TxtAreaComments.setEditable(false);
		PnlBottom.add(ScrollPaneStatus);
		add(PnlBottom, BorderLayout.SOUTH);
		// -----------------Default Frame-----------------//
		setResizable(false);
		setTitle("Data Uploader");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(920, 720);
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, URISyntaxException {
		// TODO Auto-generated method stub
		if (fe == null) {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			fe = new MainFrame();
			fe.setLocationRelativeTo(null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == BtnCheck) {

			if (Boolean.valueOf(table.getValueAt(0, 0).toString()
					.equals("false"))) {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(true, i, 0);
				}
			} else {
				for (int i = 0; i < table.getRowCount(); i++) {
					table.setValueAt(false, i, 0);
				}
			}

		}
		if (e.getSource() == BtnClear) {
			TxtFolder.setText("");
			TxtPatientId.setText("");
			TxtLastName.setText("");
			TxtFirstName.setText("");
			ageComboBox.setSelectedItem(null);
			Male.setSelected(true);
			TxtAreaComments.setText("");
			diabeticstat.setSelectedItem(null);
			Txtaddcomments.setText("");
		}
		if (e.getSource() == BtnSave) {
			Gender = "";
			if (Male.isSelected()) {
				Gender = "Male";
			}
			if (Female.isSelected()) {
				Gender = "Female";
			}
			if (Other.isSelected()) {
				Gender = "Other";
			}
			if ((!(TxtFolder.getText().isEmpty()))
					&& (TxtFolder.getText().length() == 5)
					&& (TxtFolder.getText().startsWith("P0") | TxtFolder
							.getText().startsWith("p0"))
					&& (!(TxtPatientId.getText().isEmpty()))
					&& (!(ageComboBox.getSelectedItem().toString().isEmpty()))
					&& ((Date) datePicker.getModel().getValue() != null)
					&& (!(TxtLastName.getText().isEmpty()))
					&& (!(diabeticstat.getSelectedItem().toString().isEmpty()))) {

				selectedDate = new SimpleDateFormat("yyyy-MM-dd")
						.format((Date) datePicker.getModel().getValue());
				Connection c = null;
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet rs;

				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:" + workingDir
							+ "\\Resources\\DataUploaderDB.db");
					String studycheck = "select * from PatientsData where StudyDate='"
							+ selectedDate
							+ "' And FolderID ='"
							+ TxtFolder.getText().toUpperCase() + "'";
					stmt1 = c.prepareStatement(studycheck);
					rs = stmt1.executeQuery();
					if (!rs.next()) {
						c.setAutoCommit(false);
						String sql = "INSERT INTO PatientsData (PatFName,PatLName,PatAge,PatGender,PatID,FolderID,StudyDate,CreatedAt,UploadStatus,DiabeticStatus,CommentsForGrading)"
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
						stmt = c.prepareStatement(sql);
						stmt.setString(1, TxtFirstName.getText());
						stmt.setString(2, TxtLastName.getText());
						stmt.setInt(3, Integer.parseInt(ageComboBox
								.getSelectedItem().toString()));
						stmt.setString(4, Gender);
						stmt.setString(5, TxtPatientId.getText());
						stmt.setString(6, TxtFolder.getText().toUpperCase());
						stmt.setString(7, selectedDate);
						stmt.setString(8, new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(Calendar
								.getInstance().getTime()));
						stmt.setString(9, "Pending");
						stmt.setString(10, diabeticstat.getSelectedItem()
								.toString());
						stmt.setString(11, Txtaddcomments.getText());
						stmt.executeUpdate();
						stmt.close();
						c.commit();
						c.close();
						TxtAreaComments.setText("Saved Successfully...!");
						TxtFolder.setText("");
						TxtPatientId.setText("");
						TxtLastName.setText("");
						TxtFirstName.setText("");
						ageComboBox.setSelectedItem(null);
						Male.setSelected(true);
						diabeticstat.setSelectedItem(null);
						Txtaddcomments.setText("");
					} else {
						TxtAreaComments
								.setText("Image Folder already Mapped.. Please check");
					}
				} catch (Exception e1) {
					TxtAreaComments.setText(e1.getMessage());
					e1.printStackTrace();
				}
			} else {
				TxtAreaComments
						.setText("Please Enter the Mandatory (*) Feilds ...  & Check Values...!");
			}
		}
		if (e.getSource() == BtnFetchImages) {

			if (table.getRowCount() != 0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0)
							.toString());
					if (isChecked) {
						// get the values of the columns you need.
						Patfol = table.getValueAt(i, 5).toString();
						/*SwingUtilities.invokeLater(new Runnable() {
							public void run() {*/
								fsv = FileSystemView.getFileSystemView();
								drives = File.listRoots();

								for (File aDrive : drives) {
									String vispath = "";
									try {
										vispath = fsv
												.getSystemDisplayName(aDrive)
												.substring(5).trim();
									} catch (Exception e2) {

									}
									if (vispath.equals("VISUSCOUT")) {
										TxtAreaComments
												.setText("Please Wait Image Fetching Inprogress...!");
										String path = aDrive + "\\DCIM\\"
												+ Patfol;

										File dir = new File(path);
										if (dir.isDirectory()) {
											FetchImages(dir);
											sourceRoot.removeFromParent();
											sourceRoot.removeAllChildren();
											sourceModel.reload();
											addFiles(rootFile, sourceModel,
													sourceRoot);

										} else {
											TxtAreaComments
													.setText("Folder not found...!");
										}

										break;
									} else {
										TxtAreaComments
												.append("Device Not Found...!");
									}

								}
						/*	}
						});*/
					} else {
						TxtAreaComments.setText("select a study to fetch...!");
					}

				}
			} else {
				TxtAreaComments.setText("select a study to fetch...!");
			}
		}
		if (e.getSource() == BtnUpload) {

			if (netIsAvailable()) {
				/*SwingUtilities.invokeLater(new Runnable() {
					public void run() {*/
						try {
							if ((Date) datePicker.getModel().getValue() != null) {
								UploadPatientsToAzureDB();
							} else {
								TxtAreaComments
										.setText("Please select the date ...!");
							}

						} catch (ClassNotFoundException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				/*	}
				});*/
			} else {
				TxtAreaComments.setText("Ｕｆｆ．．．Ｎｏ　Ｉｎｔｅｒｎｅｔ　ｃｏｎｎｅｃｔｉｏｎ．．．．．！");
			}
		}
		if (e.getSource() == BtnShowPatients) {

			if ((Date) datePicker.getModel().getValue() != null) {
				selectedDate = new SimpleDateFormat("yyyy-MM-dd")
						.format((Date) datePicker.getModel().getValue());
				model.setRowCount(0);
				Connection c1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs, rs1;
				try {
					Class.forName("org.sqlite.JDBC");
					c1 = DriverManager.getConnection("jdbc:sqlite:"
							+ workingDir + "\\Resources\\DataUploaderDB.db");
					c1.setAutoCommit(false);
					String sql1 = "SELECT count(*) FROM PatientsData where (UploadStatus='Pending') & (StudyDate='"
							+ selectedDate + "')";
					stmt1 = c1.prepareStatement(sql1);
					rs1 = stmt1.executeQuery();
					TxtAreaComments
							.setText("Patients found : " + rs1.getInt(1));
					String sql = "SELECT StudyDate,PatID,PatFName,PatLName,FolderID FROM PatientsData where ( UploadStatus='Pending') & (StudyDate='"
							+ selectedDate + "')";
					stmt1 = c1.prepareStatement(sql);
					rs = stmt1.executeQuery();
					while (rs.next()) {
						File dir = new File(workingDir
								+ "\\Resources\\Archive\\" + selectedDate
								+ "\\" + rs.getString("FolderId"));
						if (dir.isDirectory()) {
							File[] content = dir.listFiles();
							count = content.length;
						} else {
							count = 0;

						}
						model.addRow(new Object[] { new Boolean(false),
								rs.getString("StudyDate"),
								rs.getString("PatID"),
								rs.getString("PatFName"),
								rs.getString("PatLName"),
								rs.getString("FolderID"), count });
					}
					stmt1.close();
					c1.commit();
					c1.close();

				} catch (Exception e2) {
					TxtAreaComments.setText(e2.getMessage());
					System.out.println(e2.getMessage());

				}
			} else {
				TxtAreaComments.setText("Please select the date ...!");
			}
		}

		if (e.getSource() == BtnShwPatients) {
			if ((Date) datePicker.getModel().getValue() != null) {
				selectedDate = new SimpleDateFormat("yyyy-MM-dd")
						.format((Date) datePicker.getModel().getValue());
				model1.setRowCount(0);
				Connection c1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs, rs1;
				try {
					Class.forName("org.sqlite.JDBC");
					c1 = DriverManager.getConnection("jdbc:sqlite:"
							+ workingDir + "\\Resources\\DataUploaderDB.db");
					c1.setAutoCommit(false);
					String sql1 = "SELECT count(*) FROM PatientsData where ( UploadStatus='Completed') & (StudyDate='"
							+ selectedDate + "')";
					stmt1 = c1.prepareStatement(sql1);
					rs1 = stmt1.executeQuery();
					TxtAreaComments
							.setText("Patients found : " + rs1.getInt(1));
					String sql = "SELECT StudyDate,PatID,PatFName,PatLName,FolderID FROM PatientsData where ( UploadStatus='Completed') & (StudyDate='"
							+ selectedDate + "')";
					stmt1 = c1.prepareStatement(sql);
					rs = stmt1.executeQuery();
					while (rs.next()) {
						model1.addRow(new Object[] { rs.getString("StudyDate"),
								rs.getString("PatID"),
								rs.getString("PatFName"),
								rs.getString("PatLName"),
								rs.getString("FolderID") });
					}
					stmt1.close();
					c1.commit();
					c1.close();

				} catch (Exception e2) {
					TxtAreaComments.setText(e2.getMessage());
					System.out.println(e2.getMessage());

				}
			} else {
				TxtAreaComments.setText("Please Enter the date ...!");
			}
		}

	}

	private static void FetchImages(File dir) {
		// TODO Auto-generated method stub
		try {
			selectedDate1 = new SimpleDateFormat("yyyy-MM-dd")
					.format((Date) datePicker.getModel().getValue());
			String destination = new File(workingDir + "\\Resources\\Archive\\"
					+ selectedDate1 + "\\").getAbsolutePath();
			File destDir = new File(destination);
			String source = dir.toString();
			File srcDir = new File(source);
			FileUtils.copyDirectoryToDirectory(srcDir, destDir);
			// ---Creating a backup of Images---//
			String backuppath = new File(workingDir
					+ "\\Resources\\BackupArchive\\" + selectedDate1 + "\\")
					.getAbsolutePath();
			File Backup = new File(backuppath);
			FileUtils.copyDirectoryToDirectory(srcDir, Backup);
			TxtAreaComments.setText("Image Fetching Completed...!");
		} catch (IOException e) {
			TxtAreaComments.setText(e.getMessage());
			System.out.println(e.getMessage());
			TxtAreaComments.setText("Something went wrong please retry...!");

		}
	}

	private static void UploadPatientsToAzureDB()
			throws ClassNotFoundException, SQLException {
		// -----------getting only selected studies for
		// upload-------------------------------------//
		if (table.getRowCount() != 0) {
			for (int i = 0; i < table.getRowCount(); i++) {
				Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0)
						.toString());
				if (isChecked) {
					Patdate = table.getValueAt(i, 1).toString();
					Patid = table.getValueAt(i, 2).toString();
					if (Integer.parseInt(table.getValueAt(i, 6).toString()) > 0
							&& Integer.parseInt(table.getValueAt(i, 6)
									.toString()) < 9) {

						// -----Local Sqlite db search for patients
						// selected----------------------//
						Connection c2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2;
						try {
							TxtAreaComments
									.setText("Please wait.. Studies are getting uploaded...!");
							Class.forName("org.sqlite.JDBC");

							c2 = DriverManager.getConnection("jdbc:sqlite:"
									+ workingDir
									+ "\\Resources\\DataUploaderDB.db");
							c2.setAutoCommit(false);
							String sql2 = "SELECT PatID,PatFName,PatLName,PatAge,PatGender,CreatedAt,FolderId,DiabeticStatus,CommentsForGrading FROM PatientsData where (UploadStatus='Pending') & (StudyDate ='"
									+ Patdate + "') & (PatID='" + Patid + "')";
							stmt2 = c2.prepareStatement(sql2);
							rs2 = stmt2.executeQuery();
							while (rs2.next()) {
								String DB_URL = "jdbc:mysql://kjndemo.southeastasia.cloudapp.azure.com:3306/dcpnew";
								String USER = "root";
								String PASS = "Motif@123";
								Connection conn = null;
								PreparedStatement stmt = null;

								// ---- Inserting study Information into cloud
								// DB-----//

								Class.forName("com.mysql.jdbc.Driver");
								conn = DriverManager.getConnection(DB_URL,
										USER, PASS);
								String sql = "INSERT INTO dcpnew.studies (AcquisitionSiteId,StudyId,Priority,DeviceId,SeriesNumber,PatientId,PatientName,Age,Sex,Status,CreatedAt,CreatedBy,UpdatedAt,UpdatedBy,IsActive,Source,DiabeticInfo,JsonText) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								stmt = conn.prepareStatement(sql,
										Statement.RETURN_GENERATED_KEYS);
								stmt.setString(1, "20000112");
								stmt.setString(2, new SimpleDateFormat(
										"yyyyMMddHHmmss").format(Calendar
										.getInstance().getTime()));
								stmt.setString(3, "0");
								stmt.setString(4, "62");
								stmt.setString(5, "1");
								stmt.setString(6, rs2.getString("PatID"));
								stmt.setString(7, rs2.getString("PatFName")
										+ " " + rs2.getString("PatLName"));
								stmt.setString(8, rs2.getString("PatAge")
										.toString());
								stmt.setString(9, rs2.getString("PatGender"));
								stmt.setString(10, "Draft");
								stmt.setString(11, rs2.getString("CreatedAt"));
								stmt.setString(12,
										"67581afc-c23a-4a39-bd2a-27f4f60e28e6");
								stmt.setString(13, new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss").format(Calendar
										.getInstance().getTime()));
								stmt.setString(14,
										"67581afc-c23a-4a39-bd2a-27f4f60e28e6");
								stmt.setString(15, "1");
								stmt.setString(16, "Web");
								stmt.setString(17,
										rs2.getString("DiabeticStatus"));
								String json = JsonFromat(
										rs2.getString("PatID"),
										rs2.getString("PatFName"),
										rs2.getString("PatLName"),
										rs2.getString("PatAge").toString(),
										rs2.getString("DiabeticStatus"),
										rs2.getString("PatGender"),
										rs2.getString("CreatedAt"),
										rs2.getString("CommentsForGrading"));
								stmt.setString(18,
										json.substring(1, json.length() - 1));
								stmt.executeUpdate();
								ResultSet rs3 = stmt.getGeneratedKeys();
								if (rs3.next()) {
									UploadImagesToAzure(
											workingDir
													+ "\\Resources\\Archive\\"
													+ Patdate + "\\"
													+ rs2.getString("FolderId"),
											rs3.getInt(1),
											rs2.getString("PatID"));
								}
								stmt.close();
								conn.close();
							}

							stmt2.close();
							c2.commit();
							c2.close();

						} catch (Exception e2) {
							TxtAreaComments.setText(e2.getMessage());
							System.out.println(e2.getMessage());

						}
					} else {
						TxtAreaComments
								.setText("Please check the images count for selected studies..");
					}
				} /*
				 * else { TxtAreaComments
				 * .setText("Please select the studies to upload..."); }
				 */
			}
		} else {
			TxtAreaComments.setText("search & Select studies to upload...");
		}
	}

	private static void UploadImagesToAzure(String path, int id, String pid) {

		String patid = pid;
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] content = dir.listFiles();
			for (int i = 0; i < content.length; i++) {
				try {
					String DB_URL = "jdbc:mysql://kjndemo.southeastasia.cloudapp.azure.com:3306/dcpnew";
					String USER = "root";
					String PASS = "Motif@123";
					Connection conn = null;
					PreparedStatement stmt = null, stmt2 = null;

					// ---- Inserting Media Information into cloud DB-----//

					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					String sql = "insert into dcpnew.medias (DCPStudyID,URL,Type,IsGradable,CreatedAt,CreatedBy,UpdatedAt,UpdatedBy,IsActive,IsDeleted,Description,Size,HashCode)Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					stmt.setString(3, "Media");
					stmt.setString(4, "1");
					stmt.setString(5, new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(Calendar
							.getInstance().getTime()));
					stmt.setString(6, "AC");
					stmt.setString(7, new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(Calendar
							.getInstance().getTime()));
					stmt.setString(8, "AC");
					stmt.setString(9, "1");
					stmt.setString(10, "0");
					String url = content[i].getAbsolutePath();

					// --- pushing Image to cloud container-----//

					CloudStorageAccount storageAccount = CloudStorageAccount
							.parse(storageConnectionString);
					CloudBlobClient blobClient = storageAccount
							.createCloudBlobClient();
					CloudBlobContainer container = blobClient
							.getContainerReference("group-screen-dev01");
					container.createIfNotExists();
					BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
					containerPermissions
							.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
					container.uploadPermissions(containerPermissions);
					CloudBlockBlob blob = container
							.getBlockBlobReference(new SimpleDateFormat("yyyy")
									.format(Calendar.getInstance().getTime())
									+ "/"
									+ new SimpleDateFormat("MM")
											.format(Calendar.getInstance()
													.getTime())
									+ "/"
									+ new SimpleDateFormat("dd")
											.format(Calendar.getInstance()
													.getTime())
									+ "/"
									+ "20000112/" + url.substring(69));
					blob.getProperties().setContentType("jpg");
					// blob.uploadProperties();
					File source = new File(url);
					blob.upload(new FileInputStream(source), source.length());
					stmt.setString(2, blob.getStorageUri().getPrimaryUri()
							.toString());
					stmt.setString(11, "AC");
					stmt.setString(12, "0.00");
					stmt.setString(13, "");
					stmt.executeUpdate();
					stmt.close();

					String sql1 = "Update dcpnew.studies SET Status='Pending' where Id='"
							+ id + "'";
					stmt2 = conn.prepareStatement(sql1);
					stmt2.executeUpdate();
					stmt2.close();
					conn.close();

				} catch (Exception e) {
					TxtAreaComments.setText(e.getMessage());
					System.out.println(e.getMessage());
					e.printStackTrace();

				}
			}
			Connection c3 = null;
			PreparedStatement stmt3 = null;

			// ---- Updating upload status to local SqliteDB------//
			try {
				Class.forName("org.sqlite.JDBC");
				c3 = DriverManager.getConnection("jdbc:sqlite:" + workingDir
						+ "\\Resources\\DataUploaderDB.db");
				String sql3 = "UPDATE PatientsData SET UploadStatus='Completed' WHERE PatID='"
						+ patid + "'";

				stmt3 = c3.prepareStatement(sql3);
				stmt3.executeUpdate();
				stmt3.close();
				c3.close();
				TxtAreaComments.setText("Study with Patient ID: " + patid
						+ "Uploaded Successfully");

			} catch (ClassNotFoundException | SQLException e) {
				TxtAreaComments.setText(e.getMessage());
				System.out.println(e.getMessage());

			}

		}
	}

	private void addFiles(File rootFile, DefaultTreeModel model,
			DefaultMutableTreeNode root) {
		// ---- Method to add files into the tree--/

		if (rootFile != null)

			if (rootFile.isDirectory()) {
				for (File file : rootFile.listFiles()) {
					child = new DefaultMutableTreeNode(file.getName());
					model.insertNodeInto((MutableTreeNode) child, root,
							root.getChildCount());
					if (file.isDirectory()) {
						addFiles(file, model, child);
					}
				}
			}

	}

	public static String JsonFromat(String PID, String Fname, String Lname,
			String Age, String DiabeticStatus, String Gender, String Datetime,
			String Comment) {
		String Json = "\"{\"StudyMassPoco\":{\"PatientId\":\""
				+ PID
				+ "\",\"PatientFirstName\":\""
				+ Fname
				+ "\",\"PatientSecondName\":\""
				+ Lname
				+ "\",\"DiabeticStatus\":\""
				+ DiabeticStatus
				+ "\",\"Id\":0,\"AcquisitionSiteId\":0,\"StudyId\":null,\"Priority\":false,\"DeviceId\":0,\"SeriesNumber\":0,\"Age\":\""
				+ Age
				+ "\",\"Email\":\"fhs\",\"Phone\":\"hfhg\",\"Sex\":\""
				+ Gender
				+ "\",\"Status\":null,\"Location\":null,\"CreatedAt\":\""
				+ Datetime
				+ "\",\"CreatedBy\":null,\"UpdatedAt\":null,\"UpdatedBy\":null,\"IsActive\":null,\"IsDeleted\":null},\"ODPoco\":{\"OdVisualActivity\":null,\"Odcyl\":null,\"Odaxis\":null,\"Odsph\":null,\"Odadd\":null,\"Odiop\":null},\"OSPoco\":{\"OsVisualActivity\":null,\"Oscyl\":null,\"Osaxis\":null,\"Ossph\":null,\"Osadd\":null,\"Osiop\":null},\"PatientHistoryInfo\":{\"VisitDate\":\"0001-01-01T00:00:00\",\"DiabaticStatus\":null,\"IsSmoker\":false,\"OpthalmologyHistory\":null,\"ReasonForConsulation\":null,\"OtherRelevantInformation\":null,\"PatientEmail\":null},\"Files\":null,\"IsDraft\":false,\"CaseLogPoco\":{\"Id\":0,\"DcpStudyId\":0,\"SiteId\":20000112,\"UserId\":\"67581afc-c23a-4a39-bd2a-27f4f60e28e6\",\"FirstName\":\"Uploader\",\"LastName\":\"zeiss\",\"Message\":\""
				+ Comment
				+ "\",\"Audience\": \"All\",\"Rolename\": null,\"CaseLogCount\": 0,\"CreatedAt\":null,\"CreatedBy\":\"AC\",\"UpdatedAt\":\""
				+ Datetime
				+ "\",\"UpdatedBy\":\"AC\",\"IsActive\":true,\"IsDeleted\":false},\"CustomFieldJSonText\":null}\"";
		return Json;
	}

	private static boolean netIsAvailable() {
		try {
			final URL url = new URL(
					"http://kjndemo.southeastasia.cloudapp.azure.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}

}