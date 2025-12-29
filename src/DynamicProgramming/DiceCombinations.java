package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class DiceCombinations {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       long[] dp = new long[n+1];

       dp[0] = 1;
//Arrays.fill(dp,-1);
     // findWays(dp,n);



       for(int i=1;i<=n;i++){
           for(int j=1;j<=6;j++){
               if(i-j>=0){
                   dp[i] = (dp[i]+dp[i-j])%MOD;
               }
           }
       }
        System.out.println(dp[n]%MOD);
    }

    public static  long  findWays(long[] dp, int n) {
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        long way = 0;
        for(int i=1;i<=6;i++){
            way = (way + findWays(dp,n-i))%MOD;
        }
       return dp[n] = way;

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
