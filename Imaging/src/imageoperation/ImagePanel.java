package imageoperation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;


public class ImagePanel extends JPanel
{
	
	private static final long serialVersionUID = 8805943100278470785L;
	private Image objImage;
	private double currentAngle;
	boolean bScale =false;
	boolean bRotate =false;
	boolean bFlip = false;
	boolean bIsRotated = false;
	private AffineTransform objResultTransform = null;

	public ImagePanel() 
	{
		objImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\INCBASHA\\Desktop\\R3724319\\img1.jpg");
		objResultTransform.setToIdentity();
		setBackground(Color.WHITE);

	}

	public void rotate() 
	{
		//rotate 5 degrees at a time
		currentAngle+=90;
		if (currentAngle >=360.0) 
		{
			currentAngle = 0;
		}
		bRotate = true;
		setPreferredSize(new Dimension(objImage.getHeight(this), objImage.getWidth(this)));
		revalidate();
		repaint();
	}
	
	public void scale()
	{
		bScale = true;
		repaint();
	}

	public void flip()
	{
		bFlip = true;
		repaint();
	}
	
	public void reset()
	{
		bRotate = false;
		bScale = false;
		bFlip = false;
		bIsRotated = false;
		currentAngle = 0;
		objResultTransform.setToIdentity();
		revalidate();
		repaint();

	}


	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform origXform = g2d.getTransform();

		AffineTransform objAffineTransform = new AffineTransform();
		objAffineTransform.setToIdentity();

		//center of rotation is center of the panel
		int xRot = getWidth()/2;
		int yRot =getHeight()/2;
		if(true == bRotate)
		{
			objAffineTransform.rotate(Math.toRadians(currentAngle),xRot,yRot);
			objResultTransform.preConcatenate(objAffineTransform);
			bRotate = false;
			bIsRotated = true;
		}
		if(bScale == true)
		{
			int pw = this.getWidth();
			int ph = this.getHeight();
			int iw = objImage.getWidth(this);
			int ih = objImage.getHeight(this);

			double dx= (float)pw/iw;
			double  dy= (float)ph/ih;
			int x = (getWidth() - objImage.getWidth(this))/2;
			int y = (getHeight() - objImage.getHeight(this))/2;
			if(bIsRotated)
			{
				dx= (float)pw/ih;
				dy= (float)ph/iw;
				x = (getWidth() - objImage.getHeight(this))/2;
				y = (getHeight() - objImage.getWidth(this))/2;
			}

			objAffineTransform.scale(dx,dy);
			objAffineTransform.translate(-x, -y);
			objResultTransform.preConcatenate(objAffineTransform);

			bScale = false;
		}
		if(bFlip == true)
		{
			objAffineTransform.scale(-1,1);
			objAffineTransform.translate(-getWidth(), 0);
			objResultTransform.preConcatenate(objAffineTransform);
			bFlip = false;
		}
		int x = (getWidth() - objImage.getWidth(this))/2;
		int y = (getHeight() - objImage.getHeight(this))/2;

		g2d.setTransform(objResultTransform);
		g2d.drawImage(objImage, x,y, this);
		g2d.setTransform(origXform);
		


	}


	   public Dimension getPreferredSize() 
	   {
		   if(bIsRotated)
		   {
			   return new Dimension (objImage.getHeight(this), objImage.getWidth(this));
		   }else
		   {
			   return new Dimension (objImage.getWidth(this), objImage.getHeight(this));
		   }
	   }

}
