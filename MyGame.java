

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MyGame extends SnakeGame
{
	private boolean gameover;
	//Things inherited from SnakeGame:
	// protected Snake player;
	// protected BodySegment food;
	// protected double waitSeconds;
	private int timeDiff;
	
	public MyGame()
	{
		super();
		setBG(Color.BLACK); //background color is Black
		setGridColor( new Color(0,255,255,100)); //the grid color is initially turquoise
		
		
		playGame();
	}
	
	public void gameFrame()
	{
			if(gameover)
				return;
			
			if(difficultyNum == 0)
				waitSeconds = 0.4;
			else if(difficultyNum == 1)
				waitSeconds = 0.2;
			else if(difficultyNum == 2)
				waitSeconds = 0.1;
			else
				waitSeconds = 0.05;
			
			player.addFirst(); //adds a head
			player.removeLast(); //cuts of tail
			drawGame();//do this at some point	
			
			//timer
			double currTime = System.currentTimeMillis() / 1000.0;
			timeDiff = (int)(currTime - startTime);
			int millisec = (int)( ( (currTime - startTime) - timeDiff ) * 100 );
			int min = 0;
			int sec = 0;
			
			//calculations to make format minutes:seconds.milliseconds
			if(timeDiff > 0)
			{
				min = timeDiff / 60;
				sec = timeDiff % 60;
			}
			
			if(sec < 10)
			{
				timer.setText("Time: "+min+":0"+sec+"."+millisec);
			}
			else
			{
				timer.setText("Time: "+min+":"+sec+"."+millisec);
			}
			
			score.setText("Score: "+(player.size()-3) ); //size-3 to account for the initial size
			
			//if the snake eats the food then spawn the next food in a random spot
			if(player.getFirst().isTouching(food))
			{
				player.addFirst();
				placeFood();
				setRandomGridColor();
			}
			
			//if it eats itself
			BodySegment curr = player.getFirst().getNext();
			while(curr!=player.getFirst())
			{
				if(player.getFirst().isTouching(curr))
				{
					gameover = true;
					JOptionPane.showMessageDialog(this, "Game Over");
					JOptionPane.showMessageDialog(this, "Time: " + timeDiff + "s Score: "+ (player.size()-3) ); //display the score
					String name = JOptionPane.showInputDialog("Please type in your name..."); //stores the string typed by the user
					if(name == null) //if didn't type anything then the name is just acc name
						name = System.getProperty("user.name"); 
					new ScoreFrame(new ScoreRecord(name,timeDiff,player.size()-3)); //add the score
					gameover = true;
				}
				curr = curr.getNext();
			}
			
			
			//if it hits boundary
			if(player.getFirst().getX() == WIDTH || player.getFirst().getX() < 0|| player.getFirst().getY() == HEIGHT || player.getFirst().getY() < 0)
			{
				JOptionPane.showMessageDialog(this, "Game Over");
				JOptionPane.showMessageDialog(this, "Time: " + timeDiff + "s Score: "+ (player.size()-3) ); //display the score
				String name = JOptionPane.showInputDialog("Please type in your name..."); //stores the string typed by the user
				if(name == null) //if didn't type anything then the name is just acc name
					name = System.getProperty("user.name");
				new ScoreFrame(new ScoreRecord(name,timeDiff,player.size()-3)); //add the score
				gameover = true;
			}
			
			food.setRandomHue();
	}
	
	public void setRandomGridColor()
	{
		//sets random rgb to obtain random color
		int x = (int) (Math.random()*255);
		int y = (int) (Math.random()*255);
		int z = (int) (Math.random()*255);
		
		setGridColor(new Color(x,y,z));
		
	}
	
	public static void main(String[] args){new MyGame();}	
}
