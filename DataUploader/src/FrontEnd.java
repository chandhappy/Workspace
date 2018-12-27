import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class FrontEnd extends JFrame implements ActionListener {

	public static FrontEnd fe = null;
	JLabel LblDate, LblFolder, LblPatientId, LblFirstName, LblLastName, LblAge,
			LblGender;
	JTextField TxtFolder, TxtPatientId, TxtFirstName, TxtLastName, TxtAge, TxtGender;
	JButton BtnClear, BtnSave, BtnUpload, BtnShowPatients;
	JPanel PnlPatientCreation, PnlDataUpload, PnlLeft, PnlRight, PnlBottom,
			PnlTop, PnlCenter, PnlRgtAdj, PnlFrameAdj, PnlShowPatients,
			PnlUploadBtn;
	JTextArea TxtAreaComments;
	Font FntLbl = new Font("Times New Roman", Font.BOLD, 14);
	static JTable table;
	JScrollPane ScrollPaneStatus, TableContainer;

	String[] columnNames = { "FirstName", "LastName", "Images Location","Count"};

	JTabbedPane Tabpane;
	final JComboBox<String> cb;

	public FrontEnd() {

		// TODO auto-generated constructor stub
		PnlTop = new JPanel();

		LblDate = new JLabel("Date : ");
		LblDate.setFont(FntLbl);
		PnlTop.add(LblDate);

		UtilDateModel datemodel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(datemodel, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		PnlTop.add(datePicker);

		PnlFrameAdj = new JPanel();
		PnlFrameAdj.setBorder(BorderFactory.createTitledBorder(""));

		Tabpane = new JTabbedPane();
		PnlPatientCreation = new JPanel(new GridLayout(2, 1));
		PnlDataUpload = new JPanel(new GridLayout(1,2));
		PnlShowPatients = new JPanel();

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane TableContainer = new JScrollPane(table);
	

		PnlDataUpload.add(PnlShowPatients);
		PnlUploadBtn = new JPanel(new GridLayout(1, 2));

		BtnShowPatients = new JButton("Show All Patients");
		BtnShowPatients.setFont(FntLbl);
		PnlUploadBtn.add(BtnShowPatients);

		BtnUpload = new JButton("UploadData");
		BtnUpload.setFont(FntLbl);
		PnlUploadBtn.add(BtnUpload);

		PnlDataUpload.add(PnlUploadBtn);
		PnlShowPatients.add(TableContainer,BorderLayout.EAST);

		PnlLeft = new JPanel();
		PnlLeft.setBorder(BorderFactory.createTitledBorder("Demographics"));
		PnlLeft.setLayout(new GridLayout(7, 2));

		LblFirstName = new JLabel("Patient First Name : ");
		LblFirstName.setFont(FntLbl);
		PnlLeft.add(LblFirstName);
		TxtFirstName = new JTextField(24);
		PnlLeft.add(TxtFirstName);

		LblLastName = new JLabel("Patient Last Name : ");
		LblLastName.setFont(FntLbl);
		PnlLeft.add(LblLastName);
		TxtLastName = new JTextField(24);
		PnlLeft.add(TxtLastName);

		LblAge = new JLabel("Patient Age : ");
		LblAge.setFont(FntLbl);
		PnlLeft.add(LblAge);
		TxtAge = new JTextField(3);
		PnlLeft.add(TxtAge);

		LblGender = new JLabel("Patient Gender : ");
		LblGender.setFont(FntLbl);
		PnlLeft.add(LblGender);
		String[] gender = { "", "Male", "Female", "Other" };
		cb = new JComboBox<String>(gender);
		PnlLeft.add(cb);

		LblPatientId = new JLabel("Patient ID : ");
		LblPatientId.setFont(FntLbl);
		PnlLeft.add(LblPatientId);
		TxtPatientId = new JTextField(16);
		PnlLeft.add(TxtPatientId);

		LblFolder = new JLabel("Folder ID :");
		LblFolder.setFont(FntLbl);
		PnlLeft.add(LblFolder);
		TxtFolder = new JTextField(5);
		PnlLeft.add(TxtFolder);

		PnlBottom = new JPanel();

		BtnClear = new JButton("Clear");
		BtnClear.setFont(FntLbl);
		BtnClear.addActionListener(this);
		PnlBottom.add(BtnClear);

		BtnSave = new JButton("Save");
		BtnSave.setFont(FntLbl);
		BtnSave.addActionListener(this);
		PnlBottom.add(BtnSave);

		PnlCenter = new JPanel();

		PnlCenter.setBorder(BorderFactory.createTitledBorder(" "));
		TxtAreaComments = new JTextArea(1, 80);
		ScrollPaneStatus = new JScrollPane(TxtAreaComments);
		PnlCenter.add(ScrollPaneStatus);

		PnlPatientCreation.add(PnlLeft, BorderLayout.WEST);
		PnlPatientCreation.add(PnlBottom, BorderLayout.SOUTH);

		Tabpane.add("PatientCreation", PnlPatientCreation);
		Tabpane.add("Upload Images", PnlDataUpload);
		PnlFrameAdj.add(Tabpane);

		add(PnlFrameAdj,BorderLayout.CENTER);
		add(PnlCenter,BorderLayout.SOUTH);
		add(PnlTop, BorderLayout.NORTH);

		setResizable(false);
		setTitle("Data Uploader");
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(920, 720);

	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub

		if (fe == null) {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			fe = new FrontEnd();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == BtnClear) {
			TxtFolder.setText("");
			TxtPatientId.setText("");
			TxtLastName.setText("");
			TxtFirstName.setText("");
			TxtAge.setText("");
			cb.setSelectedItem(null);
			TxtAreaComments.setText("");
		}
		if (e.getSource() == BtnSave) {

			if ((!(TxtFolder.getText().isEmpty()))
					&& (!(TxtPatientId.getText().isEmpty()))
					&& (!(TxtLastName.getText().isEmpty()))
					&& (!(cb.getSelectedItem().toString().isEmpty()))
					&& (!(TxtFolder.getText().isEmpty()))) {
				Connection c = null;
				PreparedStatement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager
							.getConnection("jdbc:sqlite:C:\\Users\\INCBASHA\\Desktop\\DataUploaderDB.db");
					c.setAutoCommit(false);
					System.out.println("Opened database successfully");
					String sql = "INSERT INTO PatientsData (PatFName,PatLName,PatAge,PatGender,PatID,FolderID)"
							+ "VALUES (?,?,?,?,?,?)";
					stmt = c.prepareStatement(sql);
					stmt.setString(1, TxtFirstName.getText());
					stmt.setString(2, TxtLastName.getText());
					stmt.setInt(3, Integer.parseInt(TxtAge.getText()));
					stmt.setString(4, cb.getSelectedItem().toString());
					stmt.setString(5, TxtPatientId.getText());
					stmt.setString(6, TxtFolder.getText());
					stmt.executeUpdate();
					stmt.close();
					c.commit();
					c.close();
				} catch (Exception e1) {
					System.err.println(e1.getClass().getName() + ": "
							+ e1.getMessage());
					e1.printStackTrace();
					System.exit(0);
				}
				System.out.println("Records created successfully");
				TxtAreaComments.append("\nSaved Successfully...!");
			}
		}
	}
}
