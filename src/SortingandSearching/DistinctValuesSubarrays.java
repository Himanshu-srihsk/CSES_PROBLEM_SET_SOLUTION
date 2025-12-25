package SortingandSearching;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DistinctValuesSubarrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> freq = new HashMap<>();
        long ans = 0;
        int l = 0;
        for(int r=0;r<n;r++){
            freq.put(arr[r], freq.getOrDefault(arr[r], 0) + 1);

            while(freq.get(arr[r])>1){
                freq.put(arr[l],freq.get(arr[l])-1);
                l++;
            }

            ans += (r - l + 1);

        }

        System.out.println(ans);

    }
}
