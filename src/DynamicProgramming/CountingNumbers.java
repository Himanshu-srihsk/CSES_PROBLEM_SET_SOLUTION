package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;

public class CountingNumbers {
    static final int MOD = 1000000007;
    static long[][][][] dp;

    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       long a = fs.nextLong();
       long b = fs.nextLong();
        dp = new long[String.valueOf(a).length()][2][2][11];
        for (long[][][] x : dp)
            for (long[][] y : x)
                for (long[] z : y)
                    java.util.Arrays.fill(z, -1);

        long num1 = numWays(String.valueOf(a-1),0,1,10,1);

        dp = new long[String.valueOf(b).length()][2][2][11];
        for (long[][][] x : dp)
            for (long[][] y : x)
                for (long[] z : y)
                    java.util.Arrays.fill(z, -1);

        long num2 = numWays(String.valueOf(b),0,1,10,1);

        long ans = (num2-num1);
       System.out.println(ans);
    }

    private static long numWays(String s,int ind,int tight,int prevDigit,int leadingZero) {
        if(ind==s.length()){
            return 1;
        }
        if(dp[ind][tight][leadingZero][prevDigit]!=-1){
            return dp[ind][tight][leadingZero][prevDigit];
        }
       int low = 0;
       int high = s.charAt(ind) - '0';
       high = tight==1? high : 9;
        long res  = 0;

       for(int digit=low;digit<=high;digit++){
          if(digit==0 && leadingZero==1){
             res = res+numWays(s,ind+1,(digit==high && tight==1)? 1: 0, 10,1);
          }else{
              if( digit!=prevDigit){
                  res = res+numWays(s,ind+1,(digit==high && tight==1)? 1: 0, digit,0);
              }
          }
       }
        return dp[ind][tight][leadingZero][prevDigit] = res;
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
