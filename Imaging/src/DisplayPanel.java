

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{

	
	private static final long serialVersionUID = 1L;
	Image objImage = null;
	
	public DisplayPanel()
	{
		//provide file path which is to be displayed
		objImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\INCBASHA\\Desktop\\R3724319\\img1.jpg");
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(objImage, 0,0,this.getWidth(),this.getHeight(),null);
	}
}
