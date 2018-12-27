package com.image.display;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JPanel;

public class flipclass extends JPanel {
	

		private static final long serialVersionUID = 1L;
		Image ojImage=null;
		flipclass() throws IOException
		{
			ojImage=ImagePanel2.objImage;
		}
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(ojImage!=null){
			int xcord = (getWidth()- ojImage.getWidth(null))/2;
			int ycord =(getHeight()- ojImage.getHeight(null))/2;
			g.drawImage(ojImage, 0,0, xcord,ycord,xcord,0 ,0,ycord, null);
			repaint();
		}
	}
	}
			
		
