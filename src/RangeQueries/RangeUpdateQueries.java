

import java.io.IOException;
import java.io.InputStream;

public class RangeUpdateQueries {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       long[] arr = new long[n];
       for(int i=0;i<n;i++){
           arr[i] = fs.nextLong();
       }
       /*
       Segment Tree with Lazy Propagation
        */
       LazyTree  lazyTree = new LazyTree(arr);
       StringBuilder ans = new StringBuilder();
       while (m-- > 0){
           int type = fs.nextInt();
           if(type==1){
               int a = fs.nextInt();
               int b = fs.nextInt();
               int delta = fs.nextInt();
               lazyTree.updateRange(arr,0,0,n-1,a-1,b-1,delta);
           }else{
               int a = fs.nextInt();
               ans.append(lazyTree.getSum(0,0,n-1,a-1,a-1)).append("\n");
           }
       }
       System.out.println(ans.toString());

    }
    static class LazyTree{
        long[] ST;
        long[] lazy;
        int n;
        LazyTree(long[] arr){
           this.n = arr.length;
           ST = new long[4*n+1];
           lazy = new long[4*n+1];
            buildTree(arr,0,0,n-1);
        }
        private void buildTree(long[] arr,int idx,int start,int end){
            if(start==end){
                ST[idx] = arr[start];
                return;
            }

            int mid = start + ((end - start) >> 1);

            buildTree(arr, 2 * idx+1, start, mid);
            buildTree(arr, 2*idx+2, mid+1, end);
            ST[idx] = ST[2*idx+1] + ST[2*idx+2];
        }
        public void updateRange(long[] arr,int idx,int start,int end,int startUpdateIdx,int endUpdateIdx,int val){
            if(lazy[idx]!=0){
                ST[idx] += (end-start+1) * lazy[idx];
                if(start!=end){
                    lazy[2*idx+1] += lazy[idx];
                    lazy[2*idx+2] += lazy[idx];
                }
                lazy[idx] = 0;
             }
            if (start > end || endUpdateIdx < start || startUpdateIdx > end) {
                return;
            }
            if(startUpdateIdx <=start && endUpdateIdx >= end){
                ST[idx] += (end-start+1) * val;
                if(start!=end){
                    lazy[2*idx+1] += val;
                    lazy[2*idx+2] += val;
                }
                return;
            }
            int mid = start + ((end - start) >> 1);

            updateRange(arr,2*idx+1,start,mid,startUpdateIdx,endUpdateIdx,val);
            updateRange(arr,2*idx+2,mid+1,end,startUpdateIdx,endUpdateIdx,val);
            ST[idx] = ST[2 * idx + 1] + ST[2 * idx + 2];
        }
        public long getSum(int idx,int start,int end,int qs,int qe){
            if(lazy[idx]!=0){
                ST[idx] += (end-start+1) * lazy[idx];
                if(start!=end){
                    lazy[2*idx+1] += lazy[idx];
                    lazy[2*idx+2] += lazy[idx];
                }
                lazy[idx] = 0;
            }
            if(start> end || qe< start ||  end < qs){
                return 0;
            }
            if(qs<=start && end<=qe){
                return ST[idx];
            }
            int mid = start + ((end - start) >> 1);


            return getSum(2*idx+1, start, mid, qs, qe) + getSum(2*idx+2, mid+1, end, qs, qe);
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
