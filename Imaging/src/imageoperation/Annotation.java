package imageoperation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Annotation
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				MyFrame frame = new MyFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});
	}
}

class MyFrame extends JFrame
{
	
	 private static final long serialVersionUID = -8395253361039322228L;
	 private JPanel imagePanel;
	 private Image image;
	 private Point2D.Double startPoint = new Point2D.Double();
	 private Point2D.Double endPoint = new Point2D.Double();
	 private Rectangle2D rect2D;
	 private boolean fill = false;

	 public MyFrame()
	 {
		 setTitle("Annotation");
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setResizable(false);

		 try
		 {
			 image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\INCBASHA\\Desktop\\R3724319\\img1.jpg");;

		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }

		 imagePanel = new JPanel()
		 {

			 
			 private static final long serialVersionUID = -6034610442843116599L;

			 public void paintComponent(Graphics g)
			 {
				 super.paintComponent(g);
				 g.drawImage(image, 0, 0, null);
				 if (rect2D != null)
				 {
					 Graphics2D g2 = (Graphics2D)g;
					//g2.setXORMode(Color.RED);
					 g2.setColor(Color.RED);
					 g2.draw(rect2D);
					 if (fill)
					 {
						 g2.setColor(Color.RED);
						 g2.fill(rect2D);
					 }
				 }
			 }
		 };

		 imagePanel.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
		 add(imagePanel);
		 imagePanel.addMouseListener(new MouseHandler());
		 imagePanel.addMouseMotionListener(new MouseSelect());
		 setFocusable(true);
		 addKeyListener(new KeyHandler());
		 setSize(1024,768);
		 setVisible(true);
	 }

	 class MouseHandler extends MouseAdapter
	 {
		 public void mousePressed(MouseEvent e)
		 {
			 startPoint.x = e.getX();
			 startPoint.y = e.getY();
		 }
	 }

	 class KeyHandler extends KeyAdapter
	 {
		 public void keyPressed(KeyEvent e)
		 {
			 if (e.getKeyCode() == (KeyEvent.VK_X))// | KeyEvent.VK_CONTROL))
			 {
				 fill = !fill;
				 imagePanel.repaint();
				 //        System.out.println("CTRL X HAS BEEN CLICKED   start  "
				 //            + startPoint.getX() + "  " + startPoint.getY() + "     end  "
				 //            + endPoint.getX() + "  " + endPoint.getY());
				 //        Graphics2D g = (Graphics2D) getGraphics();
				 //        g.setColor(Color.BLACK);
				 //        g.fillRect((int) startPoint.getX(), (int) startPoint.getY(),
				 //            (int) endPoint.getX(), (int) endPoint.getY());
			 }
		 }
	 }

	 class MouseSelect extends MouseMotionAdapter
	 {
		 @Override
		 public void mouseDragged(MouseEvent e)
		 {
			 super.mouseDragged(e);
			 rect2D = new Rectangle2D.Double(startPoint.x, startPoint.y,
					 endPoint.x - startPoint.x, endPoint.y - startPoint.y);
			 imagePanel.repaint();
			 //      g.draw(r);
			 endPoint.x = e.getX();
			 endPoint.y = e.getY();
			 //      r = new Rectangle2D.Double(startPoint.x, startPoint.y, endPoint.x
			 //          - startPoint.x, endPoint.y - startPoint.y);
			 //      g.draw(r);
		 }

	 }

}
