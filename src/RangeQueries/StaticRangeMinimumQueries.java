package RangeQueries;

import java.io.IOException;
import java.io.InputStream;

public class StaticRangeMinimumQueries {
    static int[] log2;

    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int q = fs.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextLong();
        }

//        SegmentTree tree = new SegmentTree(arr);
//        StringBuilder ans = new StringBuilder();
//        while (q-- >0){
//            int a = fs.nextInt();
//            int b= fs.nextInt();
//            ans.append(tree.queryTree(1,0,n-1,a-1,b-1)).append("\n");
//        }
//        System.out.println(ans.toString());

        log2 = new int[n + 1];
        log2[1] = 0;
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        SparseTree sparseTree = new SparseTree(arr);
        StringBuilder ans = new StringBuilder();
        while (q-- >0){
            int a = fs.nextInt();
            int b= fs.nextInt();
            if(a>b){
                int temp = a;
                a=b;
                b = temp;
            }
            ans.append(sparseTree.query(a-1,b-1)).append("\n");
        }
        System.out.println(ans.toString());

    }
    static class SparseTree{
        long[][] parser;

        int n;
        public SparseTree(long[] arr){
          this.n = arr.length;
            int maxCol = log2[n] + 1;

            parser = new long[n][maxCol];
            buildSparseTable(arr);

        }

        private void buildSparseTable(long[] arr) {
            for(int i=0;i<n;i++){
                parser[i][0] = arr[i];
            }
            for(int i=1;(1<<i)<=n;i++){
                for(int j=0;(j+(1<<i)-1)<n;j++){
                     parser[j][i] = Math.min(parser[j][i-1],parser[j+(1<<(i-1))][i-1]);
                }
            }
        }

        long query(int qs,int qe){
            int len = qe - qs + 1;
            int j = log2[len];
            return Math.min(parser[qs][j],parser[qe-(1<<j)+1][j]);
        }


    }
    static class SegmentTree{
        long[] ST;
        int n;
        SegmentTree(long[] arr){
            this.n = arr.length;
            ST = new long[4*n+1];
            buildTree(arr,1,0,n-1);
        }

        private void buildTree(long[] arr,int idx,int start,int end) {
            if(start==end){
                ST[idx] = arr[start];
                return;
            }
            int mid = (start +(end-start)/2);
            buildTree(arr,2*idx,start,mid);
            buildTree(arr,2*idx+1,mid+1,end);
            ST[idx] = Math.min(ST[2*idx],ST[2*idx+1]);
        }
        private long queryTree(int idx,int start,int end,int qs,int qe){
            if(qe<start || end<qs){
                return Integer.MAX_VALUE;
            }
            if(qs<=start && end<=qe){
                return ST[idx];
            }
            int mid = (start +(end-start)/2);
            return Math.min(queryTree(2*idx, start, mid, qs, qe) , queryTree(2*idx+1, mid+1, end, qs, qe));
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
