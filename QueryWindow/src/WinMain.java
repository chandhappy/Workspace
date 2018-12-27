import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class WinMain extends JFrame {
	private static WinMain ins = null;
	public JButton BtnSearch;
	public JRadioButton RdbtnPatientid, RdbtnPatientName;
	public ButtonGroup Btngrprad;
	public JLabel LblPatientid, LblPatientName, LblSearchType;
	public JTextField TxtFeildSearch;
	public JPanel Pnlcenter, PnlResult, PnlImg1, PnlImg2, PnlReport, PnlImage;

	WinMain() {

		Pnlcenter = new JPanel();
		Pnlcenter.setLayout(new FlowLayout());
		Pnlcenter.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Pnlcenter, BorderLayout.NORTH);

		LblSearchType = new JLabel("Search Type :");
		Pnlcenter.add(LblSearchType);

		RdbtnPatientid = new JRadioButton("Patient Id", true);
		RdbtnPatientName = new JRadioButton("Patient Name");
		Btngrprad = new ButtonGroup();
		Btngrprad.add(RdbtnPatientid);
		Btngrprad.add(RdbtnPatientName);

		Pnlcenter.add(RdbtnPatientid);
		Pnlcenter.add(RdbtnPatientName);

		TxtFeildSearch = new JTextField(20);
		Pnlcenter.add(TxtFeildSearch);

		BtnSearch = new JButton("Search");
		Pnlcenter.add(BtnSearch);

		PnlResult = new JPanel();
		PnlResult.setBackground(Color.white);

		TitledBorder border = new TitledBorder("Result(s)");
		border.setTitleJustification(TitledBorder.CENTER);
		PnlResult.setBorder(border);
		PnlResult.setLayout(new GridLayout(1, 2));
		PnlResult.setSize(800, 650);

		PnlImage = new JPanel(new GridLayout(2, 1));
		PnlResult.add(PnlImage);

		PnlImg1 = new JPanel();
		PnlImg1.setBorder(BorderFactory.createTitledBorder("Image1"));
		PnlImg1.setBounds(50, 50, 50, 50);
		PnlImage.add(PnlImg1);

		PnlImg2 = new JPanel();
		PnlImg2.setBorder(BorderFactory.createTitledBorder("Image2"));
		PnlImage.add(PnlImg2);

		PnlReport = new JPanel();
		PnlReport.setBorder(BorderFactory.createTitledBorder("Report PDF"));

		PnlResult.add(PnlReport);
		add(PnlResult);

		setVisible(true);
		setSize(1024, 700);
		setTitle("Query Window");
		setResizable(false);
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		if (ins == null) {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			ins = new WinMain();

		}

	}

}
