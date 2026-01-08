package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class CountingRooms {
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();
       char[][] building = new char[n][m];
       for(int i=0;i<n;i++){
           String x = fs.nextString();
           for(int j=0;j<x.length();j++){
               building[i][j] = x.charAt(j);
           }
       }
       boolean[][] visited = new boolean[n][m];
       int numRoom = 0;
       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
              if(!visited[i][j] &&  building[i][j]=='.'){
//                  int room = dfs(building,visited,i,j,n,m); // too many recursive call stack overflow
//                  System.out.println("At I="+i+" J="+j+" rom cnt ="+room);
                  bfs(building,visited,i,j,n,m);
                  numRoom++;
              }
           }
       }
       System.out.println(numRoom);
    }
    static void bfs(char[][] building, boolean[][] visited, int sr, int sc, int n, int m) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr,sc});
        visited[sr][sc] = true;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + row[d];
                int nc = c + col[d];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && building[nr][nc]=='.') {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr,nc});
                }
            }
        }
    }
    static int dfs(char[][] building,boolean[][] visited,int r,int c,int n,int m){

        visited[r][c] = true;
        int size = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + row[d];
            int nc = c + col[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && building[nr][nc]=='.') {
                size += dfs(building,visited,nr,nc,n,m);
            }
        }
        return size;

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
