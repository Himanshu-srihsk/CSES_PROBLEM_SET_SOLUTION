package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class WordCombinations {
    static  int MOD = 1000000007;
    static class Trie{
        Trie[] next;
        boolean exist;
        Trie(){
            next = new Trie[26];
            this.exist = false;
        }
        void insert(String s){
            Trie curr = this;
            for(Character c: s.toCharArray()){
                 if(curr.next[c-'a']==null){
                     curr.next[c-'a'] = new Trie();
                 }
                 curr = curr.next[c-'a'];
            }
            curr.exist = true;
        }
    }
    public static void main(String[] args) throws IOException {
      FastScanner fc = new FastScanner(System.in);
      String s = fc.nextString();
      int n = fc.nextInt();

      Trie root = new Trie();
      for(int i=0;i<n;i++){
          root.insert(fc.nextString());
      }

      int[] dp = new int[s.length()+1];
      dp[0] = 1;
      for(int i=0;i<s.length();i++){
          if(dp[i]>0){
              Trie curr = root;
              for(int j=i;j<s.length();j++){
                  if(curr==null){
                      break;
                  }
                  curr = curr.next[s.charAt(j)-'a'];
                  if(curr!=null && curr.exist){
                      dp[j+1] = (dp[j+1] + dp[i])%MOD;
                  }
              }
          }
      }

      System.out.println(dp[s.length()]);

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
