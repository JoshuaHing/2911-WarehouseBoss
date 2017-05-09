import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private ArrayList<ArrayList<String>> map;
	//private ArrayList<String> moves;
	
	public Game(){
		this.map = new ArrayList<ArrayList<String>>();
		//this.moves = new ArrayList<String>();
	}

	
	public void moveUP() {
		//first find person P or O
		int x = 0;
		int y = 0;
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
		
	}
	public void moveDOWN() {
		//first find person P or O
		int x = 0;
		int y = 0;
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++){
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
					}
					if(map.get(x+1).get(y).equals("B")){
						map.get(x+1).set(y,"P");
					}
					if(map.get(x+1).get(y).equals("D")){
						map.get(x+1).set(y,"O");
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
					}
					if(map.get(x+1).get(y).equals("B")){
						map.get(x+1).set(y,"P");
					}
					if(map.get(x+1).get(y).equals("D")){
						map.get(x+1).set(y,"O");
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public void moveLEFT() {
		//first find person P or O
		int x = 0;
		int y = 0;
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
					}
					if(map.get(x).get(y-1).equals("B")){
						map.get(x).set(y-1,"P");
					}
					if(map.get(x).get(y-1).equals("D")){
						map.get(x).set(y-1,"O");
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
					}
					if(map.get(x).get(y-1).equals("B")){
						map.get(x).set(y-1,"P");
					}
					if(map.get(x).get(y-1).equals("D")){
						map.get(x).set(y-1,"O");
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public void moveRIGHT() {
		//first find person P or O
		int x = 0;
		int y = 0;
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
					}
					if(map.get(x).get(y+1).equals("B")){
						map.get(x).set(y+1,"P");
					}
					if(map.get(x).get(y+1).equals("D")){
						map.get(x).set(y+1,"O");
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
					}
					if(map.get(x).get(y+1).equals("B")){
						map.get(x).set(y+1,"P");
					}
					if(map.get(x).get(y+1).equals("D")){
						map.get(x).set(y+1,"O");
					}
				}
			} else {
				//do nothing
			}
		}
		
	}
	public ArrayList<ArrayList<String>> getMap() {
		return map;
	}
	/*public ArrayList<String> getMoves() {
		return moves;
	}*/
	public void printMap(){
		System.out.println("MapStart");
		for(int j=0;j<map.size();j++){
			for(int i=0;i<map.get(j).size();i++){
				System.out.print(map.get(j).get(i) + " ");
			}
			System.out.print("NextLine");
			System.out.printf("%n");
		}
		System.out.println("MapEnd");
	}
	/*public void printMoves(){
		System.out.println("MoveStart");
		for(int i=0;i<moves.size();i++){
			System.out.println(moves.get(i));
		}
		System.out.println("MoveEnd");
	}*/
	
	public void setMap(ArrayList<ArrayList<String>> map) {
		this.map = map;
	}
}
