import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = cost.length;
        for(int i=0;i<n;i++){
            map.put(cost[i],map.getOrDefault(cost[i],0)+1);
        }
        int flag = 0;
        int value1 =0,value2 = 0;
        for(int i=0;i<n;i++){
            value1 = cost[i];
            value2 = money - cost[i];
            if(value1 == value2){
                if(map.containsKey(value1) && map.get(value1) >=2){
                    flag = 1;
                    break;
                }
            }
            if(value1 != value2){
                if(map.containsKey(value1) && map.containsKey(value2)){
                    flag = 1;
                    break;
                }
            }
        }

        if(flag == 1){
            for(int i=0;i<n;i++){
                if(value1 == cost[i] || value2 == cost[i]){
                    System.out.print(i+1 + " ");
                }
            }
            System.out.println();
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
