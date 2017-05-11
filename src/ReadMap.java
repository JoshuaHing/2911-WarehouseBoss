import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
  private int level;
  private int map[][];
  private int mx,my;

  public ReadMap(int level){
    this.level = level;

    String sfileName = "map/" + level + ".txt";
    String content = "";
    try {
      FileReader fr = new FileReader(sfileName);
      BufferedReader br = new BufferedReader(fr);

      String temp = "";

      while ((temp = br.readLine()) != null){
        content += temp;
      }
      byte b[] = content.getBytes();
      for (byte c :b
      ) {
       System.out.println(c+ "\t");
      }
      map = new int[20][20];
      int c =0;
      for (int i = 0; i <20 ; i++) {
        for (int j = 0; j <20 ; j++) {
          map[i][j] = b[c]-32;
          c++;
        }
      }

      //System.out.println(content);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
