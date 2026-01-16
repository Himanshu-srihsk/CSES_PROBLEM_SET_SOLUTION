package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightRoutesCheck {
    static List<Edge>[] graph;
    static List<Edge>[] graphR;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       /*
       SCC - Kosaraju Algorithm
        */
        graph = new ArrayList[n+1];
        graphR = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) graphR[i] = new ArrayList<>();
        while (m-- >0){
            int u = fs.nextInt();
            int v = fs.nextInt();
            graph[u].add(new Edge(u,v));
            graphR[v].add(new Edge(v,u));
        }
        boolean[] visited = new boolean[n+1];

        dfs(graph,1,visited);
        for (int i=1;i<=n;i++)
        {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println(1+" "+i);
                return;
            }
        }

        Arrays.fill(visited,false);
        dfs(graphR,1,visited);
        for (int i=1;i<=n;i++)
        {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println(i+" "+1);
                return;
            }
        }
        System.out.println("YES");

    }
    static void dfs(List<Edge> [] graph, int src, boolean[] visited){
        visited[src] = true;
        for(Edge e: graph[src]){
            if(!visited[e.dest]){
                dfs(graph,e.dest,visited);
            }
        }
    }
    static class Edge
    {
        int source, dest;


        public Edge(int source, int dest)
        {
            this.source = source;
            this.dest = dest;

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
