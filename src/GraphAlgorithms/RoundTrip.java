package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundTrip {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] parent;
    static int cycleStart = -1, cycleEnd = -1;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
        graph = new ArrayList[n + 1];
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        visited = new boolean[n + 1];
        boolean cycleExist = false;
        Arrays.fill(parent, -1);

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) {
                    cycleExist = true;
                    break;
                }
            }
        }
        if(!cycleExist){
            System.out.println("IMPOSSIBLE");
            return;
        }
        List<Integer> cycle = new ArrayList<>();
        cycle.add(cycleStart);

        for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
            cycle.add(v);
        }
        cycle.add(cycleStart);

        StringBuilder out = new StringBuilder();
        out.append(cycle.size()).append("\n");
        for(int node: cycle){
            out.append(node).append(" ");
        }
        System.out.println(out.toString());

    }
    static boolean dfs(int node,int par) {
        visited[node] = true;
        for(int child: graph[node]){
            if(!visited[child]){
                parent[child] = node;
               if(dfs(child,node)){
                   return true;
               }
            }else if(child!=par){
                cycleStart = child;
                cycleEnd = node;
                return true;
            }
        }
        return false;

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
