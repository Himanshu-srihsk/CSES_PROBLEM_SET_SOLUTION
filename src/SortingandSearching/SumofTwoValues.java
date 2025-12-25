

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SumofTwoValues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());

        }
        Set<Integer> set = new HashSet<>();

        for(int i=0;i<n;i++){
            int diff = x-arr[i];
            if(set.contains(diff)){
                int j = map.get(diff);
                if(i+1!=j)
                {
                    System.out.println(i+1+" "+j);
                    return;
                }

            }
            set.add(arr[i]);
            map.put(arr[i],i+1);
        }
        System.out.println("IMPOSSIBLE");


    }
}
