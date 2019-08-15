

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Snake implements KeyListener{
	public static final int UP=0, RIGHT=1, DOWN=2, LEFT=3;
	//variables you will need
	// 1) pointer to the first and/or last piece of the snake
	public BodySegment first;
	public BodySegment last;
	private int num = 0;
	// 2) a variable to keep track of what direction this snake is facing
	public int direction;
	
	public Snake()
	{
		//go ahead and give him 1 BodySegment
		this.addFirst();
		//tell him what direction he is moving	
		direction = RIGHT; 
		
	}
	
	public int size()
	{	
		//if not empty
		if(first!=null)
			return num;
		//if empty
		else
			return 0;
	}
	
	//returns the snake's head
	public BodySegment getFirst()
	{
		return first;
	}
	
	//adds a new head
	public void addFirst()
	{
		BodySegment x = null;
		if(first!=null) //if the snake already exists
		{
			//places the head where it is heading
			if(direction==UP)
				x = new BodySegment(first.getX(),first.getY()-15);
			else if(direction==RIGHT)
				x = new BodySegment(first.getX()+15,first.getY());
			else if(direction==DOWN)
				x = new BodySegment(first.getX(),first.getY()+15);	
			else
				x = new BodySegment(first.getX()-15,first.getY());
			
			x.setNext(first);
			first.setPrev(x);
			last.setNext(x);
			x.setPrev(last);
			
			first = x;
			num++;
		}
		else //if not exist yet
		{
			//just places a body at (0,0)
			first = new BodySegment(0,0);
			
			last = first;
			first.setNext(last);
			last.setNext(first);
			last.setPrev(first);
			first.setPrev(last);
			num++;
		}
		
	}
	
	//cuts the snake's tail
	public void removeLast()
	{
		BodySegment x = last.getPrev();
		last.getPrev().setNext(last.getNext());
		first.setPrev(x);
		
		last = x;
		num--;
	}
	
	
	public void draw(Graphics g)
	{
		//tell EACH segment to draw (and pass it g)
		BodySegment curr = first;
		curr.draw(g);
		curr=curr.getNext();
		while(curr!=first)
		{
			curr.draw(g);
			curr=curr.getNext();
		}

	}
	
	
	@Override
	//make the snake respond to key presses
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(direction != DOWN)
			{
				System.out.println("User pressed up");
				direction = UP;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(direction != LEFT)
				direction = RIGHT;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(direction != UP)
				direction = DOWN;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(direction != RIGHT)
				direction = LEFT;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
