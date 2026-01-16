import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Investigation {
    static List<Edge>[] graph;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for(int i=0;i<m;i++){
            int u = fs.nextInt();
            int v = fs.nextInt();
            long d = fs.nextInt();
            graph[u].add(new Edge(u,v,d));
        }
        findShortestPaths(1,n);
    }
    private static void findShortestPaths(int src, int n) {
        long[] dist = new long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[src] = 0l;

        long[] ways = new long[n+1];
        ways[src] = 1;

        long[] miniF = new long[n+1];
        long[] maxiF = new long[n+1];


        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingLong(node -> node.weight));
        minHeap.add(new Node(src,dist[src]));

        boolean[] done = new boolean[n+1];

        while (!minHeap.isEmpty()){
            Node node = minHeap.poll();
            int u = node.vertex;


            if (done[u]) continue;
            done[u] = true;

            for(Edge e: graph[u]){
                int v = e.dest;
                long w = e.weight;
                if(!done[v] && dist[u]+w < dist[v]){
                    dist[v] = dist[u]+w;
                    ways[v] = ways[u];
                    miniF[v] = miniF[u]+1;
                    maxiF[v] = maxiF[u]+1;
                    minHeap.add(new Node(v,dist[v]));
                }
                else if(!done[v] && dist[u]+w == dist[v]){
                    ways[v]=(ways[v] +ways[u])%MOD;
                    miniF[v] = Math.min(miniF[u]+1,miniF[v]);
                    maxiF[v] = Math.max(maxiF[v],maxiF[u]+1);
                }
            }


        }

        StringBuilder out = new StringBuilder();
        out.append(dist[n]).append(" ").append(ways[n]).append(" ").append(miniF[n]).append(" ").append(maxiF[n]);
        System.out.println(out.toString());

    }
    static class Node
    {
        int vertex;
        long weight;

        public Node(int vertex, long weight)
        {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    static class Edge
    {
        int source, dest;
        long weight;

        public Edge(int source, int dest, long weight)
        {
            this.source = source;
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