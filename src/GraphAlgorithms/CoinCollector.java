package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CoinCollector {
    static List<Integer>[] graph,graphR;
    static Stack<Integer> stack = new Stack<>();
    static List<Integer>[] dag;
    /*
    SCC + Topological Sorting + Condensed Graph
     */
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        long[] coins = new long[n+1];
        for(int i=1;i<=n;i++) coins[i] = fs.nextLong();
        graph = new ArrayList[n + 1];
        graphR = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graphR[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            graph[u].add(v);
           graphR[v].add(u);
        }

        boolean[] visited = new boolean[n+1];


        for(int i=0;i<n;i++){
            if (!visited[i+1]) {

                dfs(graph,i+1,visited);
            }
        }

        Arrays.fill(visited,false);
        int numSCC = 0;
        int[] sccAssign = new int[n + 1];
        while (!stack.isEmpty()){
            int i = stack.pop();
            if (!visited[i]) {
                numSCC++;
                dfs(graphR,i,visited,numSCC,sccAssign);
            }
        }

        long[] compCoins = new long[numSCC+1];
        for(int i=1;i<=n;i++){
            compCoins[sccAssign[i]] += coins[i];
        }

        //Building DAG
        dag = new ArrayList[numSCC+1];
        for(int i=0;i<=numSCC;i++){
            dag[i] = new ArrayList<>();
        }
        for(int i=1;i<=n;i++){
            for(int j : graph[i]){
                if(sccAssign[i]!=sccAssign[j]){
                    dag[sccAssign[i]].add(sccAssign[j]);
                }
            }
        }

        // DP on DAG
        long[] dp = new long[numSCC+1];
        for(int i=1;i<=numSCC;i++){
            dp[i] = compCoins[i];
        }
        List<Integer> topo = topoSort(numSCC);

        for(int u: topo){
            for(int v: dag[u]){
                dp[v] = Math.max(dp[v], dp[u]+ compCoins[v]);
            }
        }
        long ans = 0;
        for(long x: dp) ans = Math.max(ans, x);

        System.out.println(ans);


    }
//Topological sort on DAG
    static List<Integer> topoSort(int n){

        int[] indeg = new int[n+1];

        for(int u=1;u<=n;u++){
            for(int v: dag[u]) indeg[v]++;
        }

        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1;i<=n;i++){
            if(indeg[i]==0) q.add(i);
        }

        List<Integer> res = new ArrayList<>();

        while(!q.isEmpty()){
            int u = q.poll();
            res.add(u);

            for(int v: dag[u]){
                if(--indeg[v]==0) q.add(v);
            }
        }

        return res;
    }

    static void dfs(List<Integer> [] graph, int src, boolean[] visited){
        visited[src] = true;

        for(int e: graph[src]){
            if(!visited[e]){
                dfs(graph,e,visited);
            }
        }
        stack.push(src);
    }
    static void dfs(List<Integer> [] graph, int src, boolean[] visited,int id,int[] sccAssign){
        visited[src] = true;
        sccAssign[src] = id;
        for(int e: graph[src]){
            if(!visited[e]){
                dfs(graph,e,visited,id,sccAssign);
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
