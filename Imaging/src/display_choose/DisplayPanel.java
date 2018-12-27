package display_choose;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel
{

	/**
	 * @author
	 */
	private static final long serialVersionUID = 1L;
	Image objImage = null;
	
	public DisplayPanel() throws IOException
	{
		//provide file path which is to be displayed
		JFileChooser objChooser = new JFileChooser();
		if ( JFileChooser.APPROVE_OPTION == objChooser.showOpenDialog(this))
		{
			//String strFile = objChooser.getSelectedFile().getPath();
			objImage = ImageIO.read(objChooser.getSelectedFile());
			//objImage = Toolkit.getDefaultToolkit().getImage(strFile);
		}
		
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int xcord = (getWidth()- objImage.getWidth(null))/2;
		int ycord =(getHeight()- objImage.getHeight(null))/2;
		
		g.drawImage(objImage, xcord,ycord,this.getWidth(),this.getHeight(),null);
	}
}
