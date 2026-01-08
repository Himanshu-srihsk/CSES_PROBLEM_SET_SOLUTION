package RangeQueries;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SalaryQueries {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int q = fs.nextInt();
       int[] salary = new int[n];
        List<Integer> allValues = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            salary[i] = fs.nextInt();
            allValues.add(salary[i]);
        }
        /*
        Fenwick tree size fixed hota hai

        Compression fixed mapping hota hai

        Future values ko pehle hi include karna padta hai

        Isliye queries pehle read karte hain
         */

        Query[] queries = new Query[q];

        for(int i=0;i<q;i++){
            char type = fs.nextChar();
            if (type== '!') {

                int k = fs.nextInt() - 1;
                int x = fs.nextInt();
                queries[i] = new Query(type, k, x, 0);
                allValues.add(x);                 // include update value
            } else {
                int a = fs.nextInt();
                int b = fs.nextInt();
                queries[i] = new Query(type, 0, a, b);
                allValues.add(a);                 // include query bounds
                allValues.add(b);
            }
        }


        Collections.sort(allValues);
        Map<Integer, Integer> comp = new HashMap<>();

        //Coordinate compression = OFFLINE step
        //Isliye saari queries pehle padhi jaati hain


        List<Integer> unique = new ArrayList<>();
        for (int val : allValues) {
            if (unique.isEmpty() || unique.get(unique.size() - 1) != val) {
                unique.add(val);
            }
        }

        int idx = 1;
        for (int val : unique) {
            comp.put(val, idx++);
        }




        FenwickTree fw = new FenwickTree(unique.size());

        // insert initial salaries
        for(int i=0;i<n;i++){
            fw.update(comp.get(salary[i]),1);
        }

        StringBuilder out = new StringBuilder();

        for(Query query : queries){
            if (query.type== '!') {

                int emp = query.k;
               int newSal = query.a;
               int oldSal = comp.get(salary[emp]);
               fw.update(oldSal,-1);
               fw.update(comp.get(newSal),1);
               salary[emp] = newSal;
            }else{
               int a = query.a;
               int b = query.b;
               int l = lowerBound(unique,a);
               int r = upperBound(unique,b);

               int ans = fw.rangeSum(l,r);

               out.append(ans).append("\n");

            }
        }
        System.out.println(out.toString());
    }
    // first index where value >= x (compressed index)
    static int lowerBound(List<Integer> arr, int x) {
        int l = 0, r = arr.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (arr.get(m) < x) l = m + 1;
            else r = m;
        }
        return l + 1; // fewncick is i indexed
    }

    // last index where value <= x (compressed index)
    static int upperBound(List<Integer> arr, int x) {
        int l = 0, r = arr.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (arr.get(m) <= x) l = m + 1;
            else r = m;
        }
        return l; // already 1-indexed
    }
    static class Query {
        char type;
        int k, a, b;

        Query(char type, int k, int a, int b) {
            this.type = type;
            this.k = k;
            this.a = a;
            this.b = b;
        }
    }
    static class FenwickTree{
        int n;
        int[] bit;
        FenwickTree(int n){
            this.n = n;
            bit = new int[n+1];
        }
        void update(int index,int delta){
            while (index<=n){
                bit[index]+=delta;
                index = index + (index & -index);
            }
        }

        int sum(int index){
            int sum = 0;
            while (index>0){
                sum+=bit[index];
                index = index - (index&-index);
            }
            return sum;
        }
        int rangeSum(int l, int r) {
            if (l > r) return 0;
            return sum(r) - sum(l - 1);
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

        char nextChar() throws IOException {
            int c;
            do c = read(); while (c <= ' ');
            return (char) c;
        }
    }
}
