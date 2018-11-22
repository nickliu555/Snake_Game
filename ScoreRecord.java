//package forKids;

public class ScoreRecord implements Comparable{
	private String userName;
	private double time;
	private int score;
	
	public ScoreRecord(String u, double t, int s){
		userName = u;
		time = t;
		score =s;
	}
	
	// Line is a colon-delmited string like this:
	//     username:time:score
	public ScoreRecord(String line){
		String[] stuff = line.split(":");
		userName = stuff[0];
		time = Double.parseDouble(stuff[1]);
		score = Integer.parseInt(stuff[2]);
	}
	
	public String getUser(){return userName;}
	public double getTime(){return time;}
	public double getScore(){return score;}
	
	//User-friendly format
	public String toString()
	{
		return "Username: "+userName+"; Time: "+time+"; Score: "+score +"\n";
	}
	
	public String formatForFile()
	{
		return userName+":"+time+":"+score;
	}

	@Override
	//return a positive number is greater, negative is less
	public int compareTo(Object other) {
		ScoreRecord that = (ScoreRecord)other; 
		if(this.score<that.score)
			return -500;
		if(this.score>that.score)
			return 27;
		return 0;
	}
}
