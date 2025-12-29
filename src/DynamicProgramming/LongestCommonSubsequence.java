package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;

public class LongestCommonSubsequence {
    static final int MOD = 1000000007;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();

       int[] a = new int[n];
       int[] b= new int[m];
       for(int i=0;i<n;i++){
           a[i] = fs.nextInt();
       }
       for(int i=0;i<m;i++){
           b[i] = fs.nextInt();
       }
       dp = new int[n+1][m+1];

       for(int i=1;i<=n;i++){
           for(int j=1;j<=m;j++){
               if(a[i-1]==b[j-1]){
                   dp[i][j] = dp[i-1][j-1]+1;
               }else{
                   dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
               }
           }
       }
       System.out.println(dp[n][m]);
       System.out.println(lcs(a,b,n,m));

    }

    private static String lcs(int[] a, int[] b, int n, int m) {
        if(n==0 || m==0){
            return new String();
        }
        if(a[n-1]==b[m-1]){
            return lcs(a,b,n-1,m-1)+a[n-1]+ " ";
        }
        if(dp[n-1][m]>dp[n][m-1]){
            return lcs(a,b,n-1,m);
        }else{
            return lcs(a,b,n,m-1);
        }
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
