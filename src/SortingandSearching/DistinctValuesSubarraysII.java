package SortingandSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DistinctValuesSubarraysII {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int k = fs.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = fs.nextInt();
        }

       int l= 0;
        Map<Integer, Integer> freq = new HashMap<>();
        long ans = 0;
       for(int r=0;r<n;r++){
           freq.put(arr[r], freq.getOrDefault(arr[r], 0) + 1);
           while (freq.size()>k){
               freq.put(arr[l],freq.get(arr[l])-1);
               if(freq.get(arr[l])==0){
                   freq.remove(arr[l]);
               }
               l++;
           }
           ans = ans+(r-l+1);
       }
       System.out.println(ans);

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
