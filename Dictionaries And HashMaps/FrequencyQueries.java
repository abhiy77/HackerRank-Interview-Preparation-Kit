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

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        HashMap<Integer,Integer> map = new HashMap<>();
        HashMap<Integer,Integer> freqs = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for(List<Integer> temp : queries){

            int num = temp.get(1);

            if(temp.get(0) == 1){
                Integer freq = map.get(num);
                if(freq != null){
                    freqs.put(freq,freqs.get(freq)-1);
                }
                if(freq == null)freq = 0;
                map.put(num,map.getOrDefault(num,0) + 1);
                freqs.put(freq+1,freqs.getOrDefault(freq+1,0)+1);
            }

            else if(temp.get(0) == 2){
                Integer freq = map.get(num);

                if(freq != null && freq > 0){
                    freqs.put(freq,freqs.get(freq)-1);
                    map.put(num,freq-1);
                    freqs.put(freq-1,freqs.getOrDefault(freq-1,0) +1);
                }
            }

            else if(temp.get(0) == 3){
                if(freqs.containsKey(num) && freqs.get(num) > 0){
                    result.add(1);
                }
                else{
                    result.add(0);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
