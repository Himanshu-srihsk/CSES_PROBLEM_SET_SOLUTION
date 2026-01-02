package RangeQueries;

import java.io.IOException;
import java.io.InputStream;

public class StaticRangeSumQueries {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int q = fs.nextInt();
       long[] arr = new long[n];
       for(int i=0;i<n;i++){
           arr[i] = fs.nextLong();
       }
        long[] prefix = new long[n+1];
       prefix[0] = 0;
       for(int i=1;i<=n;i++){
           prefix[i] = prefix[i-1]+arr[i-1];
       }
       StringBuilder ans = new StringBuilder();
       while (q-- >0){
           int a = fs.nextInt();
           int b = fs.nextInt();
           ans.append(prefix[b]-prefix[a-1]).append("\n");
       }
       System.out.println(ans.toString());
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
