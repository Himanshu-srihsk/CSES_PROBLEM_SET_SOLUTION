package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IncreasingSubsequenceII {
    static final int MOD = 1000000007;
    static class FenwickTree{
        int n;
        long[] bit;
        FenwickTree(int n){
            this.n = n;
            bit = new long[n+1];
        }
        void update(int idx,long delta){
            while(idx<=n){
                bit[idx]= (bit[idx] + delta)%MOD;
                idx = idx+(idx&-idx);
            }
        }
        long sum(int idx){
            long sum = 0;
            while(idx>0){
                sum= (sum + bit[idx])%MOD;
                idx = idx - (idx&-idx);
            }
            return sum;
        }
    }
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n = fs.nextInt();
        int[] a = new int[n];
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextInt();
        }

        int[] sorted = a.clone();
        Arrays.sort(sorted);
        Map<Integer,Integer> comp = new HashMap<>();
        int id = 1;
        for(int i: sorted){
            if(!comp.containsKey(i)){
                comp.put(i,id++);
            }
        }
        FenwickTree fenwickTree = new FenwickTree(id-1);

        for(int i=0;i<n;i++){
            int v = comp.get(a[i]);
            long cnt = fenwickTree.sum(v-1);
            fenwickTree.update(v,cnt+1);
        }
        System.out.println(fenwickTree.sum(id-1));


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
