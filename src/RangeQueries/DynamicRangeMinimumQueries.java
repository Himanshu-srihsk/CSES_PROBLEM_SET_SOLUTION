package RangeQueries;

import java.io.IOException;
import java.io.InputStream;

public class DynamicRangeMinimumQueries {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       /*
       Sparse Tree
        */

        int n = fs.nextInt();
        int q = fs.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextLong();
        }

        SegmentTree tree = new SegmentTree(arr);
        StringBuilder ans = new StringBuilder();
        while (q-- >0){
            int type = fs.nextInt();
            int a = fs.nextInt();
            int b= fs.nextInt();
            if(type==1){
                tree.updateTree(arr,1,0,n-1,a-1,b);
            }else {
                ans.append(tree.queryTree(1,0,n-1,a-1,b-1)).append("\n");
            }


        }
        System.out.println(ans.toString());
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
        private void updateTree(long arr[], int idx,int start,int end,int updateIdx,int val){
            if(start==end){
                ST[idx] = val;
                arr[updateIdx] = val;
                return;
            }
            int mid = (start +(end-start)/2);
            if(updateIdx<=mid){
                updateTree(arr,2*idx,start,mid,updateIdx,val);
            }else {
                updateTree(arr,2*idx+1,mid+1,end,updateIdx,val);
            }
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
