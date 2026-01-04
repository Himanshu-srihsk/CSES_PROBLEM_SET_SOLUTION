

import java.io.IOException;
import java.io.InputStream;

public class ListRemovals {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = fs.nextInt();
        }

        FenwickTree fenwickTree = new FenwickTree(n);

        for (int i = 1; i <= n; i++) {
            fenwickTree.update(i, 1); // make all index alive
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int k = fs.nextInt();
            int pos = fenwickTree.findKthSum(k);
            fenwickTree.update(pos,-1); //make it dead
            ans.append(arr[pos]).append(" ");
        }
        System.out.println(ans.toString());



    }
    static class FenwickTree{
        int n;
        int[] bit;
        FenwickTree(int n){
            this.n = n;
            bit = new int[n+1];
        }
        void update(int index,int delta){
            while (index<=n){
                bit[index]+=delta;
                index = index + (index & -index);
            }
        }

        int sum(int index){
            int sum = 0;
            while (index>0){
                sum+=bit[index];
                index = index - (index&-index);
            }
            return sum;
        }

        //find smallest index such that sum(index) >= k
        int findKthSum(int k){
            int idx = 0;
            int bitMask = Integer.highestOneBit(n);

            while (bitMask>0){
                int next = (idx + bitMask);
                if(next<=n && bit[next] <k){
                    k-=bit[next];
                    idx = next;
                }
                bitMask>>=1;
            }
            return idx+1;
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
