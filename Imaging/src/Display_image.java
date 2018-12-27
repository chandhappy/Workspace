import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Display_image extends JFrame {

	public Display_image() {
		

		   JFrame frame = new JFrame("ShowImage");
		 
		   
		   
		   
		   frame.setSize(600,600);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   ShowImage panel = new ShowImage();
		   frame.setContentPane(panel); 
		   frame.setVisible(true); 
		  }
		
	
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		Display_image di = new Display_image();
		
	}

	
}
