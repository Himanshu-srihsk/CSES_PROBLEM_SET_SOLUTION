package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class RequiredSubstring {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
        String pattern = fs.nextString().toLowerCase();
        int[] lps = buildLPS(pattern);

       //Buildautmation for kmp
        int m = pattern.length();
        int[][] next = new int[m][26];
        for(int i=0;i<m;i++){
            for(int c =0;c<26;c++){
                char ch = (char)(c+'a');
                int j= i;
                while(j>0 && pattern.charAt(j)!=ch){
                    j=lps[j-1];
                }
                if(pattern.charAt(j)==ch){
                    j++;
                }
                next[i][c]= j;
            }
        }

        long[][][] dp = new long[n + 1][m][2];
        dp[0][0][0]=1;

        // dp[len][state][found]
        /*
        kitni strings aisi hain

        jinki length = len

        jisme pattern ka state characters match ho chuke hain

        aur found batata hai ki pattern pehle mil chuka ya nahi
         */

        for(int len=0;len<n;len++){
            for(int state = 0;state<m;state++){
                for(int found = 0;found<2;found++){
                    long ways = dp[len][state][found];
                    if(ways==0){
                        continue;
                    }
                    for(int c=0;c<26;c++){
                        /*
                        Ab tum ek naya character add karte ho.
                        is char ko add karne ke baad:

                        pattern kitna match hoga?

                        pattern mil gaya kya?
                         */
                        int ns = next[state][c];
                        int nf = found;
                        if(ns==m){
                            nf = 1;
                            ns = lps[m-1]; //allow overlaps
                        }
                        dp[len+1][ns][nf] = (dp[len+1][ns][nf]+ways)%MOD;
                    }
                }
            }
        }

        long ans =0;
        for(int state =0;state<m;state++){
            /*
            hume sirf wahi strings chahiye
jisme pattern at least ek baar aaya ho
             */
            ans = (ans+dp[n][state][1])%MOD;
        }
        System.out.println(ans);

    }
    private static int[] buildLPS(String patt) {
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
