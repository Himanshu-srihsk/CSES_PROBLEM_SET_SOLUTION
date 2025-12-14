package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SlidingWindowMex {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int idx = 0;
        int[] arr = new int[n];
        while (idx<n){
            if (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break; // Should not happen with valid input
                st = new StringTokenizer(line);
            }
            arr[idx++] = Integer.parseInt(st.nextToken());
        }
        //Map<Integer,Integer> map = new HashMap<>();
        int[] count = new int[n+1];
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0;i<k;i++){
            set.add(i);
        }
        set.add(k);
        //System.out.println();
        for(int i=0;i<n;i++){

            if(i>=k){
                int elementLeaving = arr[i-k];
                if(elementLeaving<k){
                    count[elementLeaving]--;
                    if(count[elementLeaving]==0){
                        set.add(elementLeaving);
                    }
                }
            }
            int elementEntering = arr[i];
            if(elementEntering<k){
                count[elementEntering]++;
            }
            if(elementEntering<k){
                set.remove(elementEntering);
            }
            if(i>=k-1){
                sb.append(set.first()).append(' ');
            }
        }
        System.out.print(sb.toString());
    }
}
