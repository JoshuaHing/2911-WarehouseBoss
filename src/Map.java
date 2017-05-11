import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<String>> map;			//Represents the contents of the map
	private int numGoalBoxes;							//The number of boxes that are at their goal states
	private int numGoals;								//The total number of goal squares in the map
	
	public Map(ArrayList<ArrayList<String>> map, int numGoals) {
		this.map = map;
		this.numGoals = numGoals;
		this.numGoalBoxes = 0;
	}
	
	public ArrayList<ArrayList<String>> getMap() {
		return this.map;
	}
	
	public int getNumGoalBoxes() {
		return this.numGoalBoxes;
	}
	
	public void incNumGoalBoxes() {
		this.numGoalBoxes++;
	}
	
	public void decNumGoalBoxes() {
		if(this.numGoalBoxes >= 0) {
			this.numGoalBoxes--;
		}
	}
	
	public void setGoals(int goals) {
		this.numGoals = goals;
	}
		
	public boolean isDone() {					//If all the goals are met, return true. Else, return false.
		System.out.println("numGoals = " + numGoals + " numBoxes = " + this.numGoalBoxes);  //***FOR TESTING***
		if(this.numGoalBoxes == this.numGoals) {
			return true;
		} else {
			return false;
		}
	}
}
