import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        HashMap<String,Integer> noteMap = new HashMap<String, Integer>();
        HashMap<String,Integer> magazineMap = new HashMap<String, Integer>();

        Integer occurrences;

        for(int i=0;i<magazine.length;i++){
            occurrences = magazineMap.get(magazine[i]);

            if(occurrences == null){
                magazineMap.put(magazine[i],1);
            }
            else{
                magazineMap.put(magazine[i],occurrences + 1);
            }
        }

        for(int i=0;i<note.length;i++){
            occurrences = noteMap.get(note[i]);

            if(occurrences == null){
                noteMap.put(note[i],1);
            }
            else{
                noteMap.put(note[i],occurrences + 1);
            }
        }

        String ransom = "Yes";

        for(String str : noteMap.keySet()){
            if(!magazineMap.containsKey(str) || noteMap.get(str) > magazineMap.get(str)){
                ransom = "No";
                break;
            }
        }

        System.out.println(ransom);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
