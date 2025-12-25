package SortingandSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ReadingBooks {
    public static void main(String[] args) throws IOException {
        FastScanner fc = new FastScanner(System.in);
        int n = fc.nextInt();
        long[] readingTime = new long[n];
        long totalTime = 0;
        for(int i=0;i<n;i++){
            readingTime[i] = fc.nextInt();
            totalTime+=readingTime[i];
        }
        Arrays.sort(readingTime);
        long ans = totalTime;
        long upTobeforenthBookTimeReq = totalTime - readingTime[n-1];
        if(readingTime[n-1] > upTobeforenthBookTimeReq){
            ans = 2 * readingTime[n-1];
        }
        System.out.println(ans);

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
