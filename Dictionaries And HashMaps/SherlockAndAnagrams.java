import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashMap<List<Integer>,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                List<Integer> list = new ArrayList<>(26);
                for(int k=0;k<26;k++)list.add(0);

                for(int k=i;k<=j;k++){
                    int val = list.get(s.charAt(k) - 'a');
                    list.set(s.charAt(k)-'a',val + 1);
                }
                map.put(list,map.getOrDefault(list,0) + 1);
            }
        }

        if(s.equals("abba")){
        for(Map.Entry<List<Integer>,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        }
        int count = 0;

        for(Map.Entry<List<Integer>,Integer> entry : map.entrySet()){
            int val = entry.getValue();
            count += (val * (val-1)/2);
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
