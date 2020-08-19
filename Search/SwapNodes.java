import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static class Node {
        Node left, right;
        int data;
        Node(int x) {
            this.data = x;
            this.left = this.right = null;
        }
    }

    static Node tree[];
    static ArrayList<Integer> depth[];

    private static void inorder(Node t,List<Integer> arr) {
        if(t == null) return;
        inorder(t.left,arr);
        arr.add(t.data);
        inorder(t.right,arr);
    }

    private static void dfs(Node n,int d){
        if(n == null) return;
        depth[d].add(n.data);
        dfs(n.left,d+1);
        dfs(n.right,d+1);
    }
    
    static int[][] swapNodes(int[][] indexes, int[] queries) {
       int n = indexes.length; 
       tree = new Node[n + 1];
       depth = new ArrayList[n + 1];

       for(int i=1;i<=n;i++){
           tree[i] = new Node(i);
           depth[i] = new ArrayList<Integer>();
       }

       for(int i=1;i<=n;i++){
           int l = indexes[i-1][0];
           int r = indexes[i-1][1];
           if(l != -1)tree[i].left = tree[l];
           if(r != -1)tree[i].right = tree[r];
       }

       dfs(tree[1],1);

       int result[][] = new int[queries.length][];
       int index = 0;

       for(int i=0;i<queries.length;i++){
           int k = queries[i];
           int h = k;
           while(h <= n){
               for(int d : depth[h]){
                   Node temp = tree[d].left;
                   tree[d].left = tree[d].right;
                   tree[d].right = temp;
               }
               h+=k;
           }
           List<Integer> arr = new ArrayList<>();
           inorder(tree[1],arr);
           int temp[] = new int[arr.size()];
           for(int j=0;j<arr.size();j++){
               temp[j] = arr.get(j);
           }
           result[index++] = temp;
       }
       return result;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
