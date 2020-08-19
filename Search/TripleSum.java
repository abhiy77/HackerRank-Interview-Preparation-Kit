import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        long triplets = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        Set<Integer> set = new TreeSet<Integer>();
        int i = 0;
        for(i=0;i<a.length;i++){
            set.add(a[i]);
        }
        a = new int[set.size()];
        i = 0;
        for(int val : set){
            a[i++] = val; 
        }
        set.clear();
        i = 0;
        for(i=0;i<c.length;i++){
            set.add(c[i]);
        }
        i=0;
        c = new int[set.size()];
        for(int val : set){
            c[i++] = val; 
        }
        i=0;
        set.clear();
        for(i=0;i<b.length;i++){
            set.add(b[i]);
        }
        i=0;

        for(int val : set){
            long idx1 = Arrays.binarySearch(a,val);
            if(idx1 < 0)idx1 = -(idx1+1);
            else idx1++;
            long idx3 = Arrays.binarySearch(c,val);
            if(idx3 < 0)idx3 = -(idx3+1);
            else idx3++;
            triplets += (idx1 * idx3);

        }
        return triplets;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
