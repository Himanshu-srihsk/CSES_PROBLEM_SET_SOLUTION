package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FerrisWheel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] weights = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(weights);
        if(weights[n-1]>x){
            System.out.println(Integer.MAX_VALUE);
        }
        int i = 0;
        int j = n-1;
        int ans = 0;
        while(i<=j){
            if(weights[i]+weights[j]<=x){
                i++;
            }
            j--;
            ans++;
        }

        System.out.println(ans);
    }
//the heaviest child is either paired with the lightest child or goes alone
}
