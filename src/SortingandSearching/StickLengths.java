package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StickLengths {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){

            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long median  = arr[n/2];
        long adjustment = 0;
        for(int i=0;i<n;i++){
            adjustment = adjustment + Math.abs(median - arr[i]);
        }
        System.out.println(adjustment);
    }
}
