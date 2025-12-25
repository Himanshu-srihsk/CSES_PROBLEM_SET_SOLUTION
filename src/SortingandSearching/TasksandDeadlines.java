package SortingandSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TasksandDeadlines {
    static class Task{
        int duration;
        int deadline;
        int gain;
        Task(int duration,int deadline,int gain){
            this.deadline = deadline;
            this.duration = duration;
            this.gain = gain;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fc = new FastScanner(System.in);
        int n = fc.nextInt();

        Task[] tasks = new Task[n];
        for(int i=0;i<n;i++){
            int a = fc.nextInt();
            int b = fc.nextInt();
            tasks[i] = new Task(a,b,b-a);
        }
        Arrays.sort(tasks,(a,b)->{
            if(a.duration!=b.duration){
                return a.duration - b.duration;
            }
            return b.gain - a.gain;
        });

        long reward  = 0;
        long time = 0;
        for(int i=0;i<n;i++){
            time = time + tasks[i].duration;
            reward = reward + tasks[i].deadline - time;
        }
        System.out.println(reward);

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
