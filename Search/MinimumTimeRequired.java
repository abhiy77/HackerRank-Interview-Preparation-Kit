import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        long low = 1;
        long minDays = Long.MAX_VALUE;
        long high = (long)1e18;
        while(low <= high){
            long mid = (low + high)/2;
            System.out.println(low + " " + high + " " + mid);
            if(isPossible(mid,machines,goal)){
                high = mid-1;
                minDays = Math.min(minDays,mid);
            }
            else low = mid+1;
        }
        return minDays;
    }

    public static boolean isPossible(long days,long machines[],long goal){
        long items = 0;
        for(int i=0;i<machines.length;i++){
            items += (days/machines[i]);
            if(items >= goal)return true;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
