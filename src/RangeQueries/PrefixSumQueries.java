

import java.io.IOException;
import java.io.InputStream;

public class PrefixSumQueries {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int q = fs.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextLong();
        }
        long[] prefix = new long[n+1];
        prefix[0] = 0;
        for(int i=1;i<=n;i++){
            prefix[i] = prefix[i-1]+arr[i-1];
        }

        SegmentTree tree = new SegmentTree(arr);
        StringBuilder ans = new StringBuilder();

        while (q-- >0){
            int type = fs.nextInt();
            int a = fs.nextInt();
            int b= fs.nextInt();
            if(type==1){
                tree.updateTree(arr,1,0,n-1,a-1,b);
            }else{
                Node x = tree.queryTree(1,0,n-1,a-1,b-1);

                ans.append(x.prefix).append("\n");
            }


        }
        System.out.println(ans.toString());

    }
    static class Node{
        long prefix;
        long sum;
        Node(){
            this.prefix = 0;
            this.sum = 0;
        }
        Node(long prefix,long sum){
            this.prefix = prefix;
            this.sum = sum;
        }
    }
    static class SegmentTree{
        Node[] ST;
        int n;
        SegmentTree(long[] arr){
            this.n = arr.length;
            ST = new Node[4*n+1];
            buildTree(arr,1,0,n-1);
        }

        private void updateTree(long arr[], int idx,int start,int end,int updateIdx,int val){
            if(start==end){
                ST[idx].prefix = Math.max(0,val);
                ST[idx].sum = val;

                arr[updateIdx] = val;
                return;
            }
            int mid = (start +(end-start)/2);
            if(updateIdx<=mid){
                updateTree(arr,2*idx,start,mid,updateIdx,val);
            }else {
                updateTree(arr,2*idx+1,mid+1,end,updateIdx,val);
            }
           mergeResult(idx);
        }

        private void buildTree(long[] arr,int idx,int start,int end) {
            ST[idx] = new Node();
            if(start==end){
                ST[idx] = new Node(Math.max(0,arr[start]),arr[start]);
                return;
            }
            int mid = (start +(end-start)/2);
            buildTree(arr,2*idx,start,mid);
            buildTree(arr,2*idx+1,mid+1,end);
            mergeResult(idx);
        }

        private void mergeResult(int idx) {
            ST[idx].sum = ST[2*idx].sum + ST[2*idx+1].sum;
            ST[idx].prefix = Math.max(ST[2*idx].prefix, ST[2*idx].sum+ ST[2*idx+1].prefix);
        }

        private Node queryTree(int idx,int start,int end,int qs,int qe){
            if(qe<start || end<qs){
                return new Node(0,0);
            }
            if(qs<=start && end<=qe){
                return ST[idx];
            }
            int mid = (start +(end-start)/2);

            Node l  = queryTree(2*idx, start, mid, qs, qe);
            Node r  = queryTree(2*idx+1, mid+1, end, qs, qe);

            Node res = new Node();
            res.prefix = Math.max(l.prefix, l.sum+r.prefix);
            res.sum = l.sum + r.sum;
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
