import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class Reportpanel {

	public static JButton BtnSearch;
	public static JRadioButton RdbtnPatientid, RdbtnPatientName;
	public static ButtonGroup Btngrprad;
	public static JLabel Lblimg1, LblPatientName, LblSearchType;
	public static JTextField TxtFeildSearch;
	public static JPanel Pnlcenter, PnlResult, PnlImg1, PnlImg2, PnlReport,
			PnlImage;
	public static ImageIcon image;

	@SuppressWarnings("resource")
	public static void setup() throws IOException {
		JFrame frame = new JFrame();

		Pnlcenter = new JPanel();
		Pnlcenter.setLayout(new FlowLayout());
		Pnlcenter.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(Pnlcenter, BorderLayout.NORTH);

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

		// frame.setSize(1024, 700);
		frame.setTitle("Query Window");
		frame.setResizable(false);

		// set up the frame and panel
		// JPanel frame = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PagePanel panel = new PagePanel();
		frame.add(panel);
		// frame.pack();

		PnlReport = new JPanel();
		PnlReport.setBorder(BorderFactory.createTitledBorder("Report PDF"));

		// load a pdf from a byte buffer
		File file = new File("C:\\Users\\INCBASHA\\Downloads\\123_retinareport.pdf");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				channel.size());
		PDFFile pdffile = new PDFFile(buf);

		// show the first page
		PDFPage page = pdffile.getPage(0);
		panel.showPage(page);
		PnlReport.add(panel);
		// PnlReport.repaint();
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
		PnlImage.add(PnlImg1);

		PnlImg2 = new JPanel();
		PnlImg2.setBorder(BorderFactory.createTitledBorder("Image2"));
		PnlImage.add(PnlImg2);
		PnlReport.repaint();
		PnlResult.add(PnlReport);
		frame.add(PnlResult);
		frame.setSize(1024, 700);
		frame.setVisible(true);
		frame.repaint();
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Reportpanel.setup();

				} catch (IOException | ClassNotFoundException
						| InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
