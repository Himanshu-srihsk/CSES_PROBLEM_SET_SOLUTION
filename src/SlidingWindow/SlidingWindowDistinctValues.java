package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SlidingWindowDistinctValues {
    public static void main(String[] args) throws IOException {
      // done because of slow input output  causing TLE
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int idx = 0;
        while(idx < n) {
            if (!st.hasMoreTokens()) {
                // Read the next line if the current one is exhausted
                String line = br.readLine();
                if (line == null) break; // Should not happen with valid input
                st = new StringTokenizer(line);
            }
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<k;i++){
           map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        System.out.println();
        System.out.print(map.size()+" ");
        for(int i=k;i<n;i++){
            int elementEntering = arr[i];
            int elementLeaving = arr[i-k];
            map.put(elementLeaving,map.get(elementLeaving)-1);
            if(map.get(elementLeaving)==0){
                map.remove(elementLeaving);
            }
            map.put(elementEntering,map.getOrDefault(elementEntering,0)+1);
            System.out.print(map.size()+" ");
        }

    }
}
