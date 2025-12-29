
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class RemovalGame {
    static final int MOD = 1000000007;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
         FastScanner fs = new FastScanner(System.in);
         int n = fs.nextInt();

         int[] arr = new int[n];
         for(int i=0;i<n;i++){
             arr[i] = fs.nextInt();
         }

          dp = new long[n+1][n+1];
//         for(int i=0;i<n;i++){
//             Arrays.fill(dp[i],-1);
//         }

//         System.out.println(findOptimalSol(arr,0,n-1));
          for(int len=1;len<=n;len++){
              for(int i=0;i<=n-len;i++){
                 int j= i+len-1;
                 if(i==j){
                     dp[i][j]= arr[i];
                 }else if(j==i+1){
                     dp[i][j]= Math.max(arr[i],arr[j]);
                 }
                 else{
                     long pickLeft = arr[i]+ Math.min(dp[i+2][j],dp[i+1][j-1]);
                     long pickRight = arr[j]+ Math.min(dp[i][j-2],dp[i+1][j-1]);
                     dp[i][j] = Math.max(pickRight,pickLeft);
                 }
              }
          }
          System.out.println(dp[0][n-1]);

    }

    private static long findOptimalSol(int[] arr, int l, int r) {
        /*
         Time Complexity $O(n^2) hai aur Space Complexity bhi $O(n^2)hai.
         CSES mein n=5000 hai, iska matlab 5000 * 5000 = 2.5 *10^7 states hain.  recursion isliye TLE de raha hai kyunki
         recursive calls ka overhead (stack management)
          aur baar-baar parameters pass karna Java mein iterative solution ke mukable thoda slow hota hai.
         */
        if(l==r){
            return arr[l];
        }
        if(r-l==1){
            return Math.max(arr[l],arr[r]);
        }
        if(dp[l][r]!=-1){
            return dp[l][r];
        }

        long ans = Math.max(arr[l]+Math.min(findOptimalSol(arr,l+2,r),findOptimalSol(arr,l+1,r-1)),
                arr[r]+Math.min(findOptimalSol(arr,l,r-2),findOptimalSol(arr,l+1,r-1)));

        return dp[l][r]=ans;
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
