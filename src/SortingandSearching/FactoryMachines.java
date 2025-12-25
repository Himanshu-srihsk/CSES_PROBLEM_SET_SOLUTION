package SortingandSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

public class FactoryMachines {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long t = fs.nextInt();
        long[] manufacturingTimes = new long[n];
        for(int i = 0;i<n;i++){
            manufacturingTimes[i] = fs.nextInt();
        }
        Arrays.sort(manufacturingTimes);
        long low = 1;
        long high = manufacturingTimes[0] * t;
        long ans = high;
        while (low<=high){
            long mid = (low+(high-low)/2);
            if(canManufactureWithin(mid,manufacturingTimes,t)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        System.out.println(ans);
    }

    private static boolean canManufactureWithin(long mid, long[] manufacturingTimes, long t) {
        long machineMade = 0;
        for(long k: manufacturingTimes){
            machineMade = machineMade + mid/k;
            if(machineMade>=t){
                return true;
            }
        }

        return false;
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
