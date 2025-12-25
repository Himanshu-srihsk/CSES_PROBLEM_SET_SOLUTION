package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RoomAllocation {
    static class Guest {
        int a, d, idx;
        Guest(int a, int d, int idx) {
            this.a = a;
            this.d = d;
            this.idx = idx;
        }

        public Guest() {

        }
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());

        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        Guest[] guests = new Guest[n];
        for(int i=0;i<n;i++){
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            guests[i] = new Guest(a,b,i);

            guests[i] = new Guest();
            guests[i].a = fs.nextInt();
            guests[i].d = fs.nextInt();
            guests[i].idx = i;
        }
        Arrays.sort(guests, Comparator.comparingInt(guest -> guest.a));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int[] answer = new int[n];
        int roomCount=0;
        for(int i=0;i<n;i++){
            if (!pq.isEmpty() && pq.peek()[0] < guests[i].a){
                int[] top = pq.poll();
                answer[guests[i].idx] = top[1];
                pq.add(new int[]{guests[i].d,top[1]});
            }else{
                roomCount++;
                answer[guests[i].idx] = roomCount;
                pq.add(new int[]{guests[i].d,roomCount});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roomCount).append("\n");
        for(int i=0;i<n;i++){
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString());
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
