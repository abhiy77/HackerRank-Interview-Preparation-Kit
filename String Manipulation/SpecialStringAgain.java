import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long ans = 0;
        int i = 0,j=0,count =0;
        int temp[] = new int[s.length()];

        while(i < n){
            j=i+1;count = 1;
            while(j < n && s.charAt(j) == s.charAt(j-1)){
                j++; count++;
            }

            temp[i] = count;
            ans += (count * (count+1)) >> 1;
            i = j;
        }

        for(j = 1;j<n-1;j++){
            if(s.charAt(j) == s.charAt(j-1)){
                temp[j] = temp[j-1];
            }
            if(s.charAt(j) != s.charAt(j-1) && s.charAt(j-1) == s.charAt(j+1)){
                ans += Math.min(temp[j-1],temp[j+1]);
            }
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
