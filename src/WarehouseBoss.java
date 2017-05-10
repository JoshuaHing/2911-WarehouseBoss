import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// COMP2911 project-2017S1


public class WarehouseBoss {
	
	public static void main(String[] args){
		//Game game = new Game();
		//ArrayList<ArrayList<String>> map = game.getMap();
		//ArrayList<String> moves = game.getMoves();
		ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		//****************SCANNER STARTS****************
		Scanner sc = null;

		try{
			sc = new Scanner(new FileReader("map/GameText.txt"));
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
							if(sc.hasNext("P")||sc.hasNext("B")
                  ||sc.hasNext("T")||sc.hasNext("W")
                  ||sc.hasNext("E")||sc.hasNext("O")
                  ||sc.hasNext("D")){
								map.get(i).add(sc.next());
							}
						}
						i++;
						sc.nextLine();
					}	
					sc.nextLine();
				}

				sc.nextLine();
			}
			//now that everything's initialised, print out the map.
			//Game game = new Game();
			
			//System.out.println("woohoo!");
			Game game = new Game();
			game.setMap(map);
			WarehouseBossInterface newInterface = new WarehouseBossInterface(game);



		} catch (FileNotFoundException e){}
		finally{
			if (sc != null) sc.close();
		}
	}
}

