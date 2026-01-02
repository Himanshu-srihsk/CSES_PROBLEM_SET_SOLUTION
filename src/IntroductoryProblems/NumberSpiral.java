

import java.io.IOException;
import java.io.InputStream;

public class NumberSpiral {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int t= fs.nextInt();
       StringBuilder ans = new StringBuilder();
       while(t-->0){
           long a= fs.nextLong();
           long b=fs.nextLong();
           long maxi = Math.max(a,b);
           long mini = Math.min(a,b);
           long x = 0;
           if(maxi%2==0){
               if(a==maxi){
                   x = a *a- b +1;
               }else{
                   x = (b-1)*(b-1)+ a;
               }
           }else{
               if(b==maxi){
                   x = b *b- a +1;

               }else{
                   x = (a-1)*(a-1)+ b;
               }
           }
           ans.append(x).append("\n");
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
