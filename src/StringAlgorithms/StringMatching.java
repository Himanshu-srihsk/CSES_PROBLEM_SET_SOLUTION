package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class StringMatching {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String s = fs.nextString();
        String patt = fs.nextString();

        int[] lps = findLps(patt);

        int i = 0;
        int j = 0;
        int count = 0;
        while(i<s.length()){
            if(s.charAt(i) == patt.charAt(j)){
                i++;
                j++;
            }
            if(j==patt.length()){
                count++;
                j= lps[j-1];
            }
            else if(i<s.length() && s.charAt(i) != patt.charAt(j)){
                if(j!=0){
                    j=lps[j-1];
                }else{
                    i++;
                }
            }
        }
        System.out.println(count);
    }

    private static int[] findLps(String patt) {
        int n = patt.length();
        int[] lps = new int[n];
        lps[0] = 0;
        int i = 0;
        int j = 1;
        while(j<n){
            if(patt.charAt(i)==patt.charAt(j)){
                i++;
                lps[j] = i;
                j++;

            }else{
                if(i!=0){
                    i=lps[i-1];
                }else{
                    lps[j] = 0;
                    j++;
                }
            }

        }
        return lps;
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
