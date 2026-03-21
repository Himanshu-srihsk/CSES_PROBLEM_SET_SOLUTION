package Mathematics;

import java.io.IOException;
import java.io.InputStream;

public class FibonacciNumbers {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        long n = fs.nextLong();
       //https://www.youtube.com/watch?v=EEb6JP3NXBI
/*
Matrix exponentiation

f(n) = f(n-1) + f(n-2)
     = [1 1] [f(n-1) ]
             [ f(n-2)]

          i.e 1*2 Mat  and 2*1 matrix
          above cannot be optimised as it wuill still be o(n) operations as lots of inners matrix multiplication h
          as to be done as outer num of roes and col is difft but bewlo can be used dto optimise in o(logn)

          now
          [f(n) ]     = [1 1] [f(n-1)]
           [ f(n-1)]    [1 0] [f(n-2)]


           [f(2) ]     = [1 1] [f(1)]
           [ f(1)]       [1 0] [f(0)]

           [f(3) ]     = [1 1] [f(2)]
           [ f(2)]       [1 0] [f(1)]

            [f(3) ]     = [1 1] [1 1] [f(1)]
           [ f(2)]       [1 0]  [1 0] [f(0)]

                              n-1
           [f(n) ]     = [1 1] [f(1)]
           [ f(n-1)]    [1 0] [f(0)]

 */
        if(n == 0){
            System.out.println(0);
            return;
        }
        long[][] base = {
                {1,1},
                {1,0}
        };

        long[][] res = matrixPower(base, n-1);

        System.out.println(res[0][0]);





    }

    private static long[][] matrixPower(long[][] a, long n) {
        long[][] result = {
                {1,0},
                {0,1}
        };
        while(n > 0){

            if((n & 1) == 1){
                result = multiply(result, a);
            }

            a = multiply(a, a);
            n >>= 1;
        }

        return result;

    }

    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[2][2];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                for(int k=0;k<2;k++){
                    res[i][j] =
                            (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
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
