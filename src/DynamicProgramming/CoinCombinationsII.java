package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CoinCombinationsII {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int x = fs.nextInt();
        int[] coins = new int[n];

        for(int i=0;i<n;i++){
            coins[i] = fs.nextInt();
        }
        Arrays.sort(coins);
        /*
        runtime erro and TLE with recusrisve sol
         */
//        Set<Set<Integer>> coinset = new HashSet<>();
//        Map<String,Integer> map = new HashMap<>();
//       int ans = findways(coins,x,0,coinset,map);
//       System.out.println(ans);

        int[] dp = new int[x+1];
        dp[0] = 1;
        for(int i=0;i<n;i++){
            if(i+1<n && coins[i]==coins[i+1]){
                continue;
            }
            for(int j=1;j<=x;j++){
                if(coins[i]<=j){
                    dp[j] = (dp[j]+dp[j-coins[i]])%MOD;
                }
            }
        }
        System.out.println(dp[x]);
    }

    private static int findways(int[] coins, int x,int i,Set<Set<Integer>> coinset,Map<String,Integer> map) {
       int n = coins.length;
       int exclude = 0;
       int include = 0;
       Set<Integer> set = new HashSet<>();
       String key = x+" "+i;
       if(map.containsKey(key)){
           return  map.get(key);
       }

       if(x<=0 || i>=n){
           if(x==0 && !coinset.contains(set)) {
               return 1;
           }
           return 0;
       }

       if(i+1<n && coins[i]==coins[i+1]){
           exclude= findways(coins,x,i+1,coinset,map);
       }else{
           exclude= exclude+findways(coins,x,i+1,coinset,map);
           set.add(coins[i]);
           include = findways(coins,x-coins[i],i,coinset,map);
           set.remove(coins[i]);
       }
       map.put(key,(include+exclude)%MOD);
       return map.get(key);

    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sgn = 1, res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sgn;
        }
        long nextLong() throws IOException {
            int c, sgn = 1;
            long res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sgn;
        }
        String nextString() throws IOException {
            int c;
            StringBuilder sb = new StringBuilder();

            // skip whitespace
            do {
                c = read();
            } while (c <= ' ');

            // read characters until whitespace
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }
    }
}
