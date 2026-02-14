package Geometry;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://www.youtube.com/watch?v=B2AJoQSZf4M
public class ConvexHull {
    static Point pivot;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
   /*
        Few Testcases failing .......... Ned to check
         */
       //Graham Scan Algo
        List<Point> pointList = new ArrayList<>();
        for(int i=0;i<n;i++){
            pointList.add(new Point(fs.nextLong(),fs.nextLong()));
        }


        pivot = pointList.get(0); // Finding pivot
/*
Minimum y-coordinate wala point choose karte hain
Agar tie ho - minimum x
This will be pivot

Pivot choose karte hain (lowest y, lowest x)
 */
        for(Point p: pointList){
            if(pivot.y > p.y || (pivot.y==p.y && pivot.x > p.x)){
                pivot = p;
            }
        }

        //Sort by Polar Angle
        /*
        Polar angle = Pivot se kisi point tak jo line ja rahi hai, wo x-axis se kitna angle bana rahi hai
      Jo point pivot ke right me seedha hai ->  sabse pehle
        Uske baad thoda upar wala
        Phir aur upar wala
        Aur aise anti-clockwise sweep
         */

        pointList.sort((a,b) -> {
            long cross = crossProduct(pivot,a,b);
            if(cross==0){
                // if collinear , closer point first

                long distA = distSq(pivot,a);
                long distB = distSq(pivot,b);
                return Long.compare(distB, distA);
                /*
                When collinear:
Sort by distance descending
                 */
            }
            return cross > 0 ? -1 : 1;
        });

        //  Graham Scan
        Stack<Point> stack = new Stack<>();
//        stack.push(pivot);
        for(Point p: pointList){
            while (stack.size()>=2){
                Point last = stack.pop();
                Point secondLast = stack.peek();
                if(crossProduct(secondLast,last,p) >=0){
                    stack.push(last);
                    break;
                }
            }
            stack.push(p);
        }

        StringBuilder ans = new StringBuilder();
        ans.append(stack.size()).append("\n");
        for(Point p : stack){
            ans.append(p.x+" "+p.y).append("\n");
        }
        System.out.println(ans.toString());

    }

    private static long distSq(Point a, Point b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx*dx + dy*dy;
    }

    private static long crossProduct(Point o, Point a, Point b) {
        return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);

    }


    static class Point{
        long x,y;
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
