package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CollectingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){

            arr[i] = Long.parseLong(st.nextToken());
        }

        Set<Long> set = new HashSet<>();
        int round = 0;
        for(int i=0;i<n;i++){
            long curr = arr[i];
            if(!set.contains(curr-1)){
                round++;

            }
            set.add(arr[i]);
        }
        System.out.println(round);

    }
}
