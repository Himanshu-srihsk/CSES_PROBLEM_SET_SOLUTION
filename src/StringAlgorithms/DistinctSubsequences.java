package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class DistinctSubsequences {
    static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String s = fs.nextString();
        int n = s.length();
        long[] dp = new long[n+1];
        dp[0]=1;

        int[] last = new int[26];
        Arrays.fill(last,-1);
        for(int i=1;i<=n;i++){
            char c = s.charAt(i-1);
            dp[i] = (dp[i-1]*2)%MOD;
            if(last[c-'a']!=-1){
                int j = last[c-'a'];
                dp[i] = (dp[i] - dp[j]+MOD)%MOD;
            }
            last[c-'a'] = i-1;

        }
        System.out.println((dp[n]-1+MOD)%MOD);
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
