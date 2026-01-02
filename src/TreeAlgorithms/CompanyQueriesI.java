package TreeAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyQueriesI {
    static int ancestor[][];
    static List<Integer>[] tree;
    static int n;
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
       n = fs.nextInt();
      int q = fs.nextInt();
      ancestor = new int[200001][21]; // 2power of 20 is approx 200001
        for(int i=0;i<200001;i++){
            Arrays.fill(ancestor[i],-1);
        }
        tree = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=2;i<=n;i++){
            int par = fs.nextInt();
            ancestor[i][0] = par; //immediate parent
            tree[i].add(par);
            tree[par].add(i);
        }

        /*
        Binary Lifting creating parent in power of n
         */


        preprocess(1,-1); // root is 1
        StringBuilder ans = new StringBuilder();
        while (q-->0){
            int a  = fs.nextInt();
            int b = fs.nextInt();
            ans.append(query(a,b)).append("\n");
        }
        System.out.println(ans.toString());

    }

    private static int query(int node, int k) {
        if(k==0){
            return node;
        }
        if(node == -1){
            return -1;
        }
//        for(int i=0;i<=20;i++){
//            if((k&(1<<i))!=0){
//                return query(ancestor[node][i],k-(1<<i));
//            }
//        }
//        return -1;

        for (int i=0;i<=20;i++){
            if(node==-1)break;
            if((k&(1<<i))!=0){
                node = ancestor[node][i];
            }
        }
        return node;
    }

    private static void preprocess(int node, int parent) {
        ancestor[node][0] = parent;
//        for(int i=1;i<=20;i++){
//            if(ancestor[node][i-1]!=-1){
//                ancestor[node][i] = ancestor[ancestor[node][i-1]][i-1];  // i.e 2^k == 2^k-1 * 2^k-1 = 2*2^k-1 = 2^k
//            }
//        }
//        for(int child: tree[node]){
//            if(child!=parent){
//                preprocess(child,node);
//            }
//        }

        for(int i=1;i<=20;i++){
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
