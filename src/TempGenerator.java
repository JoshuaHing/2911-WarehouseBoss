import java.util.ArrayList;
import java.util.Iterator;



/*
 * TO WHOEVER IMPLEMENTS THIS INTO THE PROGRAM:
 * STEPS:
 * 1. call the Generate() function.
 * 2. scroll down, and change print to print to the file, not the command line
 */
public class TempGenerator{
	public final static int N = 12; 		// SIZE OF MAP. CHANGE IF NEEDED
	public final static int M = 12;
	public int boxesNeeded;
	public static char[][] map = new char[N][M];
	
	public boolean validStartFlag;
	public int randomStartX,randomStartY,randomWayX,randomWayY,randomTX,randomTY;
	public ArrayList<Pair> boxIndex = new ArrayList<Pair>();
	public ArrayList<Pair> targetIndex = new ArrayList<Pair>();
	public int[] movesX = new int[102];
	public int[] movesY = new int[102];
	public String mode;
		
	public int preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY;
	
	
	public TempGenerator(int boxNeeded, String mode){
		setAllWall();
		generate(boxNeeded);
		this.validStartFlag = false;
		this.mode = mode;
	}	
	public void setAllWall(){
		for (int n=0;n<N;n++){
			for(int m=0;m<M;m++){
				map[n][m] = 'W';
			}
		}
		
	}
	
	public void generate(int boxNeeded){
		//first initialize boxIndex & targetIndex arrayList of pair
		this.boxesNeeded = boxNeeded;
		for (int g = 0;g<boxNeeded;g++){
			boxIndex.add(new Pair(0,0));
			targetIndex.add(new Pair(0,0));
		}
		
		int boxSum =0;
		//set player & box binded at start 
		randomStartX = (int )(Math.random() * (N-7))+4;
		randomStartY = (int )(Math.random() * (M-7))+4;
		//5~8
		//set box ,maybe multiple boxes
		while(boxSum<boxNeeded){
			//indicate place(1,0) means left (0,-1)means down, this does mean the index of box 
			randomWayX = (int )(Math.random()*3) -1;
			randomWayY = (int )(Math.random()*3) -1;
			this.validStartFlag = checkStartPoint(randomStartX,randomStartY,randomWayX,randomWayY);
			//should always valid 
			if(validStartFlag){
				//set player && box location for real
				if(map[randomStartX+randomWayX][randomStartY+randomWayY]=='W'){
					//if it's not wall then it's duplicated box place, then skip to next loop
					map[randomStartX][randomStartY] = 'P';
					map[randomStartX+randomWayX][randomStartY+randomWayY] = 'B';
					
					int tmp = 0;
					Iterator<Pair> pair = boxIndex.iterator();
				
					while(tmp < boxNeeded && pair.hasNext()){
						Pair newpair = pair.next();
						if(tmp == boxSum ){
							newpair.indexX = randomStartX+randomWayX;
							newpair.indexY = randomStartY+randomWayY;
							break;
						}
						
						tmp++;
					}
					boxSum ++;
				}
				
			}
		}
		
		//now set target place
		int targetSum = 0;
		while(targetSum<boxNeeded){
			randomTX = (int)(Math.random() * (N-2))+1;
			randomTY = (int)(Math.random() * (M-2))+1;
			//1~10
			if(map[randomTX][randomTY]!='P'&&map[randomTX][randomTY]!='B'){
				map[randomTX][randomTY] = 'T';
				int tmp = 0;
				Iterator<Pair> pair = targetIndex.iterator();
				while(tmp<boxNeeded && pair.hasNext()){
					Pair newpair = pair.next();
					if(tmp == targetSum){
						newpair.indexX = randomTX;
						newpair.indexY = randomTY;
						break;
					}
					tmp++;
				}
				
				targetSum++;
			}
		}
		print();
		
		//now simulate path, minimize wall destroying. 

		int possibleMoveDis[] = new int[4];//used to record possible move distance from box to target
		//[0] contains up [1]contains down [2] contains left [3] contains right
		int tried = 0;
		while(!checkFinished(boxNeeded) && tried <100){
			//when game not finished		
			tried++;
			Iterator<Pair> pair = boxIndex.iterator();
			Iterator<Pair> pair1 = targetIndex.iterator();
			
			for(int t = 0;t<boxIndex.size();t++){
				assert(pair.hasNext()&&pair1.hasNext());
				Pair newpair = pair.next();
				Pair newpair1 = pair1.next();
				currBoxX = newpair.indexX;
				currBoxY = newpair.indexY;
				//initially same as curr
				preBoxX = currBoxX;
				preBoxY = currBoxY;
				
				int tempResult = 0;
				
				while((currBoxX != newpair1.indexX)|| (currBoxY != newpair1.indexY)){
					
					//change box location with least distance
					possibleMoveDis[0] = calDistance(currBoxX-1,currBoxY,newpair1.indexX,newpair1.indexY);
					possibleMoveDis[1] = calDistance(currBoxX+1,currBoxY,newpair1.indexX,newpair1.indexY);
					possibleMoveDis[2] = calDistance(currBoxX,currBoxY-1,newpair1.indexX,newpair1.indexY);
					possibleMoveDis[3] = calDistance(currBoxX,currBoxY+1,newpair1.indexX,newpair1.indexY);
					tempResult = findMinimum(possibleMoveDis[0],possibleMoveDis[1],possibleMoveDis[2],possibleMoveDis[3]);
					//need to note that box cant go to the 4 corners!!!!!!!
					
					if(tempResult!= 0 && tempResult <10){
						if(tempResult == 1){

							nextBoxX = currBoxX-1;
							nextBoxY = currBoxY;
							//need to note that box cant go to the 4 corners.
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;
							

						}else if(tempResult == 2){
							nextBoxX = currBoxX+1;
							nextBoxY = currBoxY;
							//need to note that box cant go to the 4 corners.
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;
						}else if(tempResult == 3){
							nextBoxX = currBoxX;
							nextBoxY = currBoxY-1;
							//need to note that box cant go to the 4 corners.
							
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							//
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;

						}else if(tempResult == 4){
							nextBoxX = currBoxX;
							nextBoxY = currBoxY+1;
							//need to note that box cant go to the 4 corners.
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							//
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;

						}
					
						
					}
					else{
						//maybe meet box in a row/col in this case, if so, try skip current loop and loop again
						if(findRandom(tempResult)==1){
							nextBoxX = currBoxX-1;
							nextBoxY = currBoxY;
							//need to note that box cant go to the 4 corners.

							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							//
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;
							
							

						}else if(findRandom(tempResult) == 2){
							nextBoxX = currBoxX+1;
							nextBoxY = currBoxY;
							//need to note that box cant go to the 4 corners.
						
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							//
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);	
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;
						}else if(findRandom(tempResult) == 3){
							nextBoxX = currBoxX;
							nextBoxY = currBoxY-1;
							//need to note that box cant go to the 4 corners.
							
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							//
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);

							}
							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;

						}else if(findRandom(tempResult) == 4){
							nextBoxX = currBoxX;
							nextBoxY = currBoxY+1;
							//need to note that box cant go to the 4 corners.							
							//if cornering
							if(Math.abs(preBoxX-nextBoxX) == 1 || Math.abs(preBoxY-nextBoxY)==1){
								removeCorneringWall(preBoxX,preBoxY,currBoxX,currBoxY,nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
							}
							else{
								removeWall(nextBoxX,nextBoxY,newpair1.indexX,newpair1.indexY);
								removeWall(currBoxX,currBoxY,newpair1.indexX,newpair1.indexY);
							}

							preBoxX = currBoxX;
							preBoxY = currBoxY;
							currBoxX = nextBoxX;
							currBoxY = nextBoxY;

						}
						
						
					}
					

					tried++;
				}
				
				
			}
			
			
		}
		
				
	}
	public void removeCorneringWall(int pBX,int pBY,int cBX,int cBY,int nBX, int nBY,int tarX,int tarY ){
		removeWall(nBX,nBY,tarX,tarY);
		removeWall(cBX,cBY,tarX,tarY);
		//removeWall(pBX,pBY,tarX,tarY);
		if(preBoxX-nextBoxX == 1 && preBoxY-nextBoxY == 1){
			//preBox is left up to nextBox
			if(currBoxX-nextBoxX==1){
				removeWall(pBX+1,pBY,tarX,tarY);
				removeWall(cBX+1,cBY,tarX,tarY);
			}else if(currBoxY-nextBoxY==1){
				removeWall(pBX,pBY+1,tarX,tarY);
				removeWall(cBX,cBY+1,tarX,tarY);
			}
		}else if(preBoxX-nextBoxX == -1 && preBoxY-nextBoxY == 1){
			if(currBoxX-nextBoxX== -1){
				removeWall(pBX-1,pBY,tarX,tarY);
				removeWall(cBX-1,cBY,tarX,tarY);
			}else if (currBoxY-nextBoxY == 1){
				removeWall(pBX,pBY+1,tarX,tarY);
				removeWall(cBX,cBY+1,tarX,tarY);
			}
		}else if(preBoxX-nextBoxX == 1 && preBoxY-nextBoxY == -1){
			if(currBoxX-nextBoxX== 1){
				removeWall(pBX+1,pBY,tarX,tarY);
				removeWall(cBX+1,cBY,tarX,tarY);
			}else if (currBoxY-nextBoxY == -1){
				removeWall(pBX,pBY-1,tarX,tarY);
				removeWall(cBX,cBY-1,tarX,tarY);
			}		
		}else if(preBoxX-nextBoxX == -1 && preBoxY-nextBoxY == -1){
			if(currBoxX-nextBoxX== -1){
				removeWall(pBX-1,pBY,tarX,tarY);
				removeWall(cBX-1,cBY,tarX,tarY);
			}else if (currBoxY-nextBoxY == -1){
				removeWall(pBX,pBY-1,tarX,tarY);
				removeWall(cBX,cBY-1,tarX,tarY);
			}
		}	
	}
	
	public void removeWall(int nBX,int nBY,int tarX,int tarY){

		if (map[nBX][nBY] == 'B'){
			//gg fked in this case should never happen but if happen, expand around to be empty
			specialCase(nBX,nBY);
		}else if (map[nBX][nBY] == 'T' && nBX !=tarX && nBY != tarY){
			//found a TARGET but not the one looking for
			map[nBY][nBY] = 'E';
		} else if(map[nBX][nBY] == 'T' && nBX ==tarX && nBY == tarY){
			map[nBX][nBY] = 'O';
			//specialCase(nBX,nBY);
		}else if(map[nBX][nBY]=='O'){
			//do nothing
		}
		else{
			map[nBX][nBY]='E';
		}
	}
	
	public void specialCase(int x,int y){

		map[x-1][y-1] = (map[x-1][y-1]=='O') ? 'O':'E';
		map[x-1][y] = (map[x-1][y]=='O') ? 'O':'E';
		map[x-1][y+1] = (map[x-1][y+1]=='O') ? 'O':'E';
		map[x][y-1] = (map[x][y-1]=='O') ? 'O':'E';
		map[x][y] = (map[x][y]=='B') ? 'E':'E';
		map[x][y+1] = (map[x][y+1]=='O') ? 'O':'E';
		map[x+1][y-1] = (map[x+1][y-1]=='O') ? 'O':'E';
		map[x+1][y] = (map[x+1][y]=='O') ? 'O':'E';
		map[x+1][y+1] = (map[x+1][y+1]=='O') ? 'O':'E';
	}

	//find random move from input minimum moves
	public int findRandom(int number){
		int result=0,tens=0,single= 0;
		if(number == 13 || number ==14){
			tens = 1;
			single = number - tens*10;
			int ranS = (int)(Math.random() *2);
			if(ranS == 0){result = tens;}
			else{result = single;}
		}
		else if(number == 23 || number ==24){
			tens = 2;
			single = number - tens*10;
			int ranS = (int)(Math.random() *2);
			if(ranS == 0){result = tens;}
			else{result = single;}
		}
		else if(number == 31 || number ==32){
			tens = 3;
			single = number - tens*10;
			int ranS = (int)(Math.random() *2);
			if(ranS == 0){result = tens;}
			else{result = single;}
		}
		else if(number == 41 || number ==42){
			tens = 4;
			single = number - tens*10;
			int ranS = (int)(Math.random() *2);
			if(ranS == 0){result = tens;}
			else{result = single;}
		}
		return result;
	}
	
	//find minimum moves
	public int findMinimum(int a,int b,int c,int d){
		int result =0;
		if(a<=b&& a<=c && a<=d){
			//up & down not possible
			if (a==c){
				result = 13;//means up&left
			}
			else if(a == d){
				result = 14;//means up&right
			}
			else{
				result = 1;//means up
			}
		}
		else if (b<=a && b<=c && b<=d){
			if (b==c){
				result = 23;//means down&left
			}
			else if(b == d){
				result = 24;//means down&right
			}
			else{
				result = 2;//means down
			}
		}
		else if (c<=a && c<=b && c<=d){
			if(c== a){
				result = 31;//means left & up
			}
			else if (c==b){
				result = 32;//means left&down
			}
			else{
				result = 3;//means left
			}
		}
		else if (d<=a && d<=b && d<=c){
			if(d== a){
				result = 41;//means right & up
			}
			else if (d==b){
				result = 42;//means right&down
			}
			else{
				result = 4;
			}
		}
		return result;
	}
	
	static public void dodgySet(int x,int y){
		if(map[x-1][y-1] == 'W'){
			map[x-1][y-1] = 'E';
		}
		if(map[x-1][y] == 'W'){
			map[x-1][y] = 'E';
		}
		if(map[x-1][y+1] == 'W'){
			map[x-1][y+1] = 'E';
		}
		if(map[x][y-1] == 'W'){
			map[x][y-1] = 'E';
		}
		if(map[x][y+1] == 'W'){
			map[x][y+1] = 'E';
		}
		if(map[x+1][y-1] == 'W'){
			map[x+1][y-1] = 'E';
		}
		if(map[x+1][y] == 'W'){
			map[x+1][y] = 'E';
		}
		if(map[x+1][y+1] == 'W'){
			map[x+1][y+1] = 'E';
		}
	}
	
	//create a inner class to represent box and targets
	private class Pair{
		public int indexX;
		public int indexY;
		
		public Pair(int xValue,int yValue){
			indexX = xValue;
			indexY = yValue;
		}
	}

	//calculating distance function |x| + |y| instead of sqrt((y-y0)^2 + (x-x0)^2)
	//needs to consider situation that two method of distance equals eg: up and left would cause
	//same distance to target
	public int calDistance(int boxX,int boxY,int tarX,int tarY){
		return Math.abs(boxX-tarX) + Math.abs(boxY-tarY);
	}
	
	//check if all box are at targets
	public boolean checkFinished(int boxNeeded){
		int countOnTarget = 0;
		for(int i = 0;i<N;i++){
			for(int j = 0;j<M;j++){
				if(map[i][j] == 'O'){countOnTarget++;}
			}
		}
		return (countOnTarget == boxNeeded);
	}
	
	//x for start x index, y for start y index, rw for randomway eg:0 for up 1 for down
	public boolean checkStartPoint(int x ,int y,int rx,int ry){
		if(!(rx ==0&&ry==0)){
		//if touches edge, then start point is invalid
		if(rx + x== 0 || ry + y == 0 || rx + x ==N-1 || ry+ y == M-1){return false;}
		else{return true;}
		}
		return false;
		}

	public static void print(){
		for(int i = 0; i< N; i++){
			for(int j = 0; j < M; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public int getBoxesNeeded() {
		return this.boxesNeeded;
	}
	
	public static Game getGame(int num, String mode) {
		Game game = new Game();
		for(int i = 0; i < 3; i++) {
			TempGenerator a = new TempGenerator(num,mode) ;
			int px = 0,py = 0;
			for(int j = 0;j < 20;j++){
				a = new TempGenerator(num, mode);
				if(a.checkFinished(num )){
					break;
				}
			}
			px = a.randomStartX;
			py = a.randomStartY;
	
			//set back player & box & target
	
			map[px][py] = 'P';
			Iterator<Pair> boxes = a.boxIndex.iterator();
			Iterator<Pair> targets = a.targetIndex.iterator();
			while(boxes.hasNext()){
				Pair tmp = boxes.next();
				map[tmp.indexX][tmp.indexY] = 'B';
				//set empty around B in order to get rid of edge cases
				dodgySet(tmp.indexX,tmp.indexY);
			}
			
			while(targets.hasNext()){
				Pair tmpT = targets.next();
				map[tmpT.indexX][tmpT.indexY] = 'T';
			}	
			
			//edit map so that outer bound is wall
			
			for(int j = 0;j<N;j++){
				for(int k = 0;k<M;k++){
					if(j ==0 || k == 0 ||j==N-1||k==M-1){
						map[j][k] = 'W';
					}
				}
			}
			if(mode.equals("Multi Player")) {
				boolean flagQ = false;
				while(!flagQ){
					int tempX = 0,tempY = 0;
					tempX = (int)(Math.random() * (N-1))+1;
					tempY = (int)(Math.random() * (M-1))+1;
					if(map[tempX][tempY] =='E'){
						map[tempX][tempY] = 'Q';
						flagQ = true;
					}
				}
			}
			ArrayList<ArrayList<String>>stringMap = new ArrayList<ArrayList<String>>();
			for(int k = 0; k < 12; k++) {
				ArrayList<String> newList = new ArrayList<String>();
				stringMap.add(newList);
				for(int j = 0; j < 12; j++) {
					char c = map[k][j];
					String s = Character.toString(c);
					stringMap.get(k).add(s);	
				}
			}
			Map newMap = new Map(stringMap, a.getBoxesNeeded());
			game.addMap(newMap);
			//print();
		}
		return game;

	}

}



