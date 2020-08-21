import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the riddle function below.
    static long[] riddle(long[] a) {
    
    int n = a.length;
    Stack<Integer> s = new Stack<>();
    long left[] = new long[n];
    long right[] = new long[n];
    long ans[] = new long[n+1];
    long len = 0;

    for(int i=0;i<n;++i) {
        left[i]=-1;right[i]=n;
    }

    for(int i=0;i<n;++i) {
        while(!s.empty() && a[s.peek()] >= a[i]) {
            s.pop();
        }
        if(!s.isEmpty()) {
            left[i]=s.peek();
        }
        s.push(i);
    }

    while(!s.isEmpty()) {
        s.pop();
    }

    for(int i=n-1;i>=0;--i) {
        while(!s.isEmpty() && a[s.peek()] >= a[i]) {
            s.pop();
        }
        if(!s.isEmpty()) {
            right[i]=s.peek();
        }
        s.push(i);
    }

    for(int i=0;i<n;++i) {
        len = right[i]-left[i]-1;
        ans[(int)len]= Math.max(ans[(int)len], a[i]);
    }

    for(int i=n-1;i>=1;--i) {
        ans[i]= Math.max(ans[i], ans[i+1]);
    }
    long res[] = new long[n];
    for(int i=1;i<=n;i++){
        res[i-1] = ans[i];
    }
    
    return res;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
