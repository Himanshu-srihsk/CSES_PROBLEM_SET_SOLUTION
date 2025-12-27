package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class LongestPalindrome {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String s = fs.nextString();
//        int maxi = 0;
//        String curr = "";
//        String max_str = "";
//        for(int i=0;i<n;i++){
//            curr = expandStr(s,i,i);
//            int currLen = curr.length();
//            if(currLen>maxi){
//                maxi = currLen;
//                max_str = curr;
//            }
//            curr = expandStr(s,i,i+1);
//            currLen = curr.length();
//            if(currLen>maxi){
//                maxi = currLen;
//                max_str = curr;
//            }
//        }
//        System.out.println(max_str);
       //TLE O(n^2)
        //Manacher Algorithm - O(n)
//https://www.youtube.com/watch?v=YVZttWzvyw8

        StringBuilder t = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            t.append("#").append(c);
        }
        t.append("#$");

        char[] arr = t.toString().toCharArray();
        int n = arr.length;

        //Manachar algo
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

        int startInd = (centerIndex-maxLen)/2;
        System.out.println(s.substring(startInd,startInd+maxLen));


    }

    private static String expandStr(String s, int i, int j) {
       int n = s.length();
       int low = i;
       int high = j;
       while(low>=0 && high<n && s.charAt(low)==s.charAt(high)){
           low--;
           high++;
       }
       return s.substring(low+1,high);
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
