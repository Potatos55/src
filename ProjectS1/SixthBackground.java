	
	package ProjectS1;


	import java.awt.*;
import java.awt.event.*;
import java.util.Random;


import javax.swing.*;

	public class SixthBackground extends JFrame implements KeyListener
		{
			static final long serialVersionUID = 0;
			Image im;
			Image Back; //background Image
			Image Boy; //Character Image
			Image Envelope;
			Image Rock;
			Image TB = null;
			Image textBubble = null;
			Image display1;
			int xCoorB = 0; // X coordinate for background
			int yCoorB = 0;
			Random rand = new Random();
			int xEnv = rand.nextInt(1400) + 400; // x coordinate for envelope
			int yEnv = rand.nextInt(3) * 125  + 460;  //y coordinate for envelope
			int xObs = rand.nextInt(1400) + 380; // x coordinate for obstacle
			int yObs = rand.nextInt(3) * 125  + 480;  //y coordinate for obstacle
			int xBoy = 0;  // x coordinate of character 
			int yBoy = 540;  // Y coordinate of character 
			int deltaX = 55; //extra to make background move 
			int envelopesN = 0; //NUmber of envelopes
			int letup = 0;
			boolean envelopes = true;
			int xCoordinate; 
			Color oranged = Color.decode("#2F0049"); /* piece of code that alllows hex colors.*/
			Color mycolor = Color.decode("#2F0049");
			boolean spacebar = true; //for spacebar text   
			boolean enter = true;
			int changescreen = 0;
											
		SixthBackground()
		{
			super("Image example");
			Back = readImage("Back.png");
			Boy = readImage("Character.png");
			Envelope =  readImage("Envelope.png");
			display1 = readImage("displayone.png");
			Rock = readImage("rock.png");
			TB = readImage("textbubble.png");
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
				if (xCoorB != 0){
					xCoorB = xCoorB - deltaX;
					xEnv = xEnv - deltaX;
					xObs = xObs - deltaX;
					
				}
				
				//background Redrawn
				
				if(xCoorB < -1400){
					
					xCoorB = 2; 
				}
			
				//Intersecting with varible and adding it to the score
				Rectangle kract= new Rectangle(xEnv, yEnv, 125, 50);
				Rectangle rect= new Rectangle(xBoy, yBoy, 50, 100);
				Rectangle obs= new Rectangle(xObs, yObs, 100, 100);
				 if(kract.intersects(rect)== true)
				 {
					 envelopesN = envelopesN +1; 
					 xEnv = rand.nextInt(1800) + 1700; 
					 yEnv = rand.nextInt(3) * 110  + 460;
					 
				 }
				 // what makes the envelope reappear when the boy doesn't touch the envelope

				 else if (kract.intersects(rect)== false && xEnv < -50)
				 {
					 xEnv = rand.nextInt(1800) + 1700; 
					 yEnv = rand.nextInt(3) * 110  + 460;
					 
				 }
				 
				 if(rect.intersects(obs)== true)
				 {
					 xBoy = xBoy - 300;
	
				 }
				
				//Character move
				
				if(xBoy <100){
					xBoy = 470;
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
			 //boy's shoadow
			g.setColor(Color.orange);
			g.fillOval(xBoy + 15, yBoy + 90, 60, 10);
			g.setColor(Color.red);
			g.fillOval(xBoy + 15, yBoy + 89, 60, 10);
			g.setColor(Color.black);
			g.fillOval(xBoy + 19, yBoy + 98, 10, 10);
			g.fillOval(xBoy + 55, yBoy + 98, 10, 10);
	
			//Score envelope
			g.drawImage(Envelope, 1495, 50, this);
			g.setColor(oranged);
			
			
			//Level Passed
			
			if(envelopesN == 5){
				g.setFont(new Font("Comic Sans MS",Font.BOLD,55));
				g.drawString("Congratulations! Jay can now pay his bills!!", 400 , 405);
				g.setColor(mycolor); 
				
				xCoorB = 0;
			}
			
			
				if(spacebar){  //for making texT "PRESS SPACEBAR TO START" DISAPEAR
					g.setFont(new Font("Comic Sans MS",Font.BOLD,55));
					g.drawString("Press Spacebar to Start", 650 , 405);
					g.setColor(mycolor); 
				}

			g.setFont(new Font("Comic Sans MS",Font.CENTER_BASELINE ,45));
			g.drawString("X" + (int) envelopesN, 1635 , 95);
			//Scrolling Envelopes
			
			g.drawImage(Envelope, xEnv, yEnv, this);
			
			//envelopes shadow
			g.setColor(Color.gray);
			g.fillOval(xEnv, yEnv + 70, 140, 20);
			
			//code for drawing obstacles
			

			g.drawImage(Rock, xObs, yObs, this);
			g.drawImage(Rock, xObs, yObs, this);
			g.drawImage(Rock, xObs, yObs, this);
			
			if(textBubble != null){
			g.drawImage(textBubble, xBoy, yBoy, this);
			}
			g.drawImage(display1, 0, 0, this);
			g.setColor(oranged);

			
			
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
		
				if(yBoy > 780){
					yBoy = 780;   
				}
			    }
			else if (KeyEvent.VK_UP == inKey)
			    {
				yBoy = yBoy - 110;
				
				
				if(yBoy < 438){
					yBoy = 438;
				  }
				
			    }
			else if (KeyEvent.VK_SPACE == inKey)
			    {
				xCoorB = xCoorB -  1; /* what makes back movable when space bar is pressed*/
				
				spacebar = false; //makes my texT "PRESS SPACEBAR TO START" DISAPEAR0
				
			    }
			else if (KeyEvent.VK_ENTER == inKey)
				{
			
				
				display1 = null; 
				enter = false;
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
			SixthBackground app = new SixthBackground();
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
		





