package BitwiseOperations;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CountingBits {
    static Map<Long,Long> map;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        Long n = fs.nextLong();
       map = new HashMap<>();
        Long ans = calculate(n);
       System.out.println(ans);
    }

    private static Long calculate(Long n) {
        if(n==0){
            return 0l;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        Long ans;
        if((n&1)!=0){
            // n= odd
            ans = 2* calculate(n/2) + n/2 +1;
        }else{
            ans = calculate(n/2-1)+calculate(n/2)+n/2;
        }
        map.put(n,ans);
        return map.get(n);
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
