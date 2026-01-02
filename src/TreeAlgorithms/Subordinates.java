package TreeAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subordinates {
    static  List<Integer>[] tree;
    static int[] subordinate;
    public static void main(String[] args) throws IOException {
         FastScanner fs = new FastScanner(System.in);
         int n = fs.nextInt();

//        tree = new ArrayList[n];
//        for (int i = 0; i < n; i++) {
//            tree[i] = new ArrayList<>();
//        }
//
//        for(int i=1;i<n;i++){
//            int boss = fs.nextInt()-1;
//            tree[boss].add(i);
//        }
//
//        subordinate = new int[n];
//        dfs(0);
//
//        StringBuilder ans = new StringBuilder();
//        for(int i=0;i<n;i++){
//            ans.append(subordinate[i]-1).append(" ");
//        }
        int[] boss = new int[n];
        int[] childCount = new int[n];
        int[] sub = new int[n];
        boss[0] = -1; // CEO
        for (int i = 1; i < n; i++) {
            boss[i] = fs.nextInt() - 1;
            childCount[boss[i]]++;
        }

        // queue of leaf nodes
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (childCount[i] == 0) {
                q.add(i);
            }
        }

        // process bottom-up
        while (!q.isEmpty()) {
            int u = q.poll();
            int p = boss[u];
            if (p != -1) {
                sub[p] += sub[u] + 1;
                childCount[p]--;
                if (childCount[p] == 0) {
                    q.add(p);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int i=0;i<n;i++){
            ans.append(sub[i]).append(" ");
        }

        System.out.println(ans.toString());

    }
    static void dfs(int ind){
        subordinate[ind] = 1;
        for(int x: tree[ind]){
            dfs(x);
            subordinate[ind]+=subordinate[x];
        }
    }
    static void traverse(int[] sub,int[] arr,int n,int i){
        if(arr[i] == i+1){
            return;
        }
        int x = arr[i]-1;
        traverse(sub,arr,n,arr[i]-1);
        sub[x]++;
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
