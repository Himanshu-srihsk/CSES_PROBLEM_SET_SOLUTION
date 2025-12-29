

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MoneySums {
    static final int MOD = 1000000007;
    static TreeSet<Integer> sumPossible = new TreeSet<>();
    static boolean[][] visited = new boolean[101][100001];
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] coins = new int[n];
        int maxSum = 0;
        for(int i=0;i<n;i++){
            coins[i] = fs.nextInt();
            maxSum+=coins[i];
        }
//        findAllcoinsSum(coins,0,n,0);
//        sumPossible.remove(0);
       boolean[] dp = new boolean[maxSum+1];
        dp[0] = true;
        for(int coin: coins){
            for(int s= maxSum; s>=coin;s--){
                dp[s] = dp[s] || dp[s-coin];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=1;i<=maxSum;i++){
            if(dp[i]){
                result.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();
//        ans.append(sumPossible.size()).append("\n");
//        for(int x: sumPossible){
//            ans.append(x).append(" ");
//        }
        ans.append(result.size()).append("\n");
        for(int x: result){
            ans.append(x).append(" ");
        }
       System.out.println(ans.toString());
    }

    private static void findAllcoinsSum(int[] coins, int i, int n,int sum) {
        if (visited[i][sum]) return;
        visited[i][sum] = true;
        if(i>=n){
            sumPossible.add(sum);
            return;
        }
        findAllcoinsSum(coins,i+1,n,sum+coins[i]);
        findAllcoinsSum(coins,i+1,n,sum);
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
