

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanetsQueriesI {
    static int ancestor[][];
    static List<Integer>[] graph;
    static int n;
    static final int LOG = 30;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       n = fs.nextInt();
       int q = fs.nextInt();
        ancestor = new int[n+1][LOG+1]; // 2power of 20 is approx 200001

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int u = i+1;
            int v = fs.nextInt();
            graph[u].add(v);
            ancestor[u][0] = v;
        }

        preprocess();
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int x = fs.nextInt();
            int k = fs.nextInt();
            int ans = jump(x, k);
            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());

    }

    private static int jump(int x, int k) {
        for(int i=LOG;i>=0;i--){
            if(((k>>i)&1)!=0){
                x = ancestor[x][i];
            }
        }
        return x;
    }

    private static void preprocess() {
        for(int i=1;i<=LOG;i++){
            for(int j=1;j<=n;j++){
                if(ancestor[j][i-1]!=-1){
                    ancestor[j][i] = ancestor[ancestor[j][i-1]][i-1];  // i.e 2^k == 2^k-1 * 2^k-1 = 2*2^k-1 = 2^k
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
