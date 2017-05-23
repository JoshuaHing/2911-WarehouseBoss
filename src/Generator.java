
import java.util.concurrent.ThreadLocalRandom;



/*
 * TO WHOEVER IMPLEMENTS THIS INTO THE PROGRAM:
 * STEPS:
 * 1. call the Generate() function.
 * 2. scroll down, and change print to print to the file, not the command line
 */
public class Generator {
	
	// variables:
	final static int N = 10; 		// SIZE OF MAP. CHANGE IF NEEDED
	final static int M = 10;
	public static char[][] map = new char[N][M];
	static int Y;
	static int X;
	static int testY;
	static int testX;
	static int delta;
	static String direction;
	static int boxes;
	static int walks; // number of directional walks for each box
	
	public static void Generate() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				map[i][j] = 'W';
		//print();
		
		// beigns by generating random numbers for starting position
		Y = ThreadLocalRandom.current().nextInt(2, N-2);
		X = ThreadLocalRandom.current().nextInt(2, M-2);
		map[Y][X] = 'P';
		
		boxes = 2;
		
		for(int k = 0; k < boxes; k++){
			walks = 1;
			do{
				delta = ThreadLocalRandom.current().nextInt(-N/2, N/2+1);
			} while(delta == 0) ; // make new delta if d=0
			direction = ThreadLocalRandom.current().nextInt(0, 2) == 1 ? "horizontal" : "vertical";
			for(int i = 0; i < walks; i++){
				System.out.println("direction = "+direction+" delta = "+delta);
				for(int j = 0; j != delta; j = delta < 0 ? j-1 : j+1){ //j-- if delta < 0, else j++
					if(direction.equals("horizontal")){
						testX = X + ((delta > 0) ? 1:-1); 
						testY = Y;
						System.out.println("memehorizontal"+((delta > 0) ? 1:-1));
					}
					else{testY = Y + ((delta > 0) ? 1:-1); testX = X; System.out.println("memevertical");}
					
					System.out.println("Y = "+Y+" X = "+X+", testY = "+testY+", testX = "+testX);
					if(testY > M-2 || testY < 1 || testX > N-2 || testX < 1) break;
					else{
						Y = testY;
						X = testX;
					}
					
					
					
					map[Y][X] = 'E'; // set square to empty as you walk
					if(Math.abs(j) == 0) map[Y][X] = 'B';
				}
				map[Y][X] = 'T';
			}
			//print(); debug
			System.out.println();
			walks = 4;
			for(int i = 0; i < walks; i++){
				//System.out.println("DOING LE I = "+i);
				do{
					delta = ThreadLocalRandom.current().nextInt(-N/2, N/2+1);
				} while(delta == 0); // make new delta if d=0
				direction = ThreadLocalRandom.current().nextInt(0, 2) == 1 ? "horizontal" : "vertical";
				System.out.println("direction = "+direction+" delta = "+delta);
				for(int j = 0; j != delta; j = delta < 0 ? j-1 : j+1){ //j-- if delta < 0, else j++
					if(direction.equals("horizontal")) {
						testX = X + ((delta > 0) ? 1:-1);
						testY = Y;
					}
					else{
						testY = Y+ ((delta > 0) ? 1:-1);	
						testX = X;
					}
					System.out.println("Y = "+Y+" X = "+X+", testY = "+testY+", testX = "+testX);
					if(testY > M-2 || testY < 1 || testX > N-2 || testX < 1 
					  || map[testY][testX] == 'T' || map[testY][testX] == 'B' || map[testY][testX] == 'P') break;
					else{
						Y = testY;
						X = testX;
					}
					

					map[Y][X] = 'E'; // set square to empty as you walk
				}
			}
			//print(); debug
		}
		print();

				
	}

	// YOU COULD EASILY MODIFY THIS TO PRINT TO A FILE INSTEAD OF THE CONSOLE!!!!!!!
	/*
	 * 
	 * 
	 * THIS IS A ROUGH DRAFT, MIGHT GET MAYBE 2 OUT OF 3
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void print(){
		for(int i = 0; i< N; i++){
			for(int j = 0; j < M; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
		System.out.println();
	}
}

//todo: use 2 arrays, 1 with boxes and tagets after they're completed, one before they're completed 
