package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

//https://www.youtube.com/watch?v=lPLhmuWMRag
public class CountingTilings {
    static final int MOD = 1000000007;
    static int[][][] memo;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
        memo = new int[m][n][1 << n];
        for (int[][] colArr : memo) {
            for (int[] rowArr : colArr) {
                Arrays.fill(rowArr, -1);
            }
        }

        System.out.println(solve(0, 0, 0,n,m));

    }
    static int solve(int j, int i, int mask,int n,int m) {
        if(j==m){
            if(mask==0){
                return 1;
            }
            return 0;
        }
        if(i==n){
            // Wrap-around: Move to the next column
            return solve(j+1,0,mask,n,m);
        }
        if(memo[j][i][mask]!=-1){
            return memo[j][i][mask];
        }

        long ans  = 0;
        if(((1<<i)&mask) !=0){
            //Current cell is already filled from the left (mask bit is 1)
            ans = solve(j,i+1,mask ^ (1 << i),n,m);  //// Move to next row, flip the bit to 0 (clearing it for the next column)

        }else{
            // Place a Horizontal domino
            // It will protrude into the next column (set bit to 1)
            ans = (ans + solve(j, i + 1, mask | (1 << i),n,m)) % MOD;

           // Place a Vertical domino
            // Check if the cell below is within bounds and not filled from the left
            if (i + 1 < n && (mask & (1 << (i + 1))) == 0) {
                ans = (ans + solve(j, i + 2, mask,n,m)) % MOD;
            }
        }
        return memo[j][i][mask] = (int) ans;
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
