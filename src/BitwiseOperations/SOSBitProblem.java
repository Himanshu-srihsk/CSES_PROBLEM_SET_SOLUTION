package BitwiseOperations;

import java.io.IOException;
import java.io.InputStream;

public class SOSBitProblem {
    static final int MAXB = 20;
    static final int MAX = 1 << MAXB;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();

       /*
       Array:
        a = [1, 2, 3, 3]
       x = 3 (binary 11)
       Submasks of 11:
       00 (0)
        01 (1)
        10 (2)
        11 (3)

        Matlab valid y:

        y = 0,1,2,3


        ->

        Submasks of 10 = {00, 10}
        11,01 is not submask

        Supermasks of 10 = {10, 11}
        not : 01, 00

        x | y = x then y is submask of x
        x & y = x then y is super mask of x
        x & y !=0 thenn at least 1 bit of both will be common and should be 1 i.e cnt[x] - cnt[y should be subset of ~x]

        x & y != 0
            = total elements
              − (number of y such that x & y = 0)

           x & y = 0   Matlab:

            jahan x me bit = 1
            wahan y me bit = 0 hi hona chahiye

            x ke jo bits 1 hain
            un jagah y ko 0 rehna hai
            Matlab y sirf un bits me 1 ho sakta hai
            jahan x me 0 hai

            Compelmte ; MAX = 1 << B
            Agar B = 4:

            MAX = 16

            Step 2: MAX - 1 kya hota hai?
            MAX - 1 = 15
            binary = 1111
            B bits ke andar sab 1

            Step 3: XOR kyun use kiya?

            XOR rule:

            1 ^ 0 = 1
            1 ^ 1 = 0

            Step 4: Ab actual complement formula
            complement = (MAX - 1) ^ x;

            1111
            ^ x
            -----
            ~x   (sirf 4 bits ke andar)

                  1111
                ^ 1100
                ------
                  0011


        */

        int[] cnt = new int[MAX];
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
           arr[i] = fs.nextInt();
           cnt[arr[i]]++;
        }
        // dpSub[mask] = sum of cnt[submask]
        int[] dpSub = cnt.clone();
        for(int i=0;i<MAXB;i++){
           for(int mask = 0;mask<MAX;mask++){
               if((mask&(1<<i))!=0){
                   dpSub[mask]+=dpSub[(mask^(1<<i))];
               }
           }
        }

        // dpSup[mask] = sum of cnt[supermask]
        int[] dpSup = cnt.clone();
        for(int i=0;i<MAXB;i++){
            for(int mask = 0;mask<MAX;mask++){
                if((mask&(1<<i))==0){
                    dpSup[mask]+=dpSup[(mask|(1<<i))];
                }
            }
        }

        StringBuilder out = new StringBuilder();
        for(int i=0;i<n;i++){
            int x = arr[i];
            /*
            the number of elements y such that x | y = x
        the number of elements y such that x & y = x
        the number of elements y such that x & y neq 0
             */

            int submaskCnt = dpSub[x];
            int supermaskCnt = dpSup[x];

            int complement = ((MAX - 1) ^ x);
            int bad =  dpSub[complement];
            int ans3 = n-bad;

                    /*
                    int complement = ((MAX - 1) ^ x);
            int bad = dpSub[complement];      // x & y = 0
            int ans3 = n - bad;               // x & y != 0

                     */
            out.append(submaskCnt).append(" ")
                    .append(supermaskCnt).append(" ")
                    .append(ans3).append("\n");
        }

        System.out.println(out.toString());


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
