package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestFlightRoute {
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
        graph = new ArrayList[n+1];
       /*
         we can use one pass Bellamn Ford as it is DAG. for weight for each edge can be considered 1
        */
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        while (m-- >0){
            int u = fs.nextInt();
            int v = fs.nextInt();
            long d = 1l;
            graph[u].add(new Edge(u,v,d));
        }
        findShortestDistance(1,n,n);
    }
    private static void findShortestDistance(int src, int dest, int n) {
        /*
        one pass bellman work for DAG only but here since graph is directed and cycle may exist so it won't work need to use bellamn with n-1 relaxation steps
         */
        int[] departure = new int[n];
        Arrays.fill(departure,-1);
        boolean[] discovered = new boolean[n+1];
        int time =0;
        for(int i=0;i<n;i++){
            if(!discovered[i+1]){
                time = dfs(i+1,discovered,departure,time);
            }
        }

        long[] cost = new long[n+1];
        Arrays.fill(cost, Long.MAX_VALUE);

        int[] parent = new int[n+1];
        Arrays.fill(parent,-1);

//        //System.out.println(Arrays.toString(departure));
//
//        cost[src] = 0;
//        for(int i=n;i>=1;i--){
//            int v = departure[i-1];
//            //relax this vertex first as toplogical order
//
//            for(Edge e: graph[v]){
//                int u = e.dest;
//                long w = -1 * e.weight;
//                if(cost[v]!=Long.MAX_VALUE && cost[v]+w < cost[u]){
//                    cost[u] = cost[v]+w;
//                    parent[u] = v;
//                }
//            }
//        }

        long [] dp = new long[n+1];
        Arrays.fill(dp, -1);
        dp[1] = 1;

        for(int i=n;i>=1;i--){
            int u = departure[i-1];
            for(Edge e: graph[u]){
                if(dp[u] != -1 && dp[e.dest] < dp[u]+1){
                    dp[e.dest] = Math.max(dp[e.dest],dp[u]+1);
                    parent[e.dest] = u;
                }

            }
        }

        if(dp[dest]==-1){
            System.out.println("IMPOSSIBLE");
            return;
        }


//        if (cost[dest] == Long.MAX_VALUE) {
//            System.out.println("IMPOSSIBLE");
//            return;
//        }

        StringBuilder out = new StringBuilder();
        List<Integer> ans = new ArrayList<>();

        int temp = dest;
        while (temp!=src){
            ans.add(temp);
            temp = parent[temp];
        }
        ans.add(src);
        Collections.reverse(ans);
        out.append(ans.size()).append("\n");
        for(int v: ans){
            out.append(v).append(" ");
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
    static int dfs(int i, boolean[] discovered , int[] departure, int time ){
        discovered[i] = true;
        for(Edge edge: graph[i]){
            int  u = edge.dest;
            if(!discovered[u]){
                time = dfs(u,discovered,departure,time);
            }
        }
        departure[time] = i;
        time++;
        return time;
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
