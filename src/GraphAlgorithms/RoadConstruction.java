package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RoadConstruction {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       /*
       Disjoint Set Union
        */
        int[] sets = new int[n];
        for(int i=0;i<n;i++){
            sets[i] = i+1;
        }
        DisJointSet  ds = new DisJointSet(n);
        StringBuilder out = new StringBuilder();
        while (m-->0){
            int a =fs.nextInt();
            int b = fs.nextInt();
            ds.union(a,b);

            int numComponent = ds.numComponents;
            int largestComponent = ds.maxSize;
            out.append(numComponent).append(" ").append(largestComponent).append("\n");
        }
        System.out.println(out.toString());

    }
    static class DisJointSet{
        int[] parent;
        int[] size;
        int numComponents;
        int maxSize;
        public DisJointSet(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            numComponents = n;
            maxSize = 1;
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int x = find(a);
            int y = find(b);
            if(x==y){
                return;
            }
            if(size[x]>size[y]){
                parent[y] = x;
                size[x]+=size[y];
            }else if(size[x]<size[y]){
                parent[x] = y;
                size[y]+=size[x];
            }else {
                parent[x] = y;
                size[y]+=size[x];
            }
            numComponents--;
            maxSize = Math.max(maxSize,Math.max(size[x],size[y]));
        }
        public int find(int k) {
            if (parent[k] == k) return k;
            return parent[k] = find(parent[k]); // Path compression
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
