import java.util.ArrayList;
import java.util.Stack;

public class Game {
  private Map map;
  private ArrayList<Map> gameMaps;
  //private int numGoalBoxes;
  //private int currentLevel;
  private Stack<Integer> movesMade;
  
  private static final int PLAYER_ONE_UP_MOVE = 0;
  private static final int PLAYER_ONE_UP_MOVE_WB = 1;
  private static final int PLAYER_ONE_DOWN_MOVE = 2;
  private static final int PLAYER_ONE_DOWN_MOVE_WB = 3;
  private static final int PLAYER_ONE_LEFT_MOVE = 4;
  private static final int PLAYER_ONE_LEFT_MOVE_WB = 5;
  private static final int PLAYER_ONE_RIGHT_MOVE = 6;
  private static final int PLAYER_ONE_RIGHT_MOVE_WB = 7;
  private static final int PLAYER_TWO_UP_MOVE = 8;
  private static final int PLAYER_TWO_UP_MOVE_WB = 9;
  private static final int PLAYER_TWO_DOWN_MOVE = 10;
  private static final int PLAYER_TWO_DOWN_MOVE_WB = 11;
  private static final int PLAYER_TWO_LEFT_MOVE = 12;
  private static final int PLAYER_TWO_LEFT_MOVE_WB = 13;
  private static final int PLAYER_TWO_RIGHT_MOVE = 14;
  private static final int PLAYER_TWO_RIGHT_MOVE_WB = 15;
  

  public Game(){
    this.map = null;
    this.gameMaps = new ArrayList<Map>();
    //this.numGoalBoxes = 0;
    this.movesMade = new Stack<Integer>();
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
			if(map.get(x-1).get(y).equals("E")){
				map.get(x-1).set(y,whoAmI);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_UP_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_UP_MOVE);
				}
			}
			if(map.get(x-1).get(y).equals("T")){
				map.get(x-1).set(y,onTargetName);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_UP_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_UP_MOVE);
				}
			}
		//if the thing we are trying to move into is B or D (boxes), then we should analyse the next thing
		//beyond the box to see if we can - update things accordingly of course
		} else if(map.get(x-1).get(y).equals("B")||map.get(x-1).get(y).equals("D")){
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
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_UP_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_UP_MOVE_WB);
					}
					
				}
				if(map.get(x-1).get(y).equals("D")){
					map.get(x-1).set(y,onTargetName);
					currMap.decNumGoalBoxes();
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_UP_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_UP_MOVE_WB);
					}
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
			if(map.get(x+1).get(y).equals("E")){
				map.get(x+1).set(y,whoAmI);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_DOWN_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_DOWN_MOVE);
				}
			}
			if(map.get(x+1).get(y).equals("T")){
				map.get(x+1).set(y,onTargetName);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_DOWN_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_DOWN_MOVE);
				}
			}
		} else if(map.get(x+1).get(y).equals("B")||map.get(x+1).get(y).equals("D")){
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
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_DOWN_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_DOWN_MOVE_WB);
					}
				}
				if(map.get(x+1).get(y).equals("D")){
					map.get(x+1).set(y,onTargetName);
					currMap.decNumGoalBoxes();
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_DOWN_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_DOWN_MOVE_WB);
					}
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
			if(map.get(x).get(y-1).equals("E")){
				map.get(x).set(y-1,whoAmI);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_LEFT_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_LEFT_MOVE);
				}
			}
			if(map.get(x).get(y-1).equals("T")){
				map.get(x).set(y-1,onTargetName);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_LEFT_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_LEFT_MOVE);
				}
			}
		} else if(map.get(x).get(y-1).equals("B")||map.get(x).get(y-1).equals("D")){
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
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_LEFT_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_LEFT_MOVE_WB);
					}
				}
				if(map.get(x).get(y-1).equals("D")){
					map.get(x).set(y-1,onTargetName);
					currMap.decNumGoalBoxes();
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_LEFT_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_LEFT_MOVE_WB);
					}
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
			if(map.get(x).get(y+1).equals("E")){
				map.get(x).set(y+1,whoAmI);
				map.get(x).set(y,iAmOn);
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_RIGHT_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_RIGHT_MOVE);
				}
			}
			if(map.get(x).get(y+1).equals("T")){
				map.get(x).set(y+1,onTargetName);
				map.get(x).set(y,iAmOn);
				//currMap.incNumGoalBoxes();
				if(playerNum == 1) {
					movesMade.add(PLAYER_ONE_RIGHT_MOVE);
				} else {
					movesMade.add(PLAYER_TWO_RIGHT_MOVE);
				}
			}
		} else if(map.get(x).get(y+1).equals("B")||map.get(x).get(y+1).equals("D")){
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
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_RIGHT_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_RIGHT_MOVE_WB);
					}
				}
				if(map.get(x).get(y+1).equals("D")){
					map.get(x).set(y+1,onTargetName);
					currMap.decNumGoalBoxes();
					if(playerNum == 1) {
						movesMade.add(PLAYER_ONE_RIGHT_MOVE_WB);
					} else {
						movesMade.add(PLAYER_TWO_RIGHT_MOVE_WB);
					}
				}
			}
		} else {
			//do nothing
		}

	}
	
	/*public void printMovesMade() {
		while(!this.movesMade.isEmpty()) {
			int curr = movesMade.remove();
			System.out.println(curr + " ");
		}
	}*/
	
	//Now, we have to reverse the moves that have been made.

	
	public void undo(int level) {
		if(!movesMade.isEmpty()) {
			int lastMove = movesMade.pop();
			//Need to be able to move with the box
			//PlayerOne - 0-7
			//PlayerTwo - 8-15

			Map currMap = this.gameMaps.get(level);
			ArrayList<ArrayList<String>> map = currMap.getMap();
			int x = 0;
			int y = 0;
			String whoAmI = "unknown"; //P or Q
			String iAmOn = "unknown"; //E or T
			String onTargetName = "unknown";//O or R
			int playerNum = 0;
			if(lastMove <= 7) {
				playerNum = 1;
			} else {
				playerNum = 2;
			}
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

			if(lastMove == PLAYER_ONE_UP_MOVE || lastMove == PLAYER_TWO_UP_MOVE) {
				System.out.println("a");
				if(map.get(x+1).get(y).equals("E")){
					map.get(x+1).set(y,whoAmI);
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x+1).get(y).equals("T")){
					map.get(x+1).set(y,onTargetName);
					map.get(x).set(y,iAmOn);
				}
			} else if(lastMove == PLAYER_ONE_DOWN_MOVE || lastMove == PLAYER_TWO_DOWN_MOVE) {
				System.out.println("b");
				if(map.get(x-1).get(y).equals("E")){
					map.get(x-1).set(y,whoAmI);
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x-1).get(y).equals("T")){
					map.get(x-1).set(y,onTargetName);
					map.get(x).set(y,iAmOn);
				}
			} else if(lastMove == PLAYER_ONE_LEFT_MOVE || lastMove == PLAYER_TWO_LEFT_MOVE) {
				System.out.println("c");
				if(map.get(x).get(y+1).equals("E")){
					map.get(x).set(y+1,whoAmI);
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x).get(y+1).equals("T")){
					map.get(x).set(y+1,onTargetName);
					map.get(x).set(y,iAmOn);
					//currMap.incNumGoalBoxes();
				}

			} else if(lastMove == PLAYER_ONE_RIGHT_MOVE || lastMove == PLAYER_TWO_RIGHT_MOVE) {
				System.out.println("d");
				if(map.get(x).get(y-1).equals("E")){
					map.get(x).set(y-1,whoAmI);
					map.get(x).set(y,iAmOn);
				}
				if(map.get(x).get(y-1).equals("T")){
					map.get(x).set(y-1,onTargetName);
					map.get(x).set(y,iAmOn);
				}
			} else {
				if(lastMove  == PLAYER_ONE_DOWN_MOVE_WB || lastMove == PLAYER_TWO_DOWN_MOVE_WB) {
				//character has to move up AND box has to move up
					if(map.get(x+1).get(y).equals("D")){
						map.get(x+1).set(y,"T");
						map.get(x).set(y, "B");
						if(playerNum == 1) {
							map.get(x-1).set(y, "P");
						} else {
							map.get(x-1).set(y, "Q");
						}
						currMap.decNumGoalBoxes();
					}
					if(map.get(x+1).get(y).equals("B")){
						map.get(x).set(y,"B");
						if(map.get(x-1).get(y).equals("T")) {
							if(playerNum == 1) {
								map.get(x-1).set(y,"O");
							} else if(playerNum == 2) {
								map.get(x-1).set(y,"P");
							}
						} else {
							if(playerNum == 1) {
								map.get(x-1).set(y, "P");
							} else {
								map.get(x-1).set(y, "Q");
							}
						}
						map.get(x+1).set(y, "E");
					}
				} else if(lastMove == PLAYER_ONE_UP_MOVE_WB || lastMove == PLAYER_TWO_UP_MOVE_WB) {
					if(map.get(x-1).get(y).equals("D")){
						map.get(x-1).set(y,"T");
						map.get(x).set(y,"B");
						if(playerNum == 1) {
							map.get(x+1).set(y, "P");
						} else {
							map.get(x+1).set(y, "Q");
						}
						
						currMap.decNumGoalBoxes();
					}
					if(map.get(x-1).get(y).equals("B")){
						map.get(x).set(y,"B");
						if(map.get(x+1).get(y).equals("T")) {
							if(playerNum == 1) {
								map.get(x+1).set(y, "O");
							} else if(playerNum == 2) {
								map.get(x+1).set(y, "P");
							}
						} else {
							if(playerNum == 1) {
								map.get(x+1).set(y, "P");
							} else {
								map.get(x+1).set(y, "Q");
							}
						}
						map.get(x-1).set(y, "E");
						currMap.decNumGoalBoxes();
					}
					
				} else if(lastMove == PLAYER_ONE_LEFT_MOVE_WB || lastMove == PLAYER_TWO_LEFT_MOVE_WB) {
					System.out.println("move = " + map.get(x).get(y+1));
					if(map.get(x).get(y-1).equals("D")){
						map.get(x).set(y-1,"T");
						map.get(x).set(y, "B");
						if(playerNum == 1) {
							map.get(x).set(y+1, "P");
						} else {
							map.get(x).set(y+1, "Q");
						}
						
						currMap.decNumGoalBoxes();
					}
					if(map.get(x).get(y-1).equals("B")){
						map.get(x).set(y,"B");
						if(map.get(x).get(y+1).equals("T")) {
							if(playerNum == 1) {
								map.get(x).set(y+1,"O");
							} else if(playerNum == 2) {
								map.get(x).set(y+1,"R");
							}
						} else {
							if(playerNum == 1) {
								map.get(x).set(y+1, "P");
							} else {
								map.get(x).set(y+1, "Q");
							}
						}
						map.get(x).set(y-1, "E");
					}	
				} else if(lastMove == PLAYER_ONE_RIGHT_MOVE_WB || lastMove == PLAYER_TWO_RIGHT_MOVE_WB) {
					if(map.get(x).get(y+1).equals("D")){
						map.get(x).set(y+1,"T");
						map.get(x).set(y, "B");
						if(playerNum == 1) {
							map.get(x).set(y-1, "P");
						} else {
							map.get(x).set(y-1, "Q");
						}
						currMap.decNumGoalBoxes();
					}
					if(map.get(x).get(y+1).equals("B")){
						map.get(x).set(y,"B");
						if(map.get(x).get(y-1).equals("T")) {
							map.get(x).set(y-1,"O");
						} else {
							if(playerNum == 1) {
								map.get(x).set(y-1, "P");
							} else {
								map.get(x).set(y-1, "Q");
							}
						}
						map.get(x).set(y+1, "E");
					}
				}
			}
		} else {
			System.out.println("Moves list is empty. Cannot undo.");
		}
	}

	public void resetMovesMade() {
		Stack<Integer> newMovesMade = new Stack<Integer>();
		this.movesMade = newMovesMade;								//Make it point to a new queue
	}
	
	public void resetMap(int level) {
		gameMaps.get(level).resetMap();
	}
	
	public Map getLevel(int level) {
		return this.gameMaps.get(level);
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
			return true;
		} else {
			return false;
		}
	}
}
