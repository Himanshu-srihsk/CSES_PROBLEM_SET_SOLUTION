package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class MinimalRotation {
    public static void main(String[] args) throws IOException {
       FastScanner fs= new FastScanner(System.in);
       String str = fs.nextString();
//        String min = str;
//
//        for (int i = 0; i < str.length(); i++)
//        {
//            str = str.substring(1) + str.charAt(0);
//            if (str.compareTo(min) < 0) {
//                min = str;
//            }
//        }
//
//        System.out.println(min);

        //Booth Algorithm
        String t = str+str;
         int n = str.length();

        int rotateOnIndx = boothAlgo(t,n);

        String minRotateStr= str.substring(rotateOnIndx)+str.substring(0,rotateOnIndx);
        System.out.println(minRotateStr);

    }

    private static int boothAlgo(String t,int n) {
        int i=0,j=1,count=0;

        while(i<n && j<n && count<n){
            char a = t.charAt(i+count); //  Booth me comparison current offset count ke saath hota hai
            char b = t.charAt(j+count);
            /*
            Booth me comparison current offset count ke saath hota hai
            count batata hai kitne characters already match ho chuke
             mismatch i + count aur j + count par aata hai

             Mismatch jone pe
             i = i + count + 1 ka matlab hai:
            jo candidates already haar chuke hain,
            un sabko ek saath skip kar do.

            i se lekar i+count tak sab rotations ka prefix same hai
            Aur wo prefix already j wali rotation se bada prove ho chuka hai
             */
            if(a>b){
                i=i+count+1;
                if(i==j){
                   j++;
                }
                count = 0;
            }else if(a<b){
                j=j+count+1;
                if(i==j){
                    j++;
                }
                count =0;
            }else{
                count++;
            }
        }
        return Math.min(i,j);
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
