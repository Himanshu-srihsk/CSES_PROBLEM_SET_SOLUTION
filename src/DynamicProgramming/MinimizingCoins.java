package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class MinimizingCoins {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int x = fs.nextInt();
        int[] coins = new int[n];

        for(int i=0;i<n;i++){
            coins[i] = fs.nextInt();
        }
//        int[][] dp = new int[n+1][x+1];
//        for(int i=0;i<n;i++){
//            Arrays.fill(dp[i],-1);
//        }
//        Arrays.sort(coins);
//
//        int ans = findMinCoin(dp,x,n-1,coins);
//        System.out.println(ans);
        int[] dp = new int[x+1];
        for(int i=1;i<=x;i++){
            dp[i] = Integer.MAX_VALUE;
            int result = Integer.MAX_VALUE;
            for(int coin: coins){
                if(i>=coin){
                    result = dp[i-coin];
                }
                if(result!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i],1+result);
                }
            }
        }
        if(dp[x]==Integer.MAX_VALUE){
            dp[x] = -1;
        }
        System.out.println(dp[x]);
    }

    private static int findMinCoin(int[][] dp, int x, int n,int[] coins) {

        if(x==0){
            return 0;
        }
        if(x<0 || n<0){
            return 1000000000;
        }
        if(dp[n][x]!=-1){
            return dp[n][x];
        }
        int include = 1+findMinCoin(dp,x-coins[n],n,coins);
        int exclude =  findMinCoin(dp,x,n-1,coins);
        return dp[n][x]= Math.min(include,exclude);
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
