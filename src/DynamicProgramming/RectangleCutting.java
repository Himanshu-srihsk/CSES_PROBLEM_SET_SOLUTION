package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class RectangleCutting {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            Arrays.fill(dp[i],MOD);
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                 if(i==j){
                     dp[i][j]= 0;
                     continue;
                 }

                //make a vertical cut
                for(int h=1;h<=i-1;h++){
                   dp[i][j] = Math.min(dp[i][j], dp[h][j]+dp[i-h][j]+1);
                }
                 //make a vertical cut
                 for(int v=1;v<=j-1;v++){
                   dp[i][j] = Math.min(dp[i][j], dp[i][v]+dp[i][j-v]+1);
                 }
            }
        }
        System.out.println(dp[n][m]);
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
