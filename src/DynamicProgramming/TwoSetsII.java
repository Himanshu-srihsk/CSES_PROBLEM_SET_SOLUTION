
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TwoSetsII {
    static final int MOD = 1000000007;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int sum = (n*(n+1))/2;
       if(sum%2!=0){
           System.out.println(0);
           return;
       }
       int sumhalf = sum/2;
//        dp = new long[n + 1][sumhalf + 1];
//        for (long[] row : dp) Arrays.fill(row, -1);
//
//        long ways = findways(sumhalf,n-1);
//       System.out.println(ways);
/*
n-1 kyun karte hain?Agar hum n numbers ke saath loop chalayein,
 toh har partition do baar count hota hai (Symmetry ki wajah se). Isse bachne ke liye hum ye karte hain:
 */
        long[] dp1 = new long[sumhalf+1];
        dp1[0] = 1;
        for(int i=1;i<n;i++){
            for(int j= sumhalf;j>=i;j--){
                dp1[j]= (dp1[j]+dp1[j-i])%MOD;
            }
        }
        System.out.println(dp1[sumhalf]);

    }
    private static long findways(int sum,int n){
        if(sum==0){
            return 1;
        }
        if(sum<0 || n<0){
            return 0;
        }
        if (dp[n][sum] != -1) return dp[n][sum];
        long include = findways(sum-n,n-1);
        long exclude = findways(sum,n-1);
        return dp[n][sum] = (include+exclude)%MOD;
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
