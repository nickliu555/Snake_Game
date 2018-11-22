//package forKids;

public class OrderedList {
	
	private OrderedListNode first;
	
	public OrderedList()
	{
		first = null;
	}
	
	public boolean isEmpty(){return first==null;}
	
	public OrderedListNode getFirst()
	{
		return first;
	}
	
	public void addFirst( Comparable newValue)
	{
		first = new OrderedListNode( newValue, first);
	}
	
	/*
	 * Preconditions:  The elements that are currently in the list
	 *  (if there are any) are arranged in order
	 *  Postconditions:  A new OrderedListNode is created containing newValue
	 *  	This new node is inserted into the list into the appropriate place
	 *  	so that the list is STILL in order
	 *  	(you can decide if you want ascending or descending order) 
	 */
	public void add(Comparable newValue)
	{
		if(isEmpty())
		{
			first = new OrderedListNode(newValue, null);
			return;
		}
		
		OrderedListNode curr = first;
		OrderedListNode prev = null;
		//places the ListNodes in descending order
		while(curr != null && curr.getValue().compareTo(newValue) > 0)
		{
			prev = curr;
			curr = curr.getNext();
		}
		
		//if empty
		if(isEmpty())
		{
			prev.setNext(new OrderedListNode(newValue, null));
		}
		//if only one node
		else if(prev == null)
		{
			OrderedListNode newNode = new OrderedListNode(newValue, first);
			first = newNode;
		}
		//the "normal" situation
		else
		{
			prev.setNext(new OrderedListNode(newValue, curr));
		}
		
	}
	
	//prints out the LinkedList in order, separated by a space
	public String toString()
	{
		String ans="";
		OrderedListNode curr=first;
		while(curr!=null)
		{
			ans+=curr.getValue()+" ";
			curr = curr.getNext();
		}
		return ans;
	}
	
	//prints into a file, each ScoreRecord is separated by a line
	public String toFileString()
	{
		String ans="";
		OrderedListNode curr=first;
		while(curr!=null)
		{
			ans+=( ( ScoreRecord )( curr.getValue() ) ).formatForFile()+"\r\n";
			curr = curr.getNext();
		}
		return ans;
	}
	
}
