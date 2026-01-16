package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CycleFinding {
    static  List<Edge> edges;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        edges = new ArrayList<>();
        while (m-- >0){
            int u = fs.nextInt();
            int v = fs.nextInt();
            long d = fs.nextLong();
            edges.add(new Edge(u,v,d));
        }

        /*
        n-th relaxation must be either inside the cycle or after the cycle

        Why n-th relaxation can't be before the cycle
        Ex: Source Node A ->(Cycle 3-4) -> Node B
        Node A (Before Cycle): This node will stop relaxing after its shortest path from the source is found (usually by iteration 1 or 2).
         It will never relax in the $n$-th iteration because its distance is already optimal.
         Nodes 3 & 4 (In Cycle): These will keep relaxing forever (1st, 2nd, ... 100th iteration)
         because the cost keeps dropping.Node B (After Cycle): Since node B depends on node 4, every time node 4's cost drops, Node B will also relax.
         */
        bellmanFord(n);
    }
    static void bellmanFord(int n){
        long[] dist = new long[n+1];

//        Arrays.fill(dist,Long.MAX_VALUE);
//        dist[1] = 0;
       Arrays.fill(dist, 0);
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);
        int negativeCycleNode = -1;
        for(int i=1;i<=n;i++){
            negativeCycleNode=-1;
            for(Edge e: edges){
                int u = e.source;
                int v = e.dest;
                long d = e.weight;
                if( dist[u]+d < dist[v]){
                    negativeCycleNode = v;
                    dist[v] = dist[u]+d;
                    parent[v] = u;
                }
            }
        }

        if(negativeCycleNode==-1){
            System.out.println("NO");
            return;
        }
        StringBuilder out = new StringBuilder();
        out.append("YES").append("\n");
        /*
        Traverse n step back to get proper node that is actually part iof negative cycle
         */
        for(int i=1;i<=n;i++){
            negativeCycleNode = parent[negativeCycleNode];
        }
        List<Integer> partOfCycle = new ArrayList<>();

        int temp = negativeCycleNode;
        while (true){
            partOfCycle.add(negativeCycleNode);
            negativeCycleNode = parent[negativeCycleNode];
            if(negativeCycleNode == temp){
                break;
            }
        }
        partOfCycle.add(temp);
        Collections.reverse(partOfCycle);
        for(int node: partOfCycle){
            out.append(node).append(" ");
        }
        System.out.println(out.toString());

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
