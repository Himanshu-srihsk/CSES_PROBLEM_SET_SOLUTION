package Mathematics;

import java.io.IOException;
import java.io.InputStream;
//https://www.youtube.com/watch?v=OTb3KNKtmaQ
public class SumofDivisors {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       long n = fs.nextLong();
       long d = 1;
       long ans = 0;
       while (d<=n){
           long q = n/d;
           long r = n/q;
           long sumD =  rangeSum(d,r);
           ans = (ans + (sumD * (q % MOD)) % MOD) % MOD;
           d = r +1;
       }
       System.out.println(ans%MOD);
    }

    private static long rangeSum(long L, long R) {
        //((L+R) *(R-L+1))/2;
        long cnt = (R-L+1)%MOD;
        long sumEnds = (L%MOD + R%MOD)%MOD;
        long res = (cnt * sumEnds)%MOD;


//        a / b  ≡  a × inverse(b) (mod M)
//        inverse(b) = b^(M-2) mod M
        long inverse2 = exponentiation(2, MOD-2);
        res = (res * inverse2) % MOD;
        return res;
    }
    private static long exponentiation(long a, long b) {
        long res = 1;
        long base = a;
        while(b>0){
            if(b%2!=0){
                res = (res * base)%MOD;
            }
            base = (base * base)%MOD;
            b>>=1;
        }
        return res;
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
