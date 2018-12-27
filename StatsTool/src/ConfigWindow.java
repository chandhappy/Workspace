import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ConfigWindow implements ActionListener {

	static JFrame frame;
	static JPanel panel;
	static JLabel lblpswd;
	static JLabel lbl;
	 JTextField TxtPswd;
	 static String[] choices = { "Afghanistan", "Albania", "Algeria", "Andorra",
				"Angola", "Antigua and Barbuda", "Argentina", "Armenia",
				"Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas, The",
				"Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
				"Belize", "Benin", "Bhutan", "Bolivia",
				"Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei",
				"Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia",
				"Cameroon", "Canada", "Cabo Verde", "Central African Republic",
				"Chad", "Chile", "China", "Colombia", "Comoros",
				"Congo, Democratic Republic of the", "Congo, Republic of the",
				"Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Curacao",
				"Cyprus", "Czechia", "Denmark", "Djibouti", "Dominica",
				"Dominican Republic", "East Timor (see Timor-Leste)", "Ecuador",
				"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
				"Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon",
				"Gambia, The", "Georgia", "Germany", "Ghana", "Greece",
				"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
				"Haiti", "Holy See", "Honduras", "Hong Kong", "Hungary",
				"Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
				"Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan",
				"Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo",
				"Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
				"Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
				"Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",
				"Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
				"Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco",
				"Mongolia", "Montenegro", "Morocco", "Mozambique", "Namibia",
				"Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua",
				"Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan",
				"Palau", "Palestinian Territories", "Panama",
				"Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland",
				"Portugal", "Qatar", "Romania", "Russia", "Rwanda",
				"Saint Kitts and Nevis", "Saint Lucia",
				"Saint Vincent and the Grenadines", "Samoa", "San Marino",
				"Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia",
				"Seychelles", "Sierra Leone", "Singapore", "Sint Maarten",
				"Slovakia", "Slovenia", "Solomon Islands", "Somalia",
				"South Africa", "South Korea", "South Sudan", "Spain",
				"Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
				"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania",
				"Thailand", "Timor-Leste", "Togo", "Tonga",
				"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
				"Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
				"United Kingdom", "Uruguay", "Uzbekistan", "Vanuatu",
				"Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" };


	 final static JComboBox<String> cb= new JComboBox<String>(choices);
	public ConfigWindow() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("Config");
		panel = new JPanel();
	    frame.add(panel);
	    
	    lblpswd = new JLabel("Admin Password");
	    panel.add(lblpswd);
	    JTextField TxtPswd= new JTextField(10);
	    panel.add(TxtPswd);
	    JButton btn = new JButton("EDIT");
	    btn.addActionListener(this);
	    panel.add(btn);
	    lbl = new JLabel("Select Country");
	    panel.add(lbl);
	    cb.setEditable(false);
	    panel.add(cb);

	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(350, 300);
	    frame.setLocation(430, 100);
		
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub 
		UIManager
		.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
       new ConfigWindow();
	    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(TxtPswd.getText().equals("M@TR!X")) {
			cb.setEditable(true);
		}
	}

}
