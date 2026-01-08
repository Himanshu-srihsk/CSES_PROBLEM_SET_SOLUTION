package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BuildingRoads {
    static List<Integer>[] graph;
    static boolean[] visited;
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
        List<Integer> reps = new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                reps.add(i);
                bfs(i,n);
            }
        }
        StringBuilder ans = new StringBuilder();
        if(reps.size()>1){
            ans.append(reps.size()-1).append("\n");
            int k = reps.size();
            for(int i=0;i<k-1;i++){
                ans.append(reps.get(i)+" "+reps.get(i+1)).append("\n");
            }
        }else{
            ans.append(0);
        }
        System.out.println(ans.toString());
    }
    static void bfs(int k,int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        visited[k] = true;
        while (!queue.isEmpty()){
            int curr = queue.poll();
            for (int d : graph[curr]) {
                if (!visited[d]) {
                    visited[d] = true;
                    queue.add(d);
                }
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
