package TreeAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeMatching {
    static List<Integer>[] tree;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        tree = new ArrayList[n];
        for(int i=0;i<n;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            tree[a-1].add(b-1);
            tree[b-1].add(a-1);
        }
        dp = new int[200001][2];
        for(int i=0;i<=200000;i++){
            Arrays.fill(dp[i],-1);
        }

        int ans = Math.max(treeMatching(0,0,-1),treeMatching(0,1,-1));
        System.out.println(ans);

    }

    private static int treeMatching(int vertex, int isIncluded, int parent) {
        if(dp[vertex][isIncluded]!=-1){
            return dp[vertex][isIncluded];
        }

        if(isIncluded==1){
            /*
            dp[root][1] = Max(
            1+ dp[2,0] + dp[3,1]  { when 1-2 is included},
                         1+ dp[2,1] + dp[3,0]  { when 1-3 is included}
                         }

                         ans = dp[2,1] + dp[3,1] + ...
                        1+ dp[2,0] + dp[3,1] is equivalent to  ans - dp[2,1] + dp[2,0]
                         1+ dp[2,1] + dp[3,0]  is equivalent to  ans - dp[3,1] + dp[3,0]

             */
            int ans = 0;
            for(int neighbour: tree[vertex]){
                if(neighbour!=parent){
                    ans+=treeMatching(neighbour,1,vertex);
                }
            }
            int myres = 0;
            for(int neighbour: tree[vertex]){
                if(neighbour!=parent){
                   myres = Math.max(myres, ans - dp[neighbour][1]+1+treeMatching(neighbour,0,vertex));
                }
            }
            dp[vertex][isIncluded] = myres;
            return myres;
        }else{
            int ans = 0;
            for(int neighbour: tree[vertex]){
                 if(neighbour!=parent){
                     ans+=treeMatching(neighbour,1,vertex);
                 }
            }
            dp[vertex][isIncluded] = ans;
            return ans;
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
