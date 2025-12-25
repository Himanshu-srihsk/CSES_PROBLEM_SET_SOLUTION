package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){

            arr[i] = Long.parseLong(st.nextToken());
        }
         long maxi= arr[0];
        for(int i = 0;i<n;i++){
            if(arr[i] > maxi){
                maxi = arr[i];
            }
        }
        if(maxi <= 0){
            System.out.println(maxi);
        }else{
            long maxSoFar = 0;
            long maxSub = 0;
            for(int i=0;i<n;i++){
                maxSoFar+=arr[i];
                if(maxSoFar<0){
                    maxSoFar = 0;
                }
                if(maxSub<maxSoFar){
                    maxSub = maxSoFar;
                }

              //  System.out.println("maxsub ="+maxSub + " maxSoFar =" +maxSoFar);

            }
            System.out.println(maxSub);
        }

    }
}
