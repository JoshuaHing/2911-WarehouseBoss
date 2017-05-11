import java.util.ArrayList;

public class Map {

  private ArrayList<ArrayList<String>> map;
  private int numGoalBoxes;
  private int numGoals;

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

  public void setGoals(int goals) {
    this.numGoals = goals;
  }

  public boolean isDone() {
    //System.out.println("numGoals = " + numGoals + " numBoxes = " + this.numGoalBoxes);  //***FOR TESTING***
    if(this.numGoalBoxes == this.numGoals) {
      return true;
    } else {
      return false;
    }
  }
}
