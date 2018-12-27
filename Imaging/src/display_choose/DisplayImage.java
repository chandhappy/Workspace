package display_choose;

import java.io.IOException;

import javax.swing.JFrame;

public class DisplayImage extends JFrame
{

	/**
	 * @author 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayImage()
	{
		
	}
	
	public void showImage() throws IOException
	{
		setTitle("Image display");
		add( new DisplayPanel());
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) throws IOException
	{
		
		DisplayImage objDisplayImage = new DisplayImage();
		objDisplayImage.showImage();
	}
	

}
