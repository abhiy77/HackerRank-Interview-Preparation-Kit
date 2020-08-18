import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        int cnt[] = new int[26];
        for(int i=0;i<s.length();i++){
            cnt[s.charAt(i)-'a']++;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<26;i++){
            if(cnt[i] == 0)continue;
            set.add(cnt[i]);
            min = Math.min(min,cnt[i]);
            max = Math.max(max,cnt[i]);
        }
        System.out.println(Arrays.toString(cnt));

        if(set.size() == 1)return "YES";
        else if(set.size() > 2) return "NO";
        
        int cnt_min = 0, cnt_max = 0;
        for(int i=0;i<26;i++){
            if(cnt[i] == min)cnt_min++;
            if(cnt[i] == max)cnt_max++;
        }
        if(cnt_min == 1)return "YES";
        if(cnt_max > 1 || max - min > 1)return "NO";
        
        return "YES";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
