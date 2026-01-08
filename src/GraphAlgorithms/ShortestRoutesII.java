package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class ShortestRoutesII {
    static long[][] adjMatrix;
    static long[][] cost;
    static long I = Long.MAX_VALUE;
    static int n;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       /*
       Floyd Warshall algorithm
        */
         n = fs.nextInt();
        int m = fs.nextInt();
        int q = fs.nextInt();
        adjMatrix = new long[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(adjMatrix[i],I);
        }
        for(int i=0;i<m;i++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            int d = fs.nextInt();
            adjMatrix[a][b] = Math.min(adjMatrix[a][b],d);
            adjMatrix[b][a] = Math.min(adjMatrix[b][a],d);
        }
        floydWarshall();
        StringBuilder out = new StringBuilder();
        while (q-- >0){
            long ans = cost[fs.nextInt()][fs.nextInt()];
            if(ans==I){
                out.append(-1).append("\n");
            }else{
                out.append(ans).append("\n");
            }
        }
        System.out.println(out.toString());

    }

    private static void floydWarshall() {
        if (adjMatrix ==null || adjMatrix.length == 0) {
            return;
        }
        cost = new long[n+1][n+1];

        for(int v=0;v<=n;v++){
            for(int u=0;u<=n;u++){
                if(v==u){
                    cost[v][u] = 0;
                }else{
                    cost[v][u] = adjMatrix[v][u];
                }
            }
        }

        for(int k=1;k<=n;k++){
            for(int v=1;v<=n;v++){
                for(int u = 1;u<=n;u++){
                    if(cost[v][k]!=I && cost[k][u]!=I && (cost[v][k]+cost[k][u] < cost[v][u])){
                        cost[v][u] = cost[v][k]+cost[k][u];
                    }
                }
                if(cost[v][v]<0){
                    //negative weight cycle
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
