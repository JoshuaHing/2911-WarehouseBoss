import java.util.ArrayList;

public class Game {
	private Map map;										//Represents the current map of the game
	private ArrayList<ArrayList<String>> initialMap;		//The starting map of the game (Used for restart button)
	private ArrayList<Map> gameMaps;						//Holds all of the maps of the game
	
	public Game(){
		this.map = new Map(new ArrayList<ArrayList<String>>(), 0);	
		this.initialMap = new ArrayList<ArrayList<String>>();
		this.gameMaps = new ArrayList<Map>();
	}

	public void moveUP(int level) {
		//first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++){
				if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
					x = j;
					y = i;
				}
			}
		}
		if(map.get(x).get(y).equals("P")){
			if(map.get(x-1).get(y).equals("E")||map.get(x-1).get(y).equals("T")){
				if(map.get(x-1).get(y).equals("E")){
					map.get(x-1).set(y,"P");
					map.get(x).set(y,"E");
				}
				if(map.get(x-1).get(y).equals("T")){
					map.get(x-1).set(y,"O");
					map.get(x).set(y,"E");
				}
			} else if(map.get(x-1).get(y).equals("B")||map.get(x-1).get(y).equals("D")){
				if(map.get(x-2).get(y).equals("E")||map.get(x-2).get(y).equals("T")){
					if(map.get(x-2).get(y).equals("E")){
						map.get(x-2).set(y,"B");
						map.get(x).set(y,"E");
					}
					if(map.get(x-2).get(y).equals("T")){
						map.get(x-2).set(y,"D");
						map.get(x).set(y,"E");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x-1).get(y).equals("B")){
						map.get(x-1).set(y,"P");
					}
					if(map.get(x-1).get(y).equals("D")){
						map.get(x-1).set(y,"O");
					}
				}
			} else {
				//do nothing
			}
		}
		if(map.get(x).get(y).equals("O")){
			if(map.get(x-1).get(y).equals("E")||map.get(x-1).get(y).equals("T")){
				if(map.get(x-1).get(y).equals("E")){
					map.get(x-1).set(y,"P");
					map.get(x).set(y,"T");
				}
				if(map.get(x-1).get(y).equals("T")){
					map.get(x-1).set(y,"O");
					map.get(x).set(y,"T");
					currMap.incNumGoalBoxes();
				}
			} else if(map.get(x-1).get(y).equals("B")||map.get(x-1).get(y).equals("D")){
				if(map.get(x-2).get(y).equals("E")||map.get(x-2).get(y).equals("T")){
					if(map.get(x-2).get(y).equals("E")){
						map.get(x-2).set(y,"B");
						map.get(x).set(y,"T");
					}
					if(map.get(x-2).get(y).equals("T")){
						map.get(x-2).set(y,"D");
						map.get(x).set(y,"T");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x-1).get(y).equals("B")){
						map.get(x-1).set(y,"P");
					}
					if(map.get(x-1).get(y).equals("D")){
						map.get(x-1).set(y,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public void moveDOWN(int level) {
		//first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++) {
				if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
					x = j;
					y = i;
				}
			}
		}
		if(map.get(x).get(y).equals("P")){
			if(map.get(x+1).get(y).equals("E")||map.get(x+1).get(y).equals("T")){
				if(map.get(x+1).get(y).equals("E")){
					map.get(x+1).set(y,"P");
					map.get(x).set(y,"E");
				}
				if(map.get(x+1).get(y).equals("T")){
					map.get(x+1).set(y,"O");
					map.get(x).set(y,"E");
				}
			} else if(map.get(x+1).get(y).equals("B")||map.get(x+1).get(y).equals("D")){
				if(map.get(x+2).get(y).equals("E")||map.get(x+2).get(y).equals("T")){
					if(map.get(x+2).get(y).equals("E")){
						map.get(x+2).set(y,"B");
						map.get(x).set(y,"E");
					}
					if(map.get(x+2).get(y).equals("T")){
						map.get(x+2).set(y,"D");
						map.get(x).set(y,"E");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x+1).get(y).equals("B")){
						map.get(x+1).set(y,"P");
					}
					if(map.get(x+1).get(y).equals("D")){
						map.get(x+1).set(y,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
			
		}
		if(map.get(x).get(y).equals("O")){
			if(map.get(x+1).get(y).equals("E")||map.get(x+1).get(y).equals("T")){
				if(map.get(x+1).get(y).equals("E")){
					map.get(x+1).set(y,"P");
					map.get(x).set(y,"T");
				}
				if(map.get(x+1).get(y).equals("T")){
					map.get(x+1).set(y,"O");
					map.get(x).set(y,"T");
					currMap.incNumGoalBoxes();
				}
			} else if(map.get(x+1).get(y).equals("B")||map.get(x+1).get(y).equals("D")){
				if(map.get(x+2).get(y).equals("E")||map.get(x+2).get(y).equals("T")){
					if(map.get(x+2).get(y).equals("E")){
						map.get(x+2).set(y,"B");
						map.get(x).set(y,"T");
					}
					if(map.get(x+2).get(y).equals("T")){
						map.get(x+2).set(y,"D");
						map.get(x).set(y,"T");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x+1).get(y).equals("B")){
						map.get(x+1).set(y,"P");
					}
					if(map.get(x+1).get(y).equals("D")){
						map.get(x+1).set(y,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public void moveLEFT(int level) {
		//first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++){
				if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
					x = j;
					y = i;
				}
			}
		}
		if(map.get(x).get(y).equals("P")){
			if(map.get(x).get(y-1).equals("E")||map.get(x).get(y-1).equals("T")){
				if(map.get(x).get(y-1).equals("E")){
					map.get(x).set(y-1,"P");
					map.get(x).set(y,"E");
				}
				if(map.get(x).get(y-1).equals("T")){
					map.get(x).set(y-1,"O");
					map.get(x).set(y,"E");
				}
			} else if(map.get(x).get(y-1).equals("B")||map.get(x).get(y-1).equals("D")){
				if(map.get(x).get(y-2).equals("E")||map.get(x).get(y-2).equals("T")){
					if(map.get(x).get(y-2).equals("E")){
						map.get(x).set(y-2,"B");
						map.get(x).set(y,"E");
					}
					if(map.get(x).get(y-2).equals("T")){
						map.get(x).set(y-2,"D");
						map.get(x).set(y,"E");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x).get(y-1).equals("B")){
						map.get(x).set(y-1,"P");
					}
					if(map.get(x).get(y-1).equals("D")){
						map.get(x).set(y-1,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		if(map.get(x).get(y).equals("O")){
			if(map.get(x).get(y-1).equals("E")||map.get(x).get(y-1).equals("T")){
				if(map.get(x).get(y-1).equals("E")){
					map.get(x).set(y-1,"P");
					map.get(x).set(y,"T");
				}
				if(map.get(x).get(y-1).equals("T")){
					map.get(x).set(y-1,"O");
					map.get(x).set(y,"T");
					currMap.incNumGoalBoxes();
				}
			} else if(map.get(x).get(y-1).equals("B")||map.get(x).get(y-1).equals("D")){
				if(map.get(x).get(y-2).equals("E")||map.get(x).get(y-2).equals("T")){
					if(map.get(x).get(y-2).equals("E")){
						map.get(x).set(y-2,"B");
						map.get(x).set(y,"T");
					}
					if(map.get(x).get(y-2).equals("T")){
						map.get(x).set(y-2,"D");
						map.get(x).set(y,"T");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x).get(y-1).equals("B")){
						map.get(x).set(y-1,"P");
					}
					if(map.get(x).get(y-1).equals("D")){
						map.get(x).set(y-1,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public void moveRIGHT(int level) {
		//first find person P or O
		int x = 0;
		int y = 0;
		Map currMap = this.gameMaps.get(level);
		ArrayList<ArrayList<String>> map = currMap.getMap();
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++){
				if(map.get(j).get(i).equals("P")||map.get(j).get(i).equals("O")){
					x = j;
					y = i;
				}
			}
		}
		if(map.get(x).get(y).equals("P")){
			if(map.get(x).get(y+1).equals("E")||map.get(x).get(y+1).equals("T")){
				if(map.get(x).get(y+1).equals("E")){
					map.get(x).set(y+1,"P");
					map.get(x).set(y,"E");
				}
				if(map.get(x).get(y+1).equals("T")){
					map.get(x).set(y+1,"O");
					map.get(x).set(y,"E");
				}
			} else if(map.get(x).get(y+1).equals("B")||map.get(x).get(y+1).equals("D")){
				if(map.get(x).get(y+2).equals("E")||map.get(x).get(y+2).equals("T")){
					if(map.get(x).get(y+2).equals("E")){
						map.get(x).set(y+2,"B");
						map.get(x).set(y,"E");
					}
					if(map.get(x).get(y+2).equals("T")){
						map.get(x).set(y+2,"D");
						map.get(x).set(y,"E");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x).get(y+1).equals("B")){
						map.get(x).set(y+1,"P");
					}
					if(map.get(x).get(y+1).equals("D")){
						map.get(x).set(y+1,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		if(map.get(x).get(y).equals("O")){
			if(map.get(x).get(y+1).equals("E")||map.get(x).get(y+1).equals("T")){
				if(map.get(x).get(y+1).equals("E")){
					map.get(x).set(y+1,"P");
					map.get(x).set(y,"T");
				}
				if(map.get(x).get(y+1).equals("T")){
					map.get(x).set(y+1,"O");
					map.get(x).set(y,"T");
				}
			} else if(map.get(x).get(y+1).equals("B")||map.get(x).get(y+1).equals("D")){
				if(map.get(x).get(y+2).equals("E")||map.get(x).get(y+2).equals("T")){
					if(map.get(x).get(y+2).equals("E")){
						map.get(x).set(y+2,"B");
						map.get(x).set(y,"T");
					}
					if(map.get(x).get(y+2).equals("T")){
						map.get(x).set(y+2,"D");
						map.get(x).set(y,"T");
						currMap.incNumGoalBoxes();
					}
					if(map.get(x).get(y+1).equals("B")){
						map.get(x).set(y+1,"P");
					}
					if(map.get(x).get(y+1).equals("D")){
						map.get(x).set(y+1,"O");
						currMap.decNumGoalBoxes();
					}
				}
			} else {
				//do nothing
			}
		}
		
	}

	public Map getLevel(int level) {
		return this.gameMaps.get(level);
	}

	
	public void setInitialMap(ArrayList<ArrayList<String>> map) {
		this.initialMap = map;
	}
	
	public ArrayList<ArrayList<String>> getInitialMap() {
		return this.initialMap;
	}
	public void setMap(ArrayList<ArrayList<String>> newMap) {
		ArrayList<ArrayList<String>> map = this.map.getMap();
		map = newMap;
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
	
	public boolean hasNextLevel(int level) {							//For checking if the game has another level to progress to.
		System.out.println("gameMapsSize = " + gameMaps.size());
		if(level < this.gameMaps.size()-1) {							//If the current level isn't the last, return true
			System.out.println("wooho!!!");
			return true;
		} else {														//If there are no more levels, return false
			System.out.println("no!!!!!!");
			return false;											
		}
	}
}
