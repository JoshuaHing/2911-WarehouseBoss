import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// COMP2911 project-2017S1


public class WarehouseBoss {
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	
	/**
	 * @pre All maps are of a fixed length (This will be changed later)
	 */
	public static void main(String[] args){
		ArrayList<ArrayList<String>> map = null;
		//****************SCANNER STARTS****************
		Scanner sc = null;
		int numGoals = 0;
		Game game = new Game();
		try{	//We need to keep going and take in all the maps
			sc = new Scanner(new FileReader(args[0]));
			while(sc.hasNextLine()){
				while(sc.hasNext("#")){					//Ignore comments
					sc.nextLine();
				}
				if(sc.hasNextLine()) {					//If there's another line to scan, that means that there's more maps to scan
					map = new ArrayList<ArrayList<String>>();
					for (int i = 0; i < NUM_COLS; i++) {	
						ArrayList<String> newList = new ArrayList<String>();
						map.add(newList);
						for (int j = 0; j < NUM_ROWS; j++) {
							//Only scan for these letters
							if(sc.hasNext("P")||sc.hasNext("B")||sc.hasNext("T")||sc.hasNext("W")||sc.hasNext("E")||sc.hasNext("O")||sc.hasNext("D")){
								if(sc.hasNext("T")) {
									numGoals++;
								}
								map.get(i).add(sc.next());
							}
						}
					}
					System.out.println("numGoals = " + numGoals);
					Map newMap = new Map(map, numGoals);	//Make a new map and add it to the game
					game.addMap(newMap);
					numGoals = 0;							//Reset the numGoals for future maps
					if(sc.hasNextLine()) {
						sc.nextLine();
					}
				}
			}
			System.out.println("map size = " + game.numMaps());			//print the number of maps loaded into the game
			game.setInitialMap(game.getLevel(0).getMap());				//Initial map is set to the first one on the map list
			game.setMap(game.getLevel(0).getMap());						//Current map is set to the first one on the map list
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);	//Create a new interface to start the game
		} catch (FileNotFoundException e){}
		finally{
			if (sc != null) sc.close();
		}
	}
}

