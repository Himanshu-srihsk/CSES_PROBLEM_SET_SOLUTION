package IntroductoryProblems;

import java.io.IOException;
import java.io.InputStream;

public class Permutations {
    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      int n = fs.nextInt();
      if(n==1){
          System.out.println("1");
          return;
      }
      if(n<=3){
          System.out.println("NO SOLUTION");
          return;
      }
       boolean odd = n%2==1;
      int val = odd? n/2+1 : n/2;

      int [] ansarr = new int[n];
      int x = 1;
      for(int i=0;i<n;i+=2){
          ansarr[i] =val;
          val--;
      }
       val = n;
      for(int i=1;i<n;i+=2){
          ansarr[i] = val;
          val--;
      }
        StringBuilder ans = new StringBuilder();
      for(int i=0;i<n;i++){
          ans.append(ansarr[i]).append(" ");
      }
      System.out.println(ans.toString());
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
