package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MessageRoute {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       List<Edge> edges = new ArrayList<>();
       for(int i=0;i<m;i++){
           edges.add(new Edge(fs.nextInt()-1,fs.nextInt()-1));
       }
       Graph graph = new Graph(edges,n);
       StringBuilder ans = new StringBuilder();
       boolean poss = bfs(graph,0,n-1,ans,n);
       if(poss){
           System.out.println(ans.toString());
           return;
       }
        System.out.println("IMPOSSIBLE");
    }

    static boolean bfs(Graph graph, int src, int dest,  StringBuilder ans,int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        q.add(src);
        visited[src] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == dest) break;

            for (int v : graph.adjList.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

        if (!visited[dest]) return false;

        // reconstruct path
        List<Integer> path = new ArrayList<>();
        for (int v = dest; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        ans.append(path.size()).append("\n");
        for (int x : path) ans.append(x + 1).append(" ");

        return true;
    }

/*
bfsTraversal bad idea to store string like this , will cause TLE
Every BFS step:
creates a new String

copies old string (O(length))
BFS can push O(n) states
Worst case complexity becomes O(n^2)
 */
    private static boolean bfsTraversal(Graph graph, int src, int dest,StringBuilder ans,int n) {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[] discovered = new boolean[n];
        queue.add(new Pair(src,String.valueOf(src+1),1));
        int cnt = 0;
        while (!queue.isEmpty()){
            Pair p = queue.poll();
            int node = p.node;
            discovered[node] = true;
            if(node == dest){
                ans.append(p.cnt).append("\n");
                ans.append(p.parent);
                return true;
            }
            for(int x: graph.adjList.get(node)){
                if(!discovered[x]){
                  queue.add(new Pair(x,p.parent +" "+ String.valueOf(x+1),p.cnt+1));
                }
            }
        }
        return false;
    }


    static class Pair {
        int node;
        String parent; // or int move / dir
        int cnt;

        Pair(int node, String parent,int cnt) {
            this.node = node;
            this.parent = parent;
            this.cnt = cnt;
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
    static class Graph
    {
        List<List<Integer>> adjList = null;
        Graph(List<Edge> edges, int n)
        {
            adjList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (Edge edge: edges)
            {
                int src = edge.source;
                int dest = edge.dest;

                adjList.get(src).add(dest);
                adjList.get(dest).add(src);
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
