import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<String>> map;
	private ArrayList<ArrayList<String>> initialMap;
	private int numGoalBoxes;
	private int numGoals;

	public Map(ArrayList<ArrayList<String>> map, int numGoals) {
		this.map = map;
		this.numGoals = numGoals;
		this.numGoalBoxes = 0;	
		this.initialMap = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				String currString = map.get(i).get(j);
				ArrayList<String> newList = new ArrayList<String>();
				initialMap.add(newList);
				initialMap.get(i).add(currString);
			}
		}
	}

	public void resetMap() {
		ArrayList<ArrayList<String>> initialMap = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				String currString = this.initialMap.get(i).get(j);
				ArrayList<String> newList = new ArrayList<String>();
				initialMap.add(newList);
				initialMap.get(i).add(currString);
			}
		}
		printMap(initialMap);
		this.numGoalBoxes = 0;
		this.map = initialMap;	
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
		this.numGoalBoxes--;
	}

	public void setGoals(int goals) {
		this.numGoals = goals;
	}
	
	public void setGoalBoxes(int numGoalBoxes) {
		this.numGoalBoxes = numGoalBoxes;
	}
	
	public void printMap(ArrayList<ArrayList<String>> initialMap) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				String currString = initialMap.get(i).get(j);
				System.out.print(currString + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isDone() {
		// System.out.println("numGoals = " + numGoals + " numBoxes = " +
		// this.numGoalBoxes); //***FOR TESTING***
		if (this.numGoalBoxes == this.numGoals) {
			return true;
		} else {
			return false;
		}
	}
}
