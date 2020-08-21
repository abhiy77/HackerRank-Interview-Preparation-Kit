import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Coor{
    int x,y,cost;
    Coor(int x,int y,int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}


public class Solution {

    public static void getDirections(Coor c,char[][] grid,Queue<Coor> queue){
        int x = c.x;
        int y = c.y;
        int k = 0;
        for(k=x-1;k>=0;k--){
            if(grid[k][y] == 'X')break;
            else queue.add(new Coor(k,y,c.cost+1));
        }
        for(k=y+1;k<grid[0].length;k++){
            if(grid[x][k] == 'X')break;
            else queue.add(new Coor(x,k,c.cost+1));
        }
        

        for(k=x+1;k<grid.length;k++){
            if(grid[k][y] == 'X')break;
            else queue.add(new Coor(k,y,c.cost+1));
        }
        
        for(k=y-1;k>=0;k--){
            if(grid[x][k] == 'X')break;
            queue.add(new Coor(x,k,c.cost+1));
        }
        


        if(x-1 >= 0 && grid[x-1][y] != 'X'){
            
        }
        if(x+1 < grid.length && grid[x+1][y] != 'X'){
            queue.add(new Coor(x+1,y,c.cost+1));
        }
        if(y-1 >=0 && grid[x][y-1] != 'X'){
            queue.add(new Coor(x,y-1,c.cost+1));
        }
        if(y+1 < grid[0].length && grid[x][y+1] != 'X'){
            queue.add(new Coor(x,y+1,c.cost+1));
        }
        

        

    }

    static char[][] makeGrid(String str[]){
        char arr[][] = new char[str.length][str[0].length()];
        for(int i=0;i<str.length;i++){
            String s = str[i];
            for(int j=0;j<s.length();j++){
                arr[i][j] = s.charAt(j);
            }
        }
        return arr;
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] str, int startX, int startY, int goalX, int goalY)     {
        char grid[][] = makeGrid(str);
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Coor> queue = new LinkedList<>();
        queue.add(new Coor(startX,startY,0));
        boolean visited[][] = new boolean[n][m];

        int minDist = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Coor c = queue.poll();
            if(c.x == goalX && c.y == goalY){
                minDist = Math.min(minDist,c.cost);
                continue;
            }
            if(visited[c.x][c.y])continue;
            visited[c.x][c.y] = true;
            getDirections(c,grid,queue);
        }

        return minDist;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
