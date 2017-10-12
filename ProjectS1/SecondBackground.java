package ProjectS1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SecondBackground extends JFrame implements KeyListener
{
	static final long serialVersionUID = 0;
	Image im;
	Image Back;
	Image Boy;
	int Bck = 0;
	int xCoor = 0;
	int yCoor = 0;
	int xBoy = 0;
	int yBoy = 550;
	
	
	SecondBackground()
	{
		super("Image example");
		Back = readImage("Back.png");
		Boy = readImage("Character.png");
		JPanel p = new JPanel ();
		this.setSize(1800, 1000);
		this.setVisible(true);
		p.requestFocus();
		addKeyListener(this);
		newGame();
	}
	
	public static Image readImage(String imageName) {
		Image image = Toolkit.getDefaultToolkit().getImage(imageName);
		MediaTracker imageTracker = new MediaTracker(new JPanel());
		imageTracker.addImage(image, 0);
		try {
			imageTracker.waitForID(0);
		} catch (InterruptedException e) {
			return null;
		}
		return image;
	}

	public void newGame()
	{
		xCoor = 300;
	
	}
	
	public void makeProgramWait(int milliseconds)
	{
		try
		    {
			Thread.sleep(milliseconds);
		    }
		catch (Exception e)
		{
			System.out.println("An error in sleep process.");
		}
	}
	
	public void playGame()
	{
		while (true)
		{
			
			if(yBoy > 880){
				yBoy = 880;
			}
			
			if(yBoy < 550){
				yBoy = 550;
			}
			if(xBoy <100){
				xBoy = 870;
			}
			
			makeProgramWait(100);
			repaint();
		}
	}
	
	public void paint(Graphics g)
	    {
	    if (im == null)
		   {
		   im = createImage(this.getWidth(), this.getHeight());
		   }
	   Graphics tempG = im.getGraphics();
	   paintScreen(tempG);
	   g.drawImage(im, 0, 0, this);
	    }
	
	public void paintScreen(Graphics g)
	    {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(Back, Bck, 0, this);
		g.drawImage(Boy, xBoy, yBoy, this);
		
		
		
	    }
	
	public void keyPressed(KeyEvent e)
	    {
		
		int inKey;
		inKey = e.getKeyCode();
		if (KeyEvent.VK_Q == inKey)
		    {
			System.exit(0);
		    }
		else if (KeyEvent.VK_S == inKey)
		    {
			newGame();
		    }
		else if (KeyEvent.VK_DOWN == inKey)
		    {
			yBoy = yBoy + 110;
	
			
		    }
		else if (KeyEvent.VK_UP == inKey)
		    {
			yBoy = yBoy - 110;
			
		    }
		repaint();
	    }
	
	public void keyReleased(KeyEvent e)
	    {
	    }
	
	public void keyTyped(KeyEvent e)
	    {
	    }
	
	public static void main(String args[])
	    {
		SecondBackground app = new SecondBackground();
	    app.addWindowListener(new WindowAdapter()
	        {
	    	public void windowClosing(WindowEvent e)
	    	    {
	    		System.exit(0);
	    	    }
	        });
	        app.playGame();
	    }
}
	