package RangeQueries;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctValuesQueries {
    //Fenwick Tree + last occurrence + offline queries.
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int q = fs.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextLong();
        }
        Query[] queries = new Query[q];
        for(int i=0;i<q;i++){
            queries[i] = new Query(fs.nextInt()-1,fs.nextInt()-1,i);
        }
        Arrays.sort(queries);

        FenwickTree fenwickTree = new FenwickTree(n);
        Map<Long,Integer> lastOccurence = new HashMap<>();
        long[] ans = new long[q];
        int qi = 0;

        for(int i=0;i<n;i++){
            /*
            3
            Fw: 1

            3 2
            Fw: 1 1

            3 2 3
            Fw: 0 1 1

            {here for index 1 we need to update the val by -1 and index 3 we need to update teh val by +1}

            now when i == current query qi.r then process it in while loop for all qi.r == i

            3 2 3 1
            fw: 0 1 1 1

            3 2 3 1 2
            fw: 0 0 1 1 1

             */
            long val = arr[i];
            if(lastOccurence.containsKey(val)){
                fenwickTree.update(lastOccurence.get(val)+1,-1);
            }
            lastOccurence.put(val,i);
            fenwickTree.update(i+1,1);
            while (qi < q && queries[qi].r ==i){
                ans[queries[qi].idx] = fenwickTree.rangeSum(queries[qi].l+1,queries[qi].r+1);
                qi++;
            }


        }
        StringBuilder out = new StringBuilder();
        for(long x: ans) out.append(x).append("\n");
        System.out.print(out);

    }
    static class Query implements Comparable<Query>{
       int l;
       int r;
       int idx;
       Query(int l,int r,int idx){
           this.l = l;
           this.r = r;
           this.idx = idx;
       }

        @Override
        public int compareTo(Query o) {
            return this.r - o.r;
        }
    }
    static class FenwickTree{
        int n;
        long[] bit;
        FenwickTree(int n){
            this.n = n;
            bit = new long[n+1];
        }
        void update(int idx,int delta){
            while (idx<=n){
                bit[idx]+=delta;
                idx = idx+(idx & -idx);
            }
        }
        long sum(int idx){
            long res = 0;
            while (idx>0){
                res += bit[idx];
                idx = idx - (idx & -idx);
            }
            return res;
        }
        long rangeSum(int l,int r){
            return sum(r) - sum(l-1);
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
