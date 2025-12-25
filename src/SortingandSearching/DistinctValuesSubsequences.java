package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class DistinctValuesSubsequences {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer,Integer> freq = new HashMap<>();
        for(int i=0;i<n;i++){
            freq.put(arr[i],freq.getOrDefault(arr[i],0)+1);
        }

        long ans = 1;

        for(Map.Entry<Integer,Integer> e : freq.entrySet()){
            ans = (ans % MOD * (e.getValue()+1) % MOD)% MOD;
        }
        ans = (ans + MOD -1)%MOD;
        System.out.println(ans);
    }
}
