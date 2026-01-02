package RangeQueries;

import java.io.IOException;
import java.io.InputStream;

public class ForestQueries {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       /*
          2d prefix sum
        */
        int n = fs.nextInt();
        int q = fs.nextInt();

        int[][] matrix = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            String s = fs.nextString();
            for(int j=1;j<=n;j++){
                if(s.charAt(j-1)=='*'){
                    matrix[i][j] = 1;
                }else{
                    matrix[i][j] = 0;
                }
            }
        }

        int[][] replicaSum = new int[n+1][n+1];
        //left to right sum
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                replicaSum[i][j] = replicaSum[i][j-1] + matrix[i][j];
            }
        }
        //top to down sum
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                replicaSum[i][j] = replicaSum[i-1][j]+replicaSum[i][j];
            }
        }
        StringBuilder ans = new StringBuilder();
        while (q-->0){
            int a = fs.nextInt();
            int b= fs.nextInt();
            int c = fs.nextInt();
            int d = fs.nextInt();
            int val = replicaSum[c][d] - replicaSum[c][b-1] - replicaSum[a-1][d] + replicaSum[a-1][b-1];
            ans.append(val).append("\n");
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
