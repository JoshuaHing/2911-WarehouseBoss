import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// COMP2911 project-2017S1

public class WarehouseBoss {

	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;

	public static void main(String[] args) {
		ArrayList<ArrayList<String>> map = null;
		// ****************SCANNER STARTS****************
		Scanner sc = null;
		int numGoals = 0;
		Game game = new Game();
		try { // We need to keep going and take in all the maps
			sc = new Scanner(new FileReader("map/SinglePlayer.txt"));
			while (sc.hasNextLine()) {
				while (sc.hasNext("#")) {
					sc.nextLine();
				}
				if (sc.hasNextLine()) {
					map = new ArrayList<ArrayList<String>>();
					for (int i = 0; i < NUM_COLS; i++) {
						ArrayList<String> newList = new ArrayList<String>();
						map.add(newList);
						for (int j = 0; j < NUM_ROWS; j++) {
							if (sc.hasNext("P") || sc.hasNext("B") || sc.hasNext("T") || sc.hasNext("W")
									|| sc.hasNext("E") || sc.hasNext("O") || sc.hasNext("D") || sc.hasNext("Q")) {
								if (sc.hasNext("T")) {
									numGoals++;
								}
								map.get(i).add(sc.next());
							}
						}
					}
					// System.out.println("numGoals = " + numGoals);
					Map newMap = new Map(map, numGoals);
					game.addMap(newMap);
					numGoals = 0;
					if (sc.hasNextLine()) {
						sc.nextLine();
					}
				}
			}
			// System.out.println("map size = " + game.numMaps());
			//game.setInitialMap(game.getLevel(0).getMap());
			game.setMap(game.getLevel(0));
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);
		} catch (FileNotFoundException e) {
		} finally {
			if (sc != null)
				sc.close();
		}
	}
}
