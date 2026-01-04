package Geometry;

import java.io.IOException;
import java.io.InputStream;

public class PointLocationTest {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       StringBuilder ans = new StringBuilder();
       while (n-- >0){
           long x1 = fs.nextLong();
           long y1 = fs.nextLong();
           long x2 = fs.nextLong();
           long y2 = fs.nextLong();
           long x3 = fs.nextLong();
           long y3 = fs.nextLong();

            double cross = (y2-y1) * (x3-x1) - (y3-y1) * (x2-x1);
//           double slope1 = (y2-y1)*1.0/(x2-x1);
//           double slope2 = (y3-y1)*1.0/(x3-x1);

           if(cross == 0){
               ans.append("TOUCH").append("\n");
           } else if (cross > 0) {
               ans.append("RIGHT").append("\n");
           }else{
               ans.append("LEFT").append("\n");
           }

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
