package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CoinCombinationsI {
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

        int[] dp = new int[x+1];
        dp[0] = 1;
        for(int i=1;i<=x;i++){
            for(int j=0;j<n;j++){
                if(i>=coins[j]){
                    dp[i] = (dp[i-coins[j]]+ dp[i])%MOD;
                }
            }
        }
        System.out.println(dp[x]);

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
