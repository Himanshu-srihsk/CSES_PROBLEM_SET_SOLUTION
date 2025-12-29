package DynamicProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class Projects {
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        Project[] projects = new Project[n];
        for(int i=0;i<n;i++){
            projects[i] = new Project(fs.nextInt(),fs.nextInt(), fs.nextInt());
        }
        Arrays.sort(projects);

        int[] endTimes = new int[n];
        for(int i = 0; i < n; i++) endTimes[i] = projects[i].end;

        long[] dp = new long[n+1];
        for(int i=1;i<=n;i++){
            //skip current project
             dp[i] = dp[i-1];
             //take current project
            int bestJ = findLastProject(endTimes,projects[i-1].start);
            long currentReward = projects[i-1].reward;
            if(bestJ!=-1){
                currentReward = currentReward+dp[bestJ+1];
            }
            dp[i] = Math.max(dp[i],currentReward);
        }
        System.out.println(dp[n]);
    }

    private static int findLastProject(int[] endTimes, int start) {
        int low = 0;
        int high = endTimes.length -1;
        int ans = -1;
        while (low<=high){
            int mid = (low+(high-low)/2);
            if(endTimes[mid]<start){
                ans = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return ans;
    }

    static class Project implements Comparable<Project> {
        int start, end, reward;
        Project(int s, int e, int r) { start = s; end = e; reward = r; }
        public int compareTo(Project other) { return this.end - other.end; }
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
