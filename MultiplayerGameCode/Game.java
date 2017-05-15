import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private ArrayList<ArrayList<String>> map;
	private ArrayList<String> moves1;
	private ArrayList<String> moves2;
	
	public Game(){
		this.map = new ArrayList<ArrayList<String>>();
		this.moves1 = new ArrayList<String>();
		this.moves2 = new ArrayList<String>();
	}
	public static void main(String[] args){
		Game game = new Game();
		ArrayList<ArrayList<String>> map = game.getMap();
		ArrayList<String> moves1 = game.getMoves1();
		ArrayList<String> moves2 = game.getMoves2();
		//****************SCANNER STARTS****************
		Scanner sc = null;
		try{
			sc = new Scanner(new FileReader(args[0]));
			while(sc.hasNext()){
				if(sc.hasNext("#")){
					sc.nextLine();
				}
				if(sc.hasNext("MapStart")){
					sc.nextLine();
					int i = 0;
					while(!sc.hasNext("MapEnd")){
						ArrayList<String> newList = new ArrayList<String>();
						map.add(newList);
						while(!sc.hasNext("NextLine")){
							if(sc.hasNext("P")||sc.hasNext("B")||sc.hasNext("T")||sc.hasNext("W")||sc.hasNext("E")||sc.hasNext("O")||sc.hasNext("D")||sc.hasNext("Q")||sc.hasNext("R")){
								map.get(i).add(sc.next());
							}
						}
						i++;
						sc.nextLine();
					}	
					sc.nextLine();
				}
				if(sc.hasNext("MoveStart1")){
					sc.nextLine();
					while(!sc.hasNext("MoveEnd1")){
						if(sc.hasNext("UP")||sc.hasNext("DOWN")||sc.hasNext("LEFT")||sc.hasNext("RIGHT")){
							moves1.add(sc.next());
						}
						sc.nextLine();
					}
					sc.nextLine();
				}
				if(sc.hasNext("MoveStart2")){
					sc.nextLine();
					while(!sc.hasNext("MoveEnd2")){
						if(sc.hasNext("UP")||sc.hasNext("DOWN")||sc.hasNext("LEFT")||sc.hasNext("RIGHT")){
							moves2.add(sc.next());
						}
						sc.nextLine();
					}
					sc.nextLine();
				}
			}
		} catch (FileNotFoundException e){}
		finally{
			if (sc != null) sc.close();
		}
		//****************SCANNER ENDS****************


		for(int i=0;i<moves1.size();i++){
			String move1 = moves1.get(i);
			if(move1.equals("UP")){
				game.moveUP(1);
			}
			if(move1.equals("DOWN")){
				game.moveDOWN(1);
			}
			if(move1.equals("LEFT")){
				game.moveLEFT(1);
			}
			if(move1.equals("RIGHT")){
				game.moveRIGHT(1);
			}
		}
		for(int i=0;i<moves2.size();i++){
			String move2 = moves2.get(i);
			if(move2.equals("UP")){
				game.moveUP(2);
			}
			if(move2.equals("DOWN")){
				game.moveDOWN(2);
			}
			if(move2.equals("LEFT")){
				game.moveLEFT(2);
			}
			if(move2.equals("RIGHT")){
				game.moveRIGHT(2);
			}
		}
		game.printMap();
		
	}
	public void moveUP(int playerNum) {
		//first find person
		//if playerNum == 1, then search for P/O, if playerNum == 2, search for Q/T
		int x = 0;
		int y = 0;
		String whoAmI = "unknown"; //P or Q
		String IamOn = "unknown"; //E or T
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
		//set whoAmI, IamOn, onTargetName
		if(map.get(x).get(y) != null){
			if(map.get(x).get(y).equals("P")){
				whoAmI = "P";
				IamOn = "E";
				onTargetName = "O";
			}
			if(map.get(x).get(y).equals("Q")){
				whoAmI = "Q";
				IamOn = "E";
				onTargetName = "R";
			}
			if(map.get(x).get(y).equals("O")){
				whoAmI = "P";
				IamOn = "T";
				onTargetName = "O";
			}
			if(map.get(x).get(y).equals("R")){
				whoAmI = "Q";
				IamOn = "T";
				onTargetName = "R";
			}
		}
		//if the thing we are trying to move into is E or T, then we can just do it - update where you were
		//and where you are going
		if(map.get(x-1).get(y).equals("E")||map.get(x-1).get(y).equals("T")){
			if(map.get(x-1).get(y).equals("E")){
				map.get(x-1).set(y,whoAmI);
				map.get(x).set(y,IamOn);
			}
			if(map.get(x-1).get(y).equals("T")){
				map.get(x-1).set(y,onTargetName);
				map.get(x).set(y,IamOn);
			}
		//if the thing we are trying to move into is B or D (boxes), then we should analyse the next thing
		//beyond the box to see if we can - update things accordingly of course
		} else if(map.get(x-1).get(y).equals("B")||map.get(x-1).get(y).equals("D")){
			if(map.get(x-2).get(y).equals("E")||map.get(x-2).get(y).equals("T")){
				if(map.get(x-2).get(y).equals("E")){
					map.get(x-2).set(y,"B");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x-2).get(y).equals("T")){
					map.get(x-2).set(y,"D");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x-1).get(y).equals("B")){
					map.get(x-1).set(y,whoAmI);
				}
				if(map.get(x-1).get(y).equals("D")){
					map.get(x-1).set(y,onTargetName);
				}
			}
		} else {
			//do nothing
		}
	}
	public void moveDOWN(int playerNum) {
		//first find person P or O
		int x = 0;
		int y = 0;
		String whoAmI = "unknown";
		String IamOn = "unknown";
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
				IamOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				IamOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				IamOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				IamOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x+1).get(y).equals("E")||map.get(x+1).get(y).equals("T")){
			if(map.get(x+1).get(y).equals("E")){
				map.get(x+1).set(y,whoAmI);
				map.get(x).set(y,IamOn);
			}
			if(map.get(x+1).get(y).equals("T")){
				map.get(x+1).set(y,onTargetName);
				map.get(x).set(y,IamOn);
			}
		} else if(map.get(x+1).get(y).equals("B")||map.get(x+1).get(y).equals("D")){
			if(map.get(x+2).get(y).equals("E")||map.get(x+2).get(y).equals("T")){
				if(map.get(x+2).get(y).equals("E")){
					map.get(x+2).set(y,"B");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x+2).get(y).equals("T")){
					map.get(x+2).set(y,"D");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x+1).get(y).equals("B")){
					map.get(x+1).set(y,whoAmI);
				}
				if(map.get(x+1).get(y).equals("D")){
					map.get(x+1).set(y,onTargetName);
				}
			}
		} else {
			//do nothing
		}	
	}
	public void moveLEFT(int playerNum) {
		int x = 0;
		int y = 0;
		String whoAmI = "unknown";
		String IamOn = "unknown";
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
				IamOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				IamOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				IamOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				IamOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x).get(y-1).equals("E")||map.get(x).get(y-1).equals("T")){
			if(map.get(x).get(y-1).equals("E")){
				map.get(x).set(y-1,whoAmI);
				map.get(x).set(y,IamOn);
			}
			if(map.get(x).get(y-1).equals("T")){
				map.get(x).set(y-1,onTargetName);
				map.get(x).set(y,IamOn);
			}
		} else if(map.get(x).get(y-1).equals("B")||map.get(x).get(y-1).equals("D")){
			if(map.get(x).get(y-2).equals("E")||map.get(x).get(y-2).equals("T")){
				if(map.get(x).get(y-2).equals("E")){
					map.get(x).set(y-2,"B");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x).get(y-2).equals("T")){
					map.get(x).set(y-2,"D");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x).get(y-1).equals("B")){
					map.get(x).set(y-1,whoAmI);
				}
				if(map.get(x).get(y-1).equals("D")){
					map.get(x).set(y-1,onTargetName);
				}
			}
		} else {
			//do nothing
		}
	}
	public void moveRIGHT(int playerNum) {
		int x = 0;
		int y = 0;
		String whoAmI = "unknown";
		String IamOn = "unknown";
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
				IamOn = "E";
				onTargetName = "O";
			}
			if(whoAmI.equals("Q")){
				whoAmI = "Q";
				IamOn = "E";
				onTargetName = "R";
			}
			if(whoAmI.equals("O")){
				whoAmI = "P";
				IamOn = "T";
				onTargetName = "O";
			}
			if(whoAmI.equals("R")){
				whoAmI = "Q";
				IamOn = "T";
				onTargetName = "R";
			}
		}
		if(map.get(x).get(y+1).equals("E")||map.get(x).get(y+1).equals("T")){
			if(map.get(x).get(y+1).equals("E")){
				map.get(x).set(y+1,whoAmI);
				map.get(x).set(y,IamOn);
			}
			if(map.get(x).get(y+1).equals("T")){
				map.get(x).set(y+1,onTargetName);
				map.get(x).set(y,IamOn);
			}
		} else if(map.get(x).get(y+1).equals("B")||map.get(x).get(y+1).equals("D")){
			if(map.get(x).get(y+2).equals("E")||map.get(x).get(y+2).equals("T")){
				if(map.get(x).get(y+2).equals("E")){
					map.get(x).set(y+2,"B");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x).get(y+2).equals("T")){
					map.get(x).set(y+2,"D");
					map.get(x).set(y,IamOn);
				}
				if(map.get(x).get(y+1).equals("B")){
					map.get(x).set(y+1,whoAmI);
				}
				if(map.get(x).get(y+1).equals("D")){
					map.get(x).set(y+1,onTargetName);
				}
			}
		} else {
			//do nothing
		}
	}
	public ArrayList<ArrayList<String>> getMap() {
		return map;
	}
	public ArrayList<String> getMoves1() {
		return moves1;
	}
	public ArrayList<String> getMoves2() {
		return moves2;
	}
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
	public void printMoves1(){
		System.out.println("MoveStart2");
		for(int i=0;i<moves1.size();i++){
			System.out.println(moves1.get(i));
		}
		System.out.println("MoveEnd2");
	}
	public void printMoves2(){
		System.out.println("MoveStart2");
		for(int i=0;i<moves2.size();i++){
			System.out.println(moves2.get(i));
		}
		System.out.println("MoveEnd2");
	}
}
