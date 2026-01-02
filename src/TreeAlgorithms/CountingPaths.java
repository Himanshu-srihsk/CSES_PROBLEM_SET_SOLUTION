

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CountingPaths {
    static int n, m;
    static List<Integer>[] tree;
    static int[] depth;
    static int ancestor[][];
    static int[] parent;
    static long[] cnt;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       n = fs.nextInt();
       m = fs.nextInt();
        ancestor = new int[200001][21]; // 2power of 20 is approx 200001
        for(int i=0;i<200001;i++){
            Arrays.fill(ancestor[i],-1);
        }
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        depth = new int[n + 1];
        cnt = new long[n+1];
        parent = new int[n+1];

        bfs(1);

        /*Binary Liftting

         */
        for(int i=1;i<=n;i++){
            ancestor[i][0] = parent[i];//immediate parent
        }

        ancestor[1][0] = -1; //1 is root node and its ancstor is -1;


        for(int i=1;i<=20;i++){
            for(int j=1;j<=n;j++){
                if(ancestor[j][i-1]!=-1){
                    ancestor[j][i] = ancestor[ancestor[j][i-1]][i-1];
                }
            }
        }

       /*
       Difference Array Technique
        */

        while (m-- > 0) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int lca = findLca(a,b);

            cnt[a]++;
            cnt[b]++;
            cnt[lca]--;

            if(ancestor[lca][0]!=-1){
                cnt[ancestor[lca][0]]--;
            }

        }

        dfsCount(1,-1);
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=n;i++){
            ans.append(cnt[i]).append(" ");
        }
        System.out.println(ans.toString());
    }

    private static void dfsCount(int node, int parent) {
        for(int child: tree[node]){
            if(child!=parent){
                dfsCount(child,node);
                cnt[node]+=cnt[child];
            }
        }
    }

    private static int findLca(int a,int b){
        int da = depth[a];
        int db = depth[b];
        if(da<db){
            int temp = a;
            a=b;
            b = temp;
        }
        int diffOfDepth = depth[a] - depth[b];
        //Binary uplifting for a to match to depth of b
        a = uplift(a,diffOfDepth);
        if(a==b){
            return a;
        }
        //other wise lift both together unless they eaches same point
        for(int i=20;i>=0;i--){
            if(ancestor[a][i]!=-1 && ancestor[a][i] != ancestor[b][i]){
                a = ancestor[a][i];
                b = ancestor[b][i];
            }
        }
        return ancestor[a][0];

    }
    private static int uplift(int a, int diffOfDepth) {
        if(diffOfDepth==0){
            return a;
        }
        if(a==-1){
            return -1;
        }
        for(int i=0;i<=20;i++){
            if(a==-1)break;
            if((diffOfDepth & (1<<i))!=0){
                a = ancestor[a][i];
            }
        }
        return a;
    }
    static void bfs(int node){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        depth[node] = 0;
        parent[node] = -1;
        while (!queue.isEmpty()){
            int x = queue.poll();
            for(int child: tree[x]){
                if(child==parent[x]){continue;}
                parent[child] = x;
                depth[child] = depth[x]+1;
                queue.add(child);
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
