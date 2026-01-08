package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BuildingTeams {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] team;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        visited = new boolean[n + 1];
        team = new int[n+1];
        /*
        check if every componets is Bipartite graph or not. i.e no odd cycle

         */

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                if(!checkIfBipartite(i,n)){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        StringBuilder out = new StringBuilder();
        for(int i=1;i<=n;i++){
            out.append(team[i]).append(" ");
        }
        System.out.println(out.toString());

    }

    private static boolean checkIfBipartite(int i, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        team[i] = 1;
        visited[i] = true;
        queue.add(i);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for(int u: graph[v]){
                if(!visited[u]){
                    visited[u] = true;
                    team[u] = team[v]==1?2:1;
                    queue.add(u);
                }else if(team[v]==team[u]){
                    return false;
                }
            }
        }
        return true;
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
