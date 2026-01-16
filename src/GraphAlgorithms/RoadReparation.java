package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoadReparation {

    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       /*
       Kruskal Algo Minimum Spannning tree
        */
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            int w = fs.nextInt();
            edges.add(new Edge(u,v,w));
        }
        Collections.sort(edges,(a,b)-> a.weight - b.weight);
        List<Edge> MST = new ArrayList<>();
        int index = 0;
        DisJointSet ds = new DisJointSet(n);
        long cost = 0l;
        while (MST.size()!=n-1  && index < edges.size()){
            Edge next = edges.get(index);
            if(ds.union(next.src,next.dest)){
                MST.add(next);
                cost+=(long) next.weight;
            }
            index++;
        }
        if(ds.numComponents>1){
           System.out.println("IMPOSSIBLE");
           return;
        }
        System.out.println(cost);

    }
    static class DisJointSet{
        int[] parent;
        int[] size;
        int numComponents;
        public DisJointSet(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            numComponents = n;
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public boolean union(int a, int b) {
            int x = find(a);
            int y = find(b);
            if(x==y){
                return false;
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
            return true;
        }
        public int find(int k) {
            if (parent[k] == k) return k;
            return parent[k] = find(parent[k]); // Path compression
        }
    }

    static class Edge{
        int src;
        int dest;
        int weight;
        Edge(int src,int dest,int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
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
