package imageoperation;

import java.io.IOException;

import javax.swing.JFrame; 

import display_choose.DisplayPanel;

public class DisplayImage extends JFrame
{

	
	private static final long serialVersionUID = 1L;
	
	public DisplayImage()
	{
		
	}
	
	public void showImage() throws IOException
	{
		setTitle("Image display");
		add( new DisplayPanel());
		setSize(500, 600);
		setVisible(true);
		
	}
	public static void main(String[] args) throws IOException
	{
		
		DisplayImage objDisplayImage = new DisplayImage();
		objDisplayImage.showImage();
	
	}
	

}
