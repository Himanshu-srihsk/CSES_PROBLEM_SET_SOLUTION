package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class GridPathsI {
    static int[] x = {1,0};
    static int[] y = {0,1};
    static final int MOD = 1000000007;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n = fs.nextInt();
      int[][] mat = new int[n][n];
      for(int i=0;i<n;i++){
          String s = fs.nextString();
          for(int j=0;j<n;j++){
              if(s.charAt(j)=='*'){
                  mat[i][j] = 0;
              }else{
                  mat[i][j] = 1;
              }
          }
      }
       dp = new long[n][n];
      for(int i=0;i<n;i++){
          Arrays.fill(dp[i],-1);
      }
        long ans = findways(mat,0,0,n,n);
      System.out.println(ans);
    }
    private static long findways(int[][] mat,int i,int j,int n,int m){
        if(mat[i][j]==0 || mat[n-1][m-1]==0){
            return 0;
        }
        if(i==n-1 && j==m-1){
            return 1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        long way =0;
        for(int k=0;k<x.length;k++){
            int newx = i+x[k];
            int newy = j+y[k];

            if(isSafe(newx,newy,mat,n,m)){
                way = (way % MOD+findways(mat,newx,newy,n,m) % MOD)%MOD;
            }
        }
        return dp[i][j]=way;

    }

    private static boolean isSafe(int newx, int newy, int[][] mat,int n,int m) {
        if(newx< 0 || newx>=n || newy<0 || newy>=m || mat[newx][newy]==0){
            return false;
        }
        return true;
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
