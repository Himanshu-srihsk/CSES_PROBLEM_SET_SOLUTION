package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Apartments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
//        int idx =0;
//        int[] applicants  = new int[n];
//        while(idx < n) {
//            if (!st.hasMoreTokens()) {
//                // Read the next line if the current one is exhausted
//                String line = br.readLine();
//                if (line == null) break; // Should not happen with valid input
//                st = new StringTokenizer(line);
//            }
//            applicants[idx++] = Integer.parseInt(st.nextToken());
//        }
//
//        int[] apartment = new int[m];
//        idx =0;
//        while(idx < m) {
//            if (!st.hasMoreTokens()) {
//                // Read the next line if the current one is exhausted
//                String line = br.readLine();
//                if (line == null) break; // Should not happen with valid input
//                st = new StringTokenizer(line);
//            }
//            apartment[idx++] = Integer.parseInt(st.nextToken());
//        }

        int[] applicants = new int[n];
        int[] apartment = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            applicants[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            apartment[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(apartment);
        Arrays.sort(applicants);
        int ans = 0;
        int i=0;
        int j=0;
        while(i<n && j<m){
            if(applicants[i] - k > apartment[j]){
                j++;
            }else if(applicants[i] + k < apartment[j]){
                i++;
            }else{
                ans++;
                i++;
                j++;
            }
        }
        System.out.println(ans);
    }

}
