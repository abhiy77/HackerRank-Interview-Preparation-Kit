import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long,Long> leftMap = new HashMap<>();
        HashMap<Long,Long> rightMap = new HashMap<>();

        for(int i=0;i<arr.size();i++){
            long val = arr.get(i);
            rightMap.put(val,rightMap.getOrDefault(val,(long)0) + 1);
        }

        long triplets = 0;

        for(int i=0;i<arr.size();i++){
            long val = arr.get(i);
            rightMap.put(val,rightMap.get(val)-1);
            if(rightMap.get(val) == 0) {
                rightMap.remove(val);
            }
            long leftNum = val/r;
            long rightNum = val * r;
            if(val % r == 0 && leftMap.containsKey(leftNum) && rightMap.containsKey(rightNum)){
                long product = leftMap.get(leftNum) * rightMap.get(rightNum);
                triplets += product;
            }
            leftMap.put(val,leftMap.getOrDefault(val,(long)0) + 1);
        }

        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
