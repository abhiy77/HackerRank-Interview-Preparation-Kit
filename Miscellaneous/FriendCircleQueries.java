import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class DisjointSet{

        HashMap<Integer,Integer> rank = new HashMap<>();
        HashMap<Integer,Integer> parent = new HashMap<>();
        public int find(int i){
            if(parent.get(i) == i){
                return i;
            }
            else{
                return find(parent.get(i));
            }
        }

        public void init(int i){
            if(!parent.containsKey(i)){
                parent.put(i,i);
                rank.put(i,1);
            }
        }

        public void union(int i,int j,TreeSet<Integer> set){
            init(i);
            init(j);

            int x = find(i);
            int y = find(j);

            if(x == y)return;

            if(rank.get(x) < rank.get(y)){
                parent.put(x,y);
                rank.put(y,rank.get(x) + rank.get(y));
                set.add(rank.get(y));
            }
            else{
                parent.put(y,x);
                rank.put(x,rank.get(x)+rank.get(y));
                set.add(rank.get(x));
            }
        }
    }

public class Solution {

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        int result[] = new int[queries.length];
        int idx = 0;
        TreeSet<Integer> set = new TreeSet<>();

        DisjointSet disjointSet = new DisjointSet();
        for(int i=0;i<queries.length;i++){
            disjointSet.union(queries[i][0],queries[i][1],set);
            result[idx++] = set.last();
        }
        return result;
    }

    // private static int findMaxCircle(DisjointSet disjointSet){
    //     HashMap<Integer,Integer> freqs = new HashMap<>();
    //     for(Map.Entry<Integer,Integer> entry : disjointSet.parent.entrySet()){
    //         int val = disjointSet.find(entry.getKey());
    //         freqs.put(val,freqs.getOrDefault(val,0) + 1);
    //     }
    //     int max = 0;
    //     for(Map.Entry<Integer,Integer> entry : freqs.entrySet()){
    //         max = Math.max(entry.getValue(),max);
    //     }
    //     System.out.println("Freqs"+freqs);
    //     return max;
    // }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
