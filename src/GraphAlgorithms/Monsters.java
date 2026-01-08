package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Monsters {
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};
    static char[] dir = {'U','R','D','L'};
    static long[][] monstDist;

    static long[][] distArr;

    public static void main(String[] args) throws IOException {
      FastScanner fs = new FastScanner(System.in);
      /*
      multi-source BFS
       */
      int n = fs.nextInt();
      int m = fs.nextInt();
        char[][] building = new char[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        int start_row = 0;
        int start_col = 0;
        monstDist = new long[n][m];
        for(int i=0; i<n; i++) Arrays.fill(monstDist[i], Long.MAX_VALUE);

        for(int i=0;i<n;i++){
            String x = fs.nextString();
            for(int j=0;j<x.length();j++){
                building[i][j] = x.charAt(j);
                if(building[i][j]=='M'){
                    monstDist[i][j] = 0;
                    queue.add(new int[]{i,j});
                }else if(building[i][j]=='A'){
                    start_row = i;
                    start_col = j;
                }
            }
        }


// 1. MONSTER BFS
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + row[d];
                int nc = c + col[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&  building[nr][nc]!='#' && monstDist[nr][nc]> monstDist[r][c]+1) {
                    monstDist[nr][nc] = monstDist[r][c]+1;
                    queue.add(new int[]{nr,nc});
                }
            }
        }
        queue.clear();
        distArr = new long[n][m];
        for(int i=0; i<n; i++) Arrays.fill(distArr[i], Long.MAX_VALUE);
        distArr[start_row][start_col] = 0;
        queue.add(new int[]{start_row,start_col});

        int end_row = -1;
        int end_col = -1;

        int[][] parentR = new int[n][m];
        int[][] parentC = new int[n][m];
        int[][] parentDir = new int[n][m];
// 2. PLAYER BFS
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            if(isBoundary(r,c,n,m)){
                end_row = r;
                end_col = c;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + row[d];
                int nc = c + col[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&  building[nr][nc]!='#' && distArr[nr][nc]> distArr[r][c]+1) {
                    if(distArr[r][c]+1 < monstDist[nr][nc]){
                        distArr[nr][nc] = distArr[r][c]+1;

                        parentR[nr][nc] = r;
                        parentC[nr][nc] = c;
                        parentDir[nr][nc] = d;

                        queue.add(new int[]{nr,nc});
                    }

                }
            }

        }
        if(end_row == -1 && end_col == -1){
            System.out.println("NO");
            return;
        }
        List<Character> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        int r = end_row, c = end_col;

        StringBuilder out = new StringBuilder();

        while (r != start_row || c != start_col) {
            int d = parentDir[r][c];
            path.append(dir[d]);
            int pr = parentR[r][c];
            int pc = parentC[r][c];
            r = pr;
            c = pc;
        }
        out.append("YES\n");
        out.append(path.length()).append("\n");
        out.append(path.reverse());

       System.out.println(out.toString());
    }

    private static boolean isBoundary(int r, int c, int n, int m) {
        return r==0 || c==0 || r == n-1 || c==m-1;
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
