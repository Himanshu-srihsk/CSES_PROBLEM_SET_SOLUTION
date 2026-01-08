

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CourseSchedule {
    static List<Integer>[] graph;
    static int[] indegree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n = fs.nextInt();
      int m = fs.nextInt();
        graph = new ArrayList[n + 1];
        indegree = new int[n+1];
        Arrays.fill(indegree,0);
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            graph[u].add(v);
            indegree[v]++;
        }
        visited = new boolean[n + 1];
        List<Integer> ans =  doTopologicalSort(n);
        if(ans==null){
            System.out.println("IMPOSSIBLE");
            return;
        }
        StringBuilder out = new StringBuilder();
        for(int x:ans){
            out.append(x).append(" ");
        }
        System.out.println(out.toString());
    }

    private static List<Integer> doTopologicalSort(int n) {
        List<Integer> L = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=1;i<=n;i++){
            if(indegree[i]==0){
                stack.push(i);
            }
        }
        while (!stack.isEmpty()){
            int i = stack.pop();
            L.add(i);
            for(int m: graph[i]){
                indegree[m]--;
                if(indegree[m]==0){
                    stack.push(m);
                }
            }
        }
        for (int i = 0; i < n; i++)
        {
            if (indegree[i] != 0) {
                return null;
            }
        }
        return L;
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
