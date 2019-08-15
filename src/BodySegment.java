

import java.awt.Color;
import java.awt.Graphics;

public class BodySegment {
	//this BodySegment will act like a NODE
	//  he will point to the next BodySegment in the Snake
	//  You will need a variable for this
	
	public static final int SIZE=15;
	public Color hue = Color.green;
	public static int nextID = 0;
	//public int myID;
	private int xPos, yPos;
	private BodySegment prev;
	private BodySegment next;
	
	public BodySegment(int x, int y){
		xPos = x;
		yPos = y;
		
		//myID = nextID++;
	}
	
	//~~~~~~~~~~~~~~~~accessors and mutators~~~~~~~~~~~~~~~~~~~~~~
	//PostCondition: returns the previous body segment
	public BodySegment getPrev()
	{
		return prev;
	}
	
	//Postcondition: sets the next body segment to that of the parameter
	public void setPrev(BodySegment x)
	{
		prev = x;
	}
	
	//Postcondition: returns the next body segment
	public BodySegment getNext()
	{
		return next;
	}
	
	//Postcondition: sets the next body segment to that of the parameter
	public void setNext(BodySegment x)
	{
		next = x;
	}
	
	//PostCondition: returns the body segment's x coordinate
	public int getX()
	{
		return xPos;
	}
	
	//PostCondition: returns the body segment's y coordinate
	public int getY()
	{
		return yPos;
	}
	
	//Returns true if the head of the snake is on sp
	//Returns false if else
	public boolean isTouching( BodySegment sp)
	{
		if( xPos == sp.getX() && yPos == sp.getY() )
			return true;
		else
			return false;
	}
	
	//sets a random color (used on food)
	public void setRandomHue()
	{
		int x = (int) (Math.random()*255);
		int y = (int) (Math.random()*255);
		int z = (int) (Math.random()*255);
		
		hue = new Color(x,y,z);
	}
	
	//each BodySegment knows how to draw himself :)
	public void draw(Graphics g){
		g.setColor(hue);
		g.fillOval(xPos, yPos, SIZE, SIZE);
		//just for testing purposes, you can take this out later
		g.setColor(Color.BLACK);
		g.drawString("", xPos+SIZE/4, yPos+SIZE);
	}
}
