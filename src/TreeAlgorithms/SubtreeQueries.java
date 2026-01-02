
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SubtreeQueries {
    static int n, q;
    static List<Integer>[] tree;
    static long[] value;
    static int[] tin, tout;
    static int timer;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);

        n = fs.nextInt();
        q = fs.nextInt();

        value = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            value[i] = fs.nextLong();
        }

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        /*
        Eulers Tour
        we need to get the in time and out time for every node
         */

        tin = new int[n + 1];
        tout = new int[n + 1];
        // Euler Tour (iterative DFS)
       // buildEulerTour(1,-1);
        buildEulerTourIterative(1);

        FenwickTree fenwickTree = new FenwickTree(timer+1);
        for (int i = 1; i <= n; i++) {
            fenwickTree.update(tin[i], value[i]);
        }

        StringBuilder ans = new StringBuilder();
        while (q-->0){
            int type = fs.nextInt();
            if(type==1){
                int u = fs.nextInt();
                long x = fs.nextLong();
                long delta = x - value[u];
                value[u] = x;
                fenwickTree.update(tin[u],delta);
            }else{
                int u = fs.nextInt();
                int entryTime = tin[u];
                int exitTime = tout[u];
                long res = fenwickTree.sum(exitTime) - fenwickTree.sum(entryTime-1);
                ans.append(res).append("\n");
            }
        }
        System.out.println(ans.toString());


    }

    private static void buildEulerTour(int root,int parent) {
        tin[root] = ++timer;
        for(int child : tree[root]){
            if(child!=parent){
                buildEulerTour(child,root);
            }
        }
        tout[root] = timer;
    }

    static void buildEulerTourIterative(int root) {
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = -1;

        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (u > 0) {
                tin[u] = ++timer;
                stack.push(-u);   // marker for exit
                for (int v : tree[u]) {
                    if (v != parent[u]) {
                        parent[v] = u;
                        stack.push(v);
                    }
                }
            } else {
                u = -u;
                tout[u] = timer;
            }
        }
    }




    static class FenwickTree{
        int n;
        long[] bit;
        FenwickTree(int n){
            this.n = n;
            bit = new long[n+1];
        }
        void update(int idx,long delta){
            while (idx<=n){
                bit[idx]+=delta;
                idx = idx + (idx & -idx);
            }
        }
        long sum(int idx){
            long res = 0;
            while (idx>0){
               res+=bit[idx];
               idx = idx - (idx & -idx);
            }
            return res;
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
