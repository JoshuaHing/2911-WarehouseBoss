import java.util.ArrayList;

public class Game {
  private Map map;
  private ArrayList<Map> gameMaps;
  private int numMoves;
  //private int numGoalBoxes;
  //private int currentLevel;

  public Game(){
    this.map = null;
    this.gameMaps = new ArrayList<Map>();
    this.numMoves = 0;
    //this.numGoalBoxes = 0;
  }

  public void moveUP(int level, int playerNum) {
		// first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		String whoAmI = "unknown"; //P or Q
		String iAmOn = "unknown"; //E or T
		String onTargetName = "unknown";//O or R
		//begin searching for player
		if(playerNum == 1){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(playerNum == 2){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("Q")||map.get(j).get(i).equals("R")){
						x = j;
						y = i;
					}
				}
			}
		}
		//set whoAmI, iAmOn, onTargetName
		if(map.get(x).get(y) != null){
			if(map.get(x).get(y).equals("P")){
				whoAmI = "P";
				iAmOn = "E";
				onTargetName = "O";
			}
			if(map.get(x).get(y).equals("Q")){
				whoAmI = "Q";
				iAmOn = "E";
				onTargetName = "R";
			}
			if(map.get(x).get(y).equals("O")){
				whoAmI = "P";
				iAmOn = "T";
				onTargetName = "O";
			}
			if(map.get(x).get(y).equals("R")){
				whoAmI = "Q";
				iAmOn = "T";
				onTargetName = "R";
			}
		}
		//if the thing we are trying to move into is E or T, then we can just do it - update where you were
		//and where you are going
		if(map.get(x-1).get(y).equals("E")||map.get(x-1).get(y).equals("T")){
		    numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x-1).get(y).equals("E")){
				map.get(x-1).set(y,whoAmI);
				map.get(x).set(y,iAmOn);
			}
			if(map.get(x-1).get(y).equals("T")){
				map.get(x-1).set(y,onTargetName);
				map.get(x).set(y,iAmOn);
			}
		//if the thing we are trying to move into is B or D (boxes), then we should analyse the next thing
		//beyond the box to see if we can - update things accordingly of course
		} else if(map.get(x-1).get(y).equals("B")||map.get(x-1).get(y).equals("D")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x-2).get(y).equals("E")||map.get(x-2).get(y).equals("T")){
				if(map.get(x-2).get(y).equals("E")){
					map.get(x-2).set(y,"B");
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x-2).get(y).equals("T")){
					map.get(x-2).set(y,"D");
					map.get(x).set(y,iAmOn);
					currMap.incNumGoalBoxes();
				}
				if(map.get(x-1).get(y).equals("B")){
					map.get(x-1).set(y,whoAmI);
					//movesMade.add(UP_MOVE_WB);
					
				}
				if(map.get(x-1).get(y).equals("D")){
					map.get(x-1).set(y,onTargetName);
					currMap.decNumGoalBoxes();
					//movesMade.add(UP_MOVE_WB);
				}
			}
		} else {
			//do nothing
		}
	}

	public void moveDOWN(int level, int playerNum) {
		// first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		String whoAmI = "unknown";
		String iAmOn = "unknown";
		String onTargetName = "unknown";
		if(playerNum == 1){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(playerNum == 2){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("Q")||map.get(j).get(i).equals("R")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(map.get(x).get(y) != null){
			whoAmI = map.get(x).get(y);
			if(whoAmI.equals("P")){
				whoAmI = "P";
				iAmOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				iAmOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				iAmOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				iAmOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x+1).get(y).equals("E")||map.get(x+1).get(y).equals("T")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x+1).get(y).equals("E")){
				map.get(x+1).set(y,whoAmI);
				map.get(x).set(y,iAmOn);
			}
			if(map.get(x+1).get(y).equals("T")){
				map.get(x+1).set(y,onTargetName);
				map.get(x).set(y,iAmOn);
			}
		} else if(map.get(x+1).get(y).equals("B")||map.get(x+1).get(y).equals("D")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x+2).get(y).equals("E")||map.get(x+2).get(y).equals("T")){
				if(map.get(x+2).get(y).equals("E")){
					map.get(x+2).set(y,"B");
					map.get(x).set(y,iAmOn);
					
				}
				if(map.get(x+2).get(y).equals("T")){
					map.get(x+2).set(y,"D");
					map.get(x).set(y,iAmOn);
					currMap.incNumGoalBoxes();
				}
				if(map.get(x+1).get(y).equals("B")){
					map.get(x+1).set(y,whoAmI);
					//movesMade.add(DOWN_MOVE_WB);
				}
				if(map.get(x+1).get(y).equals("D")){
					map.get(x+1).set(y,onTargetName);
					currMap.decNumGoalBoxes();
					//movesMade.add(DOWN_MOVE_WB);
				}
			}
		} else {
			//do nothing
		}

	}

	public void moveLEFT(int level, int playerNum) {
		// first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		String whoAmI = "unknown";
		String iAmOn = "unknown";
		String onTargetName = "unknown";
		if(playerNum == 1){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(playerNum == 2){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("Q")||map.get(j).get(i).equals("R")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(map.get(x).get(y) != null){
			whoAmI = map.get(x).get(y);
			if(whoAmI.equals("P")){
				whoAmI = "P";
				iAmOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				iAmOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				iAmOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				iAmOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x).get(y-1).equals("E")||map.get(x).get(y-1).equals("T")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x).get(y-1).equals("E")){
				map.get(x).set(y-1,whoAmI);
				map.get(x).set(y,iAmOn);
			}
			if(map.get(x).get(y-1).equals("T")){
				map.get(x).set(y-1,onTargetName);
				map.get(x).set(y,iAmOn);
			}
		} else if(map.get(x).get(y-1).equals("B")||map.get(x).get(y-1).equals("D")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x).get(y-2).equals("E")||map.get(x).get(y-2).equals("T")){
				if(map.get(x).get(y-2).equals("E")){
					map.get(x).set(y-2,"B");
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x).get(y-2).equals("T")){
					map.get(x).set(y-2,"D");
					map.get(x).set(y,iAmOn);
					currMap.incNumGoalBoxes();
				}
				if(map.get(x).get(y-1).equals("B")){
					map.get(x).set(y-1,whoAmI);
					//movesMade.add(LEFT_MOVE_WB);
				}
				if(map.get(x).get(y-1).equals("D")){
					map.get(x).set(y-1,onTargetName);
					currMap.decNumGoalBoxes();
					//movesMade.add(LEFT_MOVE_WB);
				}
			}
		} else {
			//do nothing
		}

	}

	public void moveRIGHT(int level, int playerNum) {
		// first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		String whoAmI = "unknown";
		String iAmOn = "unknown";
		String onTargetName = "unknown";
		if(playerNum == 1){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(playerNum == 2){
			for(int j=0;j<map.size();j++){
				for(int i=0;i<map.get(j).size();i++){
					if(map.get(j).get(i).equals("Q")||map.get(j).get(i).equals("R")){
						x = j;
						y = i;
					}
				}
			}
		}
		if(map.get(x).get(y) != null){
			whoAmI = map.get(x).get(y);
			if(whoAmI.equals("P")){
				whoAmI = "P";
				iAmOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				iAmOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				iAmOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				iAmOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x).get(y+1).equals("E")||map.get(x).get(y+1).equals("T")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x).get(y+1).equals("E")){
				map.get(x).set(y+1,whoAmI);
				map.get(x).set(y,iAmOn);
			}
			if(map.get(x).get(y+1).equals("T")){
				map.get(x).set(y+1,onTargetName);
				map.get(x).set(y,iAmOn);
				//currMap.incNumGoalBoxes();
			}
		} else if(map.get(x).get(y+1).equals("B")||map.get(x).get(y+1).equals("D")){
            numMovesInc();
            System.out.println("REMOVE THIS AFTER: numMoves = " + this.numMoves);
			if(map.get(x).get(y+2).equals("E")||map.get(x).get(y+2).equals("T")){
				if(map.get(x).get(y+2).equals("E")){
					map.get(x).set(y+2,"B");
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x).get(y+2).equals("T")){
					map.get(x).set(y+2,"D");
					map.get(x).set(y,iAmOn);
					currMap.incNumGoalBoxes();
				}
				if(map.get(x).get(y+1).equals("B")){
					map.get(x).set(y+1,whoAmI);
				}
				if(map.get(x).get(y+1).equals("D")){
					map.get(x).set(y+1,onTargetName);
					currMap.decNumGoalBoxes();
				}
			}
		} else {
			//do nothing
		}

	}


	public void resetMap(int level) {
		gameMaps.get(level).resetMap();
	}
	
	public Map getLevel(int level) {
		return this.gameMaps.get(level);
	}

	public void numMovesInc(){
	    this.numMoves++;
    }
	
	public void setMap(Map map) {
		this.map = map;
	}
	

	public void addMap(Map newMap) {
		this.gameMaps.add(newMap);
	}

	public int numMaps() {
		return this.gameMaps.size();
	}

	
	public ArrayList<ArrayList<String>> getMap() {
		ArrayList<ArrayList<String>> map = this.map.getMap();
		return map;
	}

	public boolean hasNextLevel(int level) {
		if (!(level == this.gameMaps.size()-1)) {
			System.out.println("wooho!!!");
			this.numMoves = 0;
			return true;
		} else {
			return false;
		}
	}
}
