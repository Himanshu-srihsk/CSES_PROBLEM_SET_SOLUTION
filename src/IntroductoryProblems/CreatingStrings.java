package IntroductoryProblems;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CreatingStrings {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       String str = fs.nextString();

        if (str.length() == 1) {
            System.out.println(1);
            System.out.print(str);
            return;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder out = new StringBuilder();
        int cnt = 0;
        while (true)
        {
            cnt++;
            // print the current permutation

            out.append(new String(chars)).append("\n");

            // find the next lexicographically ordered permutation
            if (!next_permutation(chars)) {
                break;
            }
        }
        System.out.println(cnt);
        System.out.println(out.toString());

    }
    public static boolean next_permutation(char[] chars){
        //// find the largest index `i` such that `chars[i-1]` is less than `chars[i]`
        int  i= chars.length - 1;
        while (chars[i-1]>=chars[i]){
            if(--i==0){
                return false;
            }
        }

        // find the highest index `j` to the right of index `i` such that
        // chars[j] > chars[i-1]

        int j = chars.length - 1;
        while (j>i && chars[j]<=chars[i-1]){
            j--;
        }
        swap(chars, i - 1, j);

        // reverse substring `chars[i…n)` and return true
        reverse(chars, i);

        return true;

    }
    private static void swap(char[] chars, int i, int j)
    {
        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }

    private static void reverse(char[] chars, int start)
    {
        for (int i = start, j = chars.length - 1; i < j; i++, j--) {
            swap(chars, i, j);
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
