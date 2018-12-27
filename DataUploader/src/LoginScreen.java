import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("serial")
public class LoginScreen extends JFrame implements ActionListener {
	public static LoginScreen LS = null;
	JLabel LblUserName, LblPassword, Lblempty;
	JTextField TxtUsername;
	JPasswordField PswdUser;
	JButton BtnLogin, BtnClear;
	JPanel PnlEast, PnlWest, PnlCenter;
	Font FntLbl = new Font("Times New Roman", Font.BOLD, 16);

	java.net.URL imgURL = getClass().getResource("background_eye.jpg");
	ImageIcon image = new ImageIcon(imgURL);

	MainFrame fm;
	static String workingDir = System.getProperty("user.dir");
	File Dirc = new File(workingDir + "\\Resources\\Archive\\");
	File bakup = new File(workingDir + "\\Resources\\BackupArchive\\");

	public LoginScreen() {
		// TODO Auto-generated constructor stub

		PnlEast = new JPanel(new GridLayout(11, 2));
		PnlWest = new JPanel(new BorderLayout());

		Lblempty = new JLabel("", image, JLabel.CENTER);
		Lblempty.setVerticalAlignment(JLabel.CENTER);
		PnlWest.add(Lblempty);

		
		Lblempty = new JLabel("<html><u>(Kjn-2.0) Offline Study <html><u>",SwingConstants.RIGHT);
		Lblempty.setFont(FntLbl);
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("<html><u>  Uploader<html><u>",SwingConstants.LEADING);
		Lblempty.setFont(FntLbl);
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		LblUserName = new JLabel("Login Id");
		LblUserName.setFont(FntLbl);
		PnlEast.add(LblUserName);
		TxtUsername = new JTextField(15);
		PnlEast.add(TxtUsername);

		LblPassword = new JLabel("Password");
		LblPassword.setFont(FntLbl);
		PnlEast.add(LblPassword);
		PswdUser = new JPasswordField(15);
		PnlEast.add(PswdUser);

		BtnLogin = new JButton("Login");
		BtnLogin.addActionListener(this);
		BtnLogin.setFont(FntLbl);
		PnlEast.add(BtnLogin);
		BtnClear = new JButton("Clear");
		BtnClear.addActionListener(this);
		BtnClear.setFont(FntLbl);
		PnlEast.add(BtnClear);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		Lblempty = new JLabel("");
		PnlEast.add(Lblempty);
		PnlEast.setBorder(BorderFactory.createTitledBorder(" "));
		PnlWest.setBorder(BorderFactory.createTitledBorder(" "));
		add(PnlWest, BorderLayout.EAST);
		add(PnlEast, BorderLayout.WEST);
		setResizable(false);
		setTitle("Data Uploader");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(920, 720);
		setBackground(new Color(7, 44, 73));
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		if (LS == null) {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			LS = new LoginScreen();
			LS.setLocationRelativeTo(null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == BtnClear) {
			TxtUsername.setText("");
			PswdUser.setText("");
		}
		if (e.getSource() == BtnLogin) {
			if (TxtUsername.getText().equals("user")
					&& new String(PswdUser.getPassword()).equals("password")) {
				try {

					if ((!Dirc.exists()) || (!bakup.exists())) {
						try {
							Dirc.mkdirs();
							bakup.mkdirs();
							File db = new File(workingDir
									+ "\\Resources\\DataUploaderDB.db");
							FileUtils.copyInputStreamToFile(
									ResourceLoader.load("\\DataUploaderDB.db"),
									db);

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.exit(0);
					}
					fm = new MainFrame();
					fm.setLocationRelativeTo(null);
					LS.dispose();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Error..! Please check Login id & password");
			}
		}
	}
}
