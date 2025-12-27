package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class AllPalindromes {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String s = fs.nextString();
        int m = s.length();
        StringBuilder t = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            t.append("#").append(c);
        }
        t.append("#$");

        char[] arr = t.toString().toCharArray();
        int n = arr.length;

        //Manachar algo
        /*
        a b a b a
        i :  0  1  2  3  4  5  6  7  8  9 10 11 12
        t :  ^  #  a  #  b  #  a  #  b  #  a  #  $
            p[i] = index i ke center par
            kitna bada palindrome radius possible hai
            Radius matlab:

            t[i-1] == t[i+1] -> radius 1

            t[i-2] == t[i+2] -> radius 2

            i = 6   (CENTER of full palindrome)
            t[5] == t[7] → # == #
            t[4] == t[8] → b == b
            t[3] == t[9] → # == #
            t[2] == t[10] → a == a
            t[1] == t[11] → # == #
            t[0] != t[12] → ^ != $
            So:

            p[6] = 5


            This is "ababa".

            i = 6, p=5
            start = (6-5)/2 = 0
            end   = (6+5)/2 - 1 = 4
            length = 5
            → "ababa" ends at index 4


            Now we reach i = 8
            Step 1 Check if reuse is allowed

            Condition:

            i < right ?
            8 < 11 → YES
            mEANS:

            i is INSIDE an already-known palindrome
            So reuse MUST happen.

            STEP2 Calculate mirror USING FORMULA
            mirror = 2*center - i
                   = 2*6 - 8
                   = 4


            So:

            mirror = 4


            And we already KNOW:

            p[4] = 3

            STEP3 Apply the Manacher reuse rule

            Manacher formula:

            p[i] = min( p[mirror], right - i )


            Compute both terms:

            1. p[mirror]
            p[4] = 3

            2. right - i
            right - i = 11 - 8 = 3

            STEP3. Take minimum
            p[8] = min(3, 3) = 3


          -> NO character comparisons needed so far.

            This exactly matches what you manually found:

            r=1
            r=2
            r=3


            But we got it for free.

         */
        int center = 0;
        int right = 0;
        int[] p = new int[n];

        int centerIndex = 0;
        int maxLen = 0;

        for(int i=1;i<n-1;i++){
            int mirror = 2*center - i;
            int k=0;
            /*
            Index:   0 1 2 3 4 5 6 7 8 9 10
            Chars:   ^ # b # a # b # a # b #
                                 ^
                              center=6
            i ke left side me kaunsa index same distance pe hai?”
            mirror --- center --- i
              (center - mirror) == (i - center)
              mirror = 2*center - i
             */
            if(i<right){
                k = Math.min(right-i,p[mirror]);
            }
            //expand
            while(t.charAt(i-k-1)==t.charAt(i+k+1)){
                k++;
            }
            p[i] = k;
            //update center and right
            if(i+p[i]>right){
                center = i;
                right = i+p[i];
            }

            if(p[i] > maxLen){
                maxLen = p[i];
                centerIndex = i;
            }

        }

        // ans[j] = longest palindrome ending at j
        int[] ans = new int[m];
        for(int i=1;i<n-1;i++){
            int r = p[i];
            if(r==0){
                continue;
            }
            int start = (i-r)/2;
            int end = (i+r)/2-1;
            int len = end-start+1;
            ans[end] = Math.max(ans[end],len);

        }
        // 4. Propagate smaller palindromes
        for (int i = m - 2; i >= 0; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1] - 2);
        }
        StringBuilder sb = new StringBuilder();
        for(int x:ans){
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString());

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
