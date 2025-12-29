package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ElevatorRides {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int x = fs.nextInt();
        int[] weight = new int[n];
        for(int i=0;i<n;i++){
            weight[i] = fs.nextInt();
        }
        //DP + Bitmask

        State[] dp = new State[1 << n];

        for (int i = 0; i < (1 << n); i++) {
            dp[i] = new State(n + 1, Integer.MAX_VALUE);
        }

        dp[0] = new State(1, 0);  // base case

        for (int mask = 0; mask < (1 << n); mask++) {
            for (int next = 0; next < n; next++) {
                if ((mask & (1 << next)) == 0) {

                    int newMask = mask | (1 << next);
                    State cur = dp[mask];

                    int newRides = cur.rides;
                    int newWeight;

                    if (cur.lastWeight + weight[next] <= x) {
                        // put this person in the curr (last) ride
                        newWeight = cur.lastWeight + weight[next];
                    } else {
                        //start a new ride with this person
                        newRides++;
                        newWeight = weight[next];
                    }

                    // Compare pairs
                    if (newRides < dp[newMask].rides ||
                            (newRides == dp[newMask].rides && newWeight < dp[newMask].lastWeight)) {
                        dp[newMask] = new State(newRides, newWeight);
                    }
                }
            }
        }

        System.out.println(dp[(1 << n) - 1].rides);



    }
    static class State {
        int rides;
        int lastWeight;

        State(int r, int w) {
            rides = r;
            lastWeight = w;
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
