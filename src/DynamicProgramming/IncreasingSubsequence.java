package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IncreasingSubsequence {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
         FastScanner fs = new FastScanner(System.in);
         int n = fs.nextInt();
         int[] arr = new int[n];
         for(int i=0;i<n;i++){
             arr[i]= fs.nextInt();
         }

        System.out.println(solveLIS(arr));
    }

    private static int solveLIS(int[] nums) {
        if(nums == null || nums.length==0){
            return 0;
        }
        List<Integer> tails = new ArrayList<>();
        for(int x: nums){
            // Binary search to find the lower bound of x in tails
            int low = 0;
            int high = tails.size();
            while (low<high){
                int mid = (low+(high-low)/2);
                if(tails.get(mid) < x){
                    low = mid+1;
                }else{
                    high = mid;
                }
            }
            if(low == tails.size()){
                tails.add(x);
            }else{
                tails.set(low,x);
            }
        }
        return tails.size();
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
