package AdvancedTechniques;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetintheMiddle {
    /*
    2^20 = >10^6 manageable
2^40 = >10^12 impossible - will not work

Bitmask approch: - TLE at 1 Testcase
Total masks = 2^k
Inner loop = k
Total = k * 2^k
Time = O(k * 2^k)
For k = 20:

20 * 10^6 = 20 million operations

Recursion Method  -- work without TLE
include
exclude


Every element splits into 2 calls.

Total leaf nodes:

2^k
Time = O(2^k)
so for k= 20

2 million calls

     */
    static long[] coins;
    static Map<Long, Long> freq = new HashMap<>();
    static long target;
    static int n;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
         n = fs.nextInt();
        target = fs.nextInt();
         coins = new long[n];
        for(int i=0;i<n;i++){
            coins[i] = fs.nextLong();
        }
        int mid = n/2;
//        Map<Long, Long> freq = new HashMap<>();
//        int rightSize = n-mid;
//        for(int mask = 0; mask<(1<<rightSize);mask++){
//            long sum = 0;
//            for(int i=0;i<rightSize;i++){
//                if((mask&(1<<i))!=0){
//                    sum+=coins[mid+i];
//                }
//            }
//            freq.put(sum,freq.getOrDefault(sum,0l)+1);
//        }
//
//        long answer = 0;
//
//        int leftSize = mid;
//        for(int mask = 0;mask<(1<<leftSize);mask++){
//            long sum = 0;
//            for(int i=0;i<leftSize;i++){
//                if((mask&(1<<i))!=0){
//                    sum+=coins[i];
//                }
//            }
//            long needed = x-sum;
//            answer+= freq.getOrDefault(needed,0l);
//        }

        generateRight(mid, 0);

        long answer = generateLeft(0, mid, 0);

        System.out.println(answer);

    }

    static void generateRight(int idx, long sum) {
        if(idx == n) {
            freq.put(sum, freq.getOrDefault(sum, 0L) + 1);
            return;
        }

        generateRight(idx + 1, sum);
        generateRight(idx + 1, sum + coins[idx]);
    }


    static long generateLeft(int idx, int mid, long sum) {
        if(idx == mid) {
            return freq.getOrDefault(target - sum, 0L);
        }

        long count = 0;
        count += generateLeft(idx + 1, mid, sum);
        count += generateLeft(idx + 1, mid, sum + coins[idx]);

        return count;
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
