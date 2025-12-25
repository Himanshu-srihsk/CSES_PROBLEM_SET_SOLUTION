package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MissingCoinSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());



//        int low = 1;
//        int high = 0;
//        int sum = 0;
//
        for(int i=0;i<n;i++){

            arr[i] = Long.parseLong(st.nextToken());
          //  high+=arr[i];
        }
        Arrays.sort(arr);
//        sum = high;
//        int ans = -1;
//
//        boolean[] dp = SubSetNotPoss(sum,arr) ;
//        for(int i = low ;i<=high;i++){
//            if(!dp[i]){
//                    ans = i;
//                    break;
//                }
//        }
//
//        if(ans == -1){
//            ans = sum+1;
//        }

        long reachableCoin = 0;
        for(long coin : arr){
            if(reachableCoin+1 < coin){
                break;
            }
            reachableCoin+=coin;
        }
        System.out.println(reachableCoin+1);

    }

    private static boolean[]  SubSetNotPoss(int target, int[] arr) {
        int n = arr.length;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for (int num : arr) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }
        return dp;
    }
}
