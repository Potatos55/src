package ProjectS1;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class ThirdBackground extends JFrame implements KeyListener
{
	static final long serialVersionUID = 0;
	Image im;
	Image Back; //background Image
	Image Boy; //Character Image
	Image Envelope;
	int xCoorB = 0; // X coordinate for background
	int yCoorB = 0;
	Random rand = new Random();
	int xEnv = rand.nextInt(1400) + 400;
	int yEnv = rand.nextInt(3) * 110  + 460;
	int xBoy = 0;  // x coordinate of character 
	int yBoy = 550;  // Y coordinate of character 
	int deltaX = 55; //extra to make background move 
	int envelopesN = 5; //NUmber of envelopes
	int letup = 0;
	int xCoordinate; 
	Color oranged = Color.decode("#FF0000"); /* piece of code that alllows hex colors.*/
	Color mycolor = Color.decode("#FF0000");
	
	ThirdBackground()
	{
		super("Image example");
		Back = readImage("Back.png");
		Boy = readImage("Character.png");
		Envelope =  readImage("Envelope.png");
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
		xCoordinate = 300;
	
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
			/* first if statement allows the background to move, the others that follow are for the character to move*/
			if (xCoorB < 0){
				xCoorB = xCoorB - deltaX;
			}
			
			//background Redrawn
		
			
			//Character move
			if(yBoy > 780){
				yBoy = 780;
			}
			
			if(yBoy < 450){
				yBoy = 450;
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
		//Background 
		g.drawImage(Back, xCoorB, yCoorB, this);
		//Boy
		g.drawImage(Boy, xBoy, yBoy, this);
		//Score envelope
		g.drawImage(Envelope, 1495, 50, this);
		g.setColor(oranged);
		g.setFont(new Font("Comic Sans MS",Font.BOLD,55));
		g.drawString("Press Spacebar to Start", 650 , 455);
		g.setColor(mycolor);
		g.setFont(new Font("Comic Sans MS",Font.CENTER_BASELINE ,45));
		g.drawString("X" + (int) envelopesN, 1635 , 95);
		//Envelopes
		g.drawImage(Envelope, xEnv, yEnv, this);

		
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
		else if (KeyEvent.VK_SPACE == inKey)
		    {
			xCoorB = xCoorB -  1; /* what makes back movable when space bar is pressed*/
			
		    }
		repaint();
	    }
	
	public void keyReleased(KeyEvent e)
	    
	    {
		letup = 1;
	    }
	 
	
	public void keyTyped(KeyEvent e)
	    {
	    }
	
	public static void main(String args[])
	    {
		ThirdBackground app = new ThirdBackground();
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
	
