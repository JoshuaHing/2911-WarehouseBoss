import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// COMP2911 project-2017S1


public class WarehouseBoss {
	
	public static void main(String[] args){
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		//****************SCANNER STARTS****************
		Scanner sc = null;
		int numGoals = 0;
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
							if(sc.hasNext("P")||sc.hasNext("B")||sc.hasNext("T")||sc.hasNext("W")||sc.hasNext("E")||sc.hasNext("O")||sc.hasNext("D")){
								if(sc.hasNext("T")) {
									numGoals++;
								}
								map.get(i).add(sc.next());
							}
						}
						i++;
						sc.nextLine();
					}	
					sc.nextLine();
				}
			}
			Game game = new Game();
			game.setInitialMap(map);
			game.setMap(map);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game, numGoals);
		} catch (FileNotFoundException e){}
		finally{
			if (sc != null) sc.close();
		}
	}
}

