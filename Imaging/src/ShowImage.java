
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ShowImage extends JPanel{
  BufferedImage image; // Declare a name for our Image object.

// Create a constructor method
  public ShowImage(){
   super();
   // Load an image file into our Image object. 
   // This file has to be in the same
   // directory as ShowImage.class.
   try {
	image =ImageIO.read(new File("C:\\Users\\INCBASHA\\Desktop\\R3724319\\img1.jpg"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  }



  public void paintComponent(Graphics g){

	 new ShowImage();
	 int xcord=(getWidth()-image.getWidth(null))/2;
	 int ycord=(getHeight()-image.getHeight(null))/2;
   
	 g.drawImage(image, xcord, ycord, this);
	 //g.drawImage(image,0,0,image.getWidth(),image.getHeight(),30,30,image.getWidth(),image.getHeight(),null);
     // 200 wide and high
  }

  public static void main(String arg[]){
new ShowImage();
  }
}
