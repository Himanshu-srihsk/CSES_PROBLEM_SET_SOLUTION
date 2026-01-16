package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HighScore {
    static List<Edge>[] graph;
    static List<Edge>[] graphR;
    static  List<Edge> edges;
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n= fs.nextInt();
      int m = fs.nextInt();
        graph = new ArrayList[n+1];
        graphR = new ArrayList[n+1];
       edges = new ArrayList<>();
        /*
        Negative the graph
        use Bellman ford to find the min distance
         */
      for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) graphR[i] = new ArrayList<>();
      while (m-- >0){
          int u = fs.nextInt();
          int v = fs.nextInt();
          long d = fs.nextLong();
          edges.add(new Edge(u,v,-1 * d));
          graph[u].add(new Edge(u,v,-1 * d));
          graphR[v].add(new Edge(v,u,-1*d));
      }

        bellmanFord(1,n,n);



    }
    static void bellmanFord(int src,int dest,int n){
        long[] dist = new long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[src] = 0;
       // relaxation step (run V-1 times)
        for(int i=0;i<n-1;i++){
            for(Edge e: edges){
                int u = e.source;
                int v = e.dest;
                long d = e.weight;
                if(dist[u]!= Long.MAX_VALUE && dist[u]+d < dist[v]){
                    dist[v] = dist[u]+d;
                }
            }
        }
        boolean[] from1 = bfsReach(graph, 1, n);
        boolean[] toN   = bfsReach(graphR, n, n);

        for(Edge e : edges){
            /*
            "Bellman-Ford ke (n-th) step me bhi improvement ho raha hai"
              iska matlab negative cycle exists AND it is reachable from src (1)
              BUT it does NOT mean that negative cycle will affect the answer from 1 to n.
             */
            if(dist[src]!=Long.MAX_VALUE && dist[e.source]+ e.weight < dist[e.dest]){
                /*
                1 se n tak jaate jaate ek aisa cycle use kar sakte ho jisme score badha sakte hain infinitely

                Matlab cycle must be usable in a valid route:

                1 -> (cycle) -> n

                So cycle must satisfy both:

                reachable from 1

                from that cycle you can still reach n
                Ex:

                1 -> 2 -> 3 -> 2   (cycle)

                1 can reach n

                n reachable from 1

                1 -> 2 (cycle start)
                2 -> 3
                3 -> 2   (positive cycle)
                3 -> 4   (n=4)
                Here cycle use karke:
                1 -> 2 -> 3 -> 2 -> 3 -> 2 ... and then -> 4

                So score infinite
                output -1

                Cycle exists and reachable from 1
                BUT cycle wala component n tak nahi ja sakta


                Ex 2:
                1 -> 2 -> 3 -> 2   (cycle)
                1 -> 4             (n=4)

                Here:

                cycle reachable from 1

                BUT cycle se n (4) reachable nahi

                So 1 to 4 maximum score finite (just path 1->4)
                output NOT -1


                Bellman-Ford detects negative cycle reachable from 1,
                    but we print -1 only if that cycle can also reach n.

                 */
                if(from1[e.dest] && toN[e.dest]){
                    System.out.println(-1);
                    return;
                }

            }
        }
        System.out.println(-1 * dist[dest]);

    }
    static boolean[] bfsReach(List<Edge>[] graph, int start, int n){
        boolean[] vis = new boolean[n+1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        vis[start] = true;

        while(!q.isEmpty()){
            int x = q.poll();
            for(Edge e: graph[x]){
                int y = e.dest;
                if(!vis[y]){
                    vis[y] = true;
                    q.add(y);
                }
            }
        }
        return vis;
    }


    private static void findShortestDistance(int src, int dest, int n) {
        /*
        one pass bellman work for DAG only but here since graph is directed and cycle may exist so it won't work need to use bellamn with n-1 relaxation steps
         */
        int[] departure = new int[n+1];
        Arrays.fill(departure,-1);
        boolean[] discovered = new boolean[n+1];
        int time =0;
        for(int i=1;i<=n;i++){
            if(!discovered[i]){
                time = dfs(graph,i,discovered,departure,time);
            }
        }

        long[] cost = new long[n+1];
        Arrays.fill(cost, Long.MAX_VALUE);

        cost[src] = 0;
        for(int i=n;i>=1;i--){
            int v = departure[i];
            //relax this vertex first as toplogical order
            for(Edge e: graphR[v]){
                int u = e.dest;
                long w = e.weight;
                if(cost[v]!=Long.MAX_VALUE && cost[v]+w < cost[u]){
                    cost[u] = cost[v]+w;
                }
            }
        }
        for(Edge e : edges){
            if(cost[e.source]+ e.weight < cost[e.dest]){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(-1 * cost[dest]);
    }
    static int dfs(List<Edge> []graph, int i, boolean[] discovered ,int[] departure,int time ){
        discovered[i] = true;
        for(Edge edge: graph[i]){
            int  u = edge.dest;
            if(!discovered[u]){
                time = dfs(graph,u,discovered,departure,time);
            }
        }
        departure[time] = i;
        time++;
        return time;
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
