package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class BookShop {
    static final int MOD = 1000000007;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n = fs.nextInt();
      int x = fs.nextInt();
      int prices[] = new int[n];
      int[] pages = new int[n];
      for(int i=0;i<n;i++){
          prices[i] = fs.nextInt();
      }
        for(int i=0;i<n;i++){
            pages[i] = fs.nextInt();
        }
//        dp = new long[x+1][n+1];
//        for(int i=0;i<=x;i++){
//            Arrays.fill(dp[i],-1);
//        }
//        long ans = findMaxPages(prices,pages,x,n-1);
//      System.out.println(ans);

//        int[] dp = new int[x+1];
//        dp[0] = 0;
//        for(int i=0;i<n;i++){
//            for(int j = x;j>=prices[i];j--){
//                /*
//                Agar hum loop seedha (j = currentPrice to x) chalayenge, toh woh Unbounded Knapsack (ek book ko kai baar kharidna) ban jayega.
//                 Reverse loop ensure karta hai ki dp[j - currentPrice] hamesha purani state (pichli book tak ki) se aaye.
//                 */
//                dp[j] = Math.max(dp[j],(pages[i]+dp[j-prices[i]])%MOD);
//            }
//        }
//        System.out.println(dp[x]);

        int[] prev = new int[x+1];

        for(int i=0;i<n;i++){
            int[] dp = new int[x+1];
            for(int j = 0;j<=x;j++){
                int currentPrice = prices[i];
                int pagesinBook = pages[i];
                int pick = (j>=currentPrice ? (prev[j-currentPrice]+pagesinBook)%MOD:0);
                int skip = prev[j];
                dp[j] = Math.max(pick,skip);

            }
            prev = dp;
        }
        System.out.println(prev[x]);
    }

    private static long findMaxPages(int[] prices, int[] pages, int x, int n) {
        if(x<0){
            return 0;
        }
        if(n<0){
            return 0;
        }
        /*
        if n = 1000 aur x = 100,000 hai.

        dp[x+1][n+1]
        100,000 times 1,000 = 10^8 entries.
        Java mein ek long 8 bytes leta hai.
        Total memory: 10^8 times 8 -> approx 800  MB
        CSES ki limit 512 MB hai.
        code memory limit cross kar dega
         */
        if(dp[x][n]!=-1){
            return dp[x][n];
        }
        long include = 0;
        if(x>=prices[n]){
            include = (pages[n]+findMaxPages(prices,pages,x-prices[n],n-1))%MOD;
        }
        long exclude = findMaxPages(prices,pages,x,n-1)%MOD;
        return dp[x][n] = Math.max(include,exclude);
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
