package TreeAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TreeDistancesI {
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        tree = new ArrayList[n];
        for(int i=0;i<n;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            tree[a-1].add(b-1);
            tree[b-1].add(a-1);
        }
        int farthestNode = bfs(0,n);
        int[] distanceFromEnd1 = bfsDistance(farthestNode,n);

        int routeLastNode = 0;
        int lastdist = 0;
        for(int i=0;i<n;i++){
            if(distanceFromEnd1[i]>lastdist){
                lastdist = distanceFromEnd1[i];
                routeLastNode = i;
            }
        }

        int[] distanceFromEnd2 = bfsDistance(routeLastNode,n);
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<n;i++){
            int dist = 0;
            dist = Math.max(distanceFromEnd1[i],distanceFromEnd2[i]);
            ans.append(dist).append(" ");
        }
        System.out.println(ans.toString());

    }
    private static int[] bfsDistance(int farthestNode,int n) {
        int[] dist = new int[n];
        Arrays.fill(dist,-1);
        dist[farthestNode] = 0;
        int farthestDistanceSoFar = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(farthestNode);
        while(!queue.isEmpty()){
            int y= queue.poll();
            for(int x: tree[y]){
                if(dist[x]==-1){
                    dist[x] = 1+dist[y];
                    if(dist[x]>farthestDistanceSoFar){
                        farthestDistanceSoFar = dist[x];
                    }
                    queue.add(x);
                }

            }
        }
        return dist;
    }

    private static int bfs(int i,int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        int[] dist = new int[n];
        Arrays.fill(dist,-1);
        dist[i] = 0;
        int farthestNode = i;
        while (!queue.isEmpty()){
            int y= queue.poll();
            for(int x: tree[y]){
                if(dist[x]==-1){
                    dist[x] = 1+dist[y];
                    if(dist[x]>dist[farthestNode]){
                        farthestNode = x;
                    }
                    queue.add(x);
                }

            }
        }
        return farthestNode;
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
