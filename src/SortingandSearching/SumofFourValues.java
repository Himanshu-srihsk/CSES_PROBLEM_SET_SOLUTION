

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SumofFourValues {
    static class Pair{

        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner fc = new FastScanner(System.in);
        int n = fc.nextInt();
        int target = fc.nextInt();
//        int[][] arr = new int[n][2];
//        for(int i=0;i<n;i++){
//            arr[i][0] = fc.nextInt();
//            arr[i][1] = i+1;
//        }
//        Arrays.sort(arr, Comparator.comparingInt(p -> p[0]));
//        for(int i=0;i<n;i++){
//            int need = target - arr[i][0];
//            StringBuilder ans = new StringBuilder();
//            if(possibleUsingSumOf3Val(need,i+1,n-1,arr,ans)){
//                ans.append(arr[i][1]).append(" ");
//                System.out.println(ans.toString());
//                return;
//            }
//        }
//        System.out.println("IMPOSSIBLE");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fc.nextInt();
        }
        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int s = arr[j] + arr[i];
                map.computeIfAbsent(s, k -> new ArrayList<>())
                        .add(new Pair(i, j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int sum = arr[i] + arr[j];
                int need = target - sum;

                if (map.containsKey(need)) {
                    for (Pair p : map.get(need)) {
                        if (p.i != i && p.i != j &&
                                p.j != i && p.j != j) {

                            System.out.println(
                                    (p.i + 1) + " " + (p.j + 1) + " " +
                                            (i + 1) + " " + (j + 1)
                            );
                            return;
                        }
                    }
                }
            }



        }
        System.out.println("IMPOSSIBLE");
    }

    private static boolean possibleUsingSumOf3Val(int need, int st, int et, int[][] arr,StringBuilder ans) {
        for(int i=st;i<et;i++){
            int req = need - arr[i][0];
            int low = i+1;
            int high = et;
            while(low<high){
                int sum = arr[low][0] + arr[high][0];
                if(sum==req){
                    ans.append(arr[i][1]).append(" ");
                    ans.append(arr[low][1]).append(" ");
                    ans.append(arr[high][1]).append(" ");
                    return true;
                }else if(sum<req){
                    low++;
                }else{
                    high--;
                }
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
