

import java.io.IOException;
import java.io.InputStream;

public class BinomialCoefficients {
    static final int MOD = 1_000_000_007;
    static final int MAX = 1_000_000;
    static long[] fact = new long[MAX + 1];
    static long[] invFact = new long[MAX + 1];
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        precompute();

        int q = fs.nextInt();
        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            if (k < 0 || k > n) {
                sb.append(0).append('\n');
            }else{
                long ans = fact[n];
                ans = (ans * invFact[k])%MOD;
                ans = (ans * invFact[n-k])%MOD;
                sb.append(ans).append("\n");
            }
        }
        System.out.println(sb.toString());

    }

    private static void precompute() {
        fact[0]= 1;
        for(int i=1;i<=MAX;i++){
            fact[i]= (fact[i-1]*i)%MOD;
        }

        /*
        n! / (k! * (n-k)!)
        a / b  ≡  a × inverse(b) (mod M)
        inverse(b) = b^(M-2) mod M

        (i+1)! = (i+1) × i!
        inverse(i!) = inverse((i+1)!) × (i+1)
invFact[i] = invFact[i+1] * (i+1) % MOD;


         */
        invFact[MAX] = exponentiation(fact[MAX], MOD-2);
        for (int i = MAX - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

//        for(int i= MAX;i>=0;i--){
//            invFact[i] = exponentiation(fact[i], MOD-2);
//        }
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
