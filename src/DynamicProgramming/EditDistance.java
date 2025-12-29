package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;

public class EditDistance {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
         FastScanner fs = new FastScanner(System.in);
         String s1 = fs.nextString();
         String s2 = fs.nextString();

         int n = s1.length();
         int m = s2.length();

         int[][] dp = new int[n+1][m+1];
         for(int i=0;i<=n;i++){
             dp[i][0] = i;
         }
         for(int j=0;j<=m;j++){
             dp[0][j] = j;
         }

         for(int i=1;i<=n;i++){
             for(int j=1;j<=m;j++){
                 int cost = 0;
                 if(s1.charAt(i-1)==s2.charAt(j-1)){
                     cost = 0;
                 }else{
                     cost = 1;
                 }

                 dp[i][j] = Math.min(dp[i-1][j-1]+cost,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
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
