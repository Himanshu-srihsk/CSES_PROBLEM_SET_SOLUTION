package RangeQueries;

import java.io.IOException;
import java.io.InputStream;

public class SubarraySumQueries {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        long[] arr = new long[n];

        for(int i=0;i<n;i++){
            arr[i] = fs.nextInt();
        }

        SegmentTree  segmentTree = new SegmentTree(arr);
        StringBuilder ans = new StringBuilder();
        while (m-- > 0){
            int a = fs.nextInt();
            int b = fs.nextInt();

            segmentTree.updateTree(arr,1,0,n-1,a-1,b);
            ans.append(segmentTree.ST[1].maxVal).append("\n");
        }
        System.out.println(ans.toString());
    }
    static class SegInfo{
        long total;
        long maxPrefix;
        long maxSuffix;
        long maxVal;
        SegInfo(){
            this.total = 0;
            this.maxPrefix = 0;
            this.maxSuffix = 0;
            this.maxVal = 0;
        }
        SegInfo(long total,long maxPrefix,long maxSuffix,long maxVal){
            this.total = total;
            this.maxPrefix = maxPrefix;
            this.maxSuffix = maxSuffix;
            this.maxVal = maxVal;
        }
    }
    static class SegmentTree{
        SegInfo[] ST;
        int n;
        SegmentTree(long[] arr){
            this.n = arr.length;
            ST = new SegInfo[4*n+1];
            buildTree(arr,1,0,n-1);
        }
        private void buildTree(long[] arr,int idx,int start,int end) {
            if(start==end){
                long v = Math.max(0,arr[start]);
                ST[idx] = new SegInfo(arr[start],v,v,v);
                return;
            }
            int mid = (start +(end-start)/2);
            buildTree(arr,2*idx,start,mid);
            buildTree(arr,2*idx+1,mid+1,end);
            buildSegInfo(idx);
        }

        private void buildSegInfo(int idx) {
            SegInfo segInfo = new SegInfo();
            SegInfo leftSubtree = ST[2*idx];
            SegInfo rightSubtree = ST[2*idx+1];
            segInfo.maxPrefix = Math.max(leftSubtree.maxPrefix, leftSubtree.total+rightSubtree.maxPrefix);
            segInfo.maxSuffix = Math.max(rightSubtree.maxSuffix,leftSubtree.maxSuffix+rightSubtree.total);
            segInfo.total = leftSubtree.total+rightSubtree.total;
            segInfo.maxVal = Math.max(Math.max(leftSubtree.maxVal,rightSubtree.maxVal), leftSubtree.maxSuffix+rightSubtree.maxPrefix );
            ST[idx] = segInfo;
        }

        private void updateTree(long arr[], int idx,int start,int end,int updateIdx,int val){
            if(start==end){
                arr[updateIdx] = val;
                long v = Math.max(0,val);
                ST[idx] = new SegInfo(val,v,v,v);
                return;
            }
            int mid = (start +(end-start)/2);
            if(updateIdx<=mid){
                updateTree(arr,2*idx,start,mid,updateIdx,val);
            }else {
                updateTree(arr,2*idx+1,mid+1,end,updateIdx,val);
            }
            buildSegInfo(idx);
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
