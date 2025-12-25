package SortingandSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class NearestSmallerValues {
    public static void main(String[] args) throws IOException {
      FastScanner fc = new FastScanner(System.in);
      int n = fc.nextInt();

      StringBuilder ans = new StringBuilder();
        Stack<int[]> st = new Stack<>();
      for(int i=0;i<n;i++){
          int x = fc.nextInt();
          while (!st.isEmpty() && st.peek()[0]>=x){
              st.pop();
          }
          if(st.isEmpty()){
              ans.append(0).append(" ");
          }else{
              ans.append(st.peek()[1]).append(" ");
          }
          st.push(new int[]{x,i+1});
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
    }
}
