package GraphAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Labyrinth {
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};
    static char[] dir = {'U','R','D','L'};

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        char[][] labyrinth = new char[n][m];
        int stRow = 0;
        int stCol = 0;
        int etRow = 0;
        int etCol = 0;
        for(int i=0;i<n;i++){
            String x = fs.nextString();
            for(int j=0;j<x.length();j++){
                labyrinth[i][j] = x.charAt(j);
                if(labyrinth[i][j]=='A'){
                    stRow = i;
                    stCol = j;
                }
                else if(labyrinth[i][j]=='B'){
                    etRow = i;
                    etCol = j;
                }
            }
        }
        StringBuilder out = new StringBuilder();
        if(isReachable(labyrinth,stRow,stCol,etRow,etCol,n,m,out)){
            System.out.println(out.toString());
        }else{
            System.out.println(out.toString());
        }
    }

    private static boolean isReachable(char[][] labyrinth, int stRow, int stCol, int etRow, int etCol, int n, int m,StringBuilder out) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int[][] distance = new int[n][m];
        int[][] parentR = new int[n][m];
        int[][] parentC = new int[n][m];
        int[][] parentDir = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[stRow][stCol] = 0;
        queue.add(new int[]{stRow,stCol});
        visited[stRow][stCol] = true;
        int minDistanceSoFar = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            if(r==etRow &&  c == etCol){
                break;
            }
            for(int d=0;d<4;d++){
                int nr = r + row[d];
                int nc = c + col[d];
                if(isValid(nr,nc,labyrinth,visited,n,m)){
                    parentR[nr][nc] = r;
                    parentC[nr][nc] = c;
                    parentDir[nr][nc] = d;
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr,nc});
                }
            }
        }
        if (!visited[etRow][etCol]) {
            out.append("NO");
            return false;
        }
        StringBuilder path = new StringBuilder();
        int r = etRow, c = etCol;

        while (r != stRow || c != stCol) {
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

        return true;
    }
    static boolean isValid(int nr,int nc,char[][] labyrinth,boolean[][] visited,int n,int m){
       return nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && labyrinth[nr][nc]!='#';
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
