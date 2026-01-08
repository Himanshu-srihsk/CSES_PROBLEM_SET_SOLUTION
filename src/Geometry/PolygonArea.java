package Geometry;

import java.io.IOException;
import java.io.InputStream;
//https://www.youtube.com/watch?v=Zo7Hb-5ePOo
public class PolygonArea {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int  n= fs.nextInt();
       Point[] points = new Point[n];
       for(int i=0;i<n;i++){
           points[i] = new Point(fs.nextLong(),fs.nextLong());
       }

       /*
       Area of triangle for A(x,y)  B(x,y) and point C(0,0) = (AxBy - AyBx)/2;
        */

        long ans = 0;
        for(int i=0;i<n;i++){
            long area = points[i].x*points[(i+1)%n].y - points[(i+1)%n].x*points[i].y;
            ans  = ans + area;
        }
        ans = Math.abs(ans);
        System.out.println(ans);

    }
    static class Point{
        long x;
        long y;
        Point(long x,long y){
            this.x = x;
            this.y = y;
        }
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
