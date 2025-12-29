package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;

//https://www.youtube.com/watch?v=ZeRewE1Ks5Q&list=PLcXpkI9A-RZI-xF76L0sZq_u-k_yHz8pd&index=12

public class CountingTowers {
    static final int MOD = 1000000007;
    static long[][]dp = new long[1000007][2];
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int m = fs.nextInt();
        StringBuilder ans = new StringBuilder();
       for(int j=0;j<m;j++){
           int n = fs.nextInt();



           //dp[i][0] - no of ways to fill the grid from ith row to the top such that there is 1 1*2 block on the i-1th row trying to extend forward
           //dp[i][1] - no of ways to fill the grid from ith row to the top such that there is 2 2*1  block on the i-1th row trying to extend forward

           dp[n][0] = 1;
           dp[n][1] = 1;
           for(int i=n-1;i>=0;i--){
               dp[i][0] = (2l * dp[i+1][0] + dp[i+1][1])%MOD;
               dp[i][1] = (4l * dp[i+1][1] + dp[i+1][0])%MOD;
           }
           long val = (dp[1][0]+dp[1][1])%MOD;
           ans.append(val).append("\n");

       }

       System.out.println(ans.toString());


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
