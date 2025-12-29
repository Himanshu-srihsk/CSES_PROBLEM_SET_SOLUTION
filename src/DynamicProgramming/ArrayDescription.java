package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;

public class ArrayDescription {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       int []arr = new int[n];
       for(int i=0;i<n;i++){
           arr[i] = fs.nextInt();
       }

       int[][] dp = new int[n+1][m+1];
       /*
       dp[i][k] = number of ways to build the prefix of size i such thta the last element of prefix is k
        */
        for(int i=1;i<=m;i++){
            if(arr[0]==0 || arr[0]==i){
                dp[1][i] = 1;
            }
        }


        for(int i=2;i<=n;i++){
            for(int k=1;k<=m;k++){
                if(arr[i-1]!=0 &&  arr[i-1]!=k){
                    dp[i][k] = 0;
                    continue;
                }

                for(int prev = k-1;prev<=k+1;prev++){
                    if(!valid(prev,m)){
                        continue;
                    }
                    dp[i][k] = (dp[i][k]+dp[i-1][prev])%MOD;
                }
            }
        }

        int ans = 0;
        for(int i=1;i<=m;i++){
            ans = (ans+dp[n][i])%MOD;
        }
        System.out.println(ans);

    }
    static boolean valid(int x,int m){
        return x>=1 && x<=m;
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
