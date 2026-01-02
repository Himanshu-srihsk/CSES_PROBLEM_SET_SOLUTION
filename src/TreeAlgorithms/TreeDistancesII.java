

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TreeDistancesII {
    static List<Integer>[] tree;
    static long[] subDistanceSum;
    static long[] subTreeSize;
    static long[] res;
    static int n;
    public static void main(String[] args) throws IOException {
        /*
         Tree DP + Rerooting
         https://www.youtube.com/watch?v=ghGbPzwk0L0
         */
        FastScanner fs = new FastScanner(System.in);
        n = fs.nextInt();
        tree = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }
        subDistanceSum = new long[n+1];
        subTreeSize = new long[n+1];
        /*
        lets start with 1 assuming root node
         */
        preProcessSubTreeSizeAndDistanceSum(1,-1); // need to optimise this
         res = new long[n+1];
        res[1] = subDistanceSum[1];

        /*
        for caluclating other results we need to do rerooting
         */
//        for(int child : tree[1]){
//            rerootingdfs(child,1);
//        }
        rerootIterative(1);
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=n;i++){
            ans.append(res[i]).append(" ");
        }
        System.out.println(ans.toString());
    }

    private static void rerootingdfs(int vertex, int parent) {
        res[vertex] = (res[parent] - subDistanceSum[vertex] - subTreeSize[vertex]) + (n - subTreeSize[vertex]) + subDistanceSum[vertex];
        for(int child: tree[vertex]){
            if(child!=parent){
                rerootingdfs(child,vertex);
            }
        }
    }
    // Rerooting using BFS
    static void rerootIterative(int root) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        q.add(root);
        parent[root] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : tree[u]) {
                if (v != parent[u]) {
                    res[v] = res[u] + n - 2 * subTreeSize[v];
                    parent[v] = u;
                    q.add(v);
                }
            }
        }
    }

    private static void preProcessSubTreeSizeAndDistanceSum(int vertex, int parent) {
         subTreeSize[vertex] = 1;
         for(int child : tree[vertex]){
             if(child!=parent){
                 preProcessSubTreeSizeAndDistanceSum(child,vertex);
                 subTreeSize[vertex]+=subTreeSize[child];
                 subDistanceSum[vertex] += subTreeSize[child] + subDistanceSum[child];
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
