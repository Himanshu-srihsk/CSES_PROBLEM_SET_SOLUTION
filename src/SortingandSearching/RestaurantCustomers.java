

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RestaurantCustomers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] dept = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            dept[i] = Integer.parseInt(st.nextToken());

        }

        Arrays.sort(arr);
        Arrays.sort(dept);

        int i=0;int j=0;
        int maxCustomer = 0;
        int curr = 0;
        while(i<n && j<n){
            //System.out.println("curr ="+curr+" i="+i+" j="+j+" arr at i "+arr[i]+" dept ta j = "+dept[j]);
            if(arr[i]< dept[j]){
                i++;
                curr++;
            }else{
                j++;
                curr--;
            }
            //System.out.println("curr ="+curr+" i="+i+" j="+j);
            maxCustomer = Math.max(maxCustomer,curr);
        }

        System.out.println(maxCustomer);

    }
}
