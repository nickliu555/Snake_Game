//package forKids;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class ScoreFrame extends JFrame{
	/** MAIN FOR TESTING PURPOSES (so you don't have to play snake so many times)
	 you can just manually change the score record that is being constructed
	 run this ScoreFrame.java & check that the record is inserted correctly!
	**/
	public static void main(String[] args){new ScoreFrame(new ScoreRecord("Testing",1,-1));}
	
	//private member variables
	public static final String FILEDIR = "C:/LiuSnakeScores";//<<<<<<<Change this to include your last name
	public static final String FILENAME = "topScores.txt";
	public static final String OUTPUTFILENAME = "topScores.txt";
	public static final int WIDTH = 500, HEIGHT = 700;
	
	//private OrderedList theScores;
	private JTextArea display; //you will append the scores into here 
	private JScrollPane scrolly;
	
	ScoreRecord current; //the score record for the current game
	
	/** !!  you will need to declare some type of List to store all of the scores into !!**/
	private OrderedList oldScores;
	
	
	//Pass in the scoreRecord for the current game!
	public ScoreFrame( ScoreRecord scoreFromThisGame ){
		super("Top Scores");		
		current = scoreFromThisGame;
		
		oldScores = new OrderedList();
		display = new JTextArea();
		display.setEditable(false);
		//display.setText("You will display the top scores here!");
		scrolly = new JScrollPane( display );
		
		readFromFile();
		writeToFile();
		
		this.add(scrolly, BorderLayout.CENTER);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//this function will:
	// 1) Read in all of the OLD scores that are stored in the text file & store them into the ORderedList
	// 2) place current into the OrderedList
	public void readFromFile()
	{
		try{		
			BufferedReader br = openFileForReading();
			if(br!=null)
			{//if the file exists, read from it
				//Now you can br.readLine() to grab the next line from the file!
				//when there is no more left in the file, br.readLine will give you null
				String input = br.readLine();
				while(input!=null)
				{
					//System.out.println(input);
					oldScores.add( new ScoreRecord(input) );
					input = br.readLine();
				}
				
			
				//don't forget to do this when you are done!
				br.close();
			}
			
		}		
		catch(Exception ex){ex.printStackTrace();}
		
		//put the current record in the correct spot in your data structure
		oldScores.add(current);
		//display each record <using display.append()>
		OrderedListNode curr = oldScores.getFirst();
		while(curr!=null)
		{
			display.append(curr.getValue()+"");
			curr = curr.getNext();
		}
		


	}

	//this function will:
	//1) Write each of the ScoreRecords out into the file
	//   Be sure that they are in a colon-delimited format
	public void writeToFile(){
		try{			
			PrintWriter out = openFileForWriting();
			//now you can out.println(?) to write something to the file
			out.print(oldScores.toFileString());
			
			
			//don't forget to do this!
			out.close();
		}
		catch(Exception ex){
			System.out.println("ERROR!!?");
			ex.printStackTrace();
		}
	}
	
	/**********************************************************************/
	/**Don't mess with these functions down here.  But you can call them **/
	private BufferedReader openFileForReading(){
		FileReader reeder;
		BufferedReader br=null;		
		try{
			reeder = new FileReader(new File(FILEDIR, FILENAME));
			br = new BufferedReader(reeder);			
		}
		catch(FileNotFoundException fnf){System.out.println("First time playing");}
		catch(Exception ex){ex.printStackTrace();}
		return br;
	}
	
	private PrintWriter openFileForWriting(){
		File scoreDir = new File(FILEDIR);
		File scoreFile = new File(FILEDIR, OUTPUTFILENAME);
		FileWriter file = null;
		try{			
			if(!scoreDir.exists()){
				boolean made = scoreDir.mkdirs();
				System.out.println(made);
				scoreFile.createNewFile();
			}
			file = new FileWriter(scoreFile.getAbsolutePath());
			
		}catch(Exception ex){
			System.out.println("ERROR!!?");
			ex.printStackTrace();
		}
		return new PrintWriter(file);
	}
	

	
}
