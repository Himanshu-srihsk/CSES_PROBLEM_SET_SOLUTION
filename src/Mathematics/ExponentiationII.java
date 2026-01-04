package Mathematics;

import java.io.IOException;
import java.io.InputStream;

public class ExponentiationII {
    static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        StringBuilder ans = new StringBuilder();
        /*
        Fermat Little Theorem
        https://www.youtube.com/watch?v=DhZdzYFHFXc

        https://www.youtube.com/watch?v=YPJ38aWeyGI
         */
        while (t-->0){
            long a,b,c;
            a= fs.nextLong();
            b=fs.nextLong();
            c = fs.nextLong();
//            long res = exponentiation(b,c);
//            res = exponentiation(a,res);
//            ans.append(res).append("\n");
            //TO DO

            long b_pow_c = exponentiation(b,c,MOD-1);
            long finalAns = exponentiation(a,b_pow_c,MOD);
            ans.append(finalAns).append("\n");
        }
        System.out.println(ans.toString());
    }


    private static long exponentiation(long a, long b,long MOD) {
        long res = 1;
        long base = a;
        if(a==0 && b==0){
            return 1l;
        }
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
