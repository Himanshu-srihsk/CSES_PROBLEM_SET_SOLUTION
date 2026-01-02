

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountingDivisors {
    static int MOD = 1000000007;
    static List<Integer> allPrimes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int t = fs.nextInt();
       seive(1000000);
       StringBuilder ans = new StringBuilder();
        while (t-->0){
            long res = 1;
            long n = fs.nextLong();
            List<Integer> powers = new ArrayList<>();
            for(int i: allPrimes){
                if((long)i*i >n) break;
                if(n%i==0){
                    int cnt = 0;
                    while(n%i==0){

                        n/=i;
                        cnt++;
                    }
                    powers.add(cnt);
                }
            }
            if(n!=1){
                // measn n itslef is prime no ex : n=17
                powers.add(1);
            }
            for(int k:powers){
                res = res*(k+1);
            }
            ans.append(res).append("\n");
        }
        System.out.println(ans.toString());
    }

    private static void seive(int n) {
        boolean[] isPrime = new boolean[n+1];
        isPrime[0]=false;
        isPrime[1]=false;
        Arrays.fill(isPrime,true);
        for(int i=2;i*i<=n;i++){
            if(isPrime[i]){
                for(int j=i*i;j<=n;j+=i){
                    isPrime[j] = false;
                }
            }
        }
        for(int i=2;i<=n;i++){
            if(isPrime[i]){
                allPrimes.add(i);
            }
        }

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
