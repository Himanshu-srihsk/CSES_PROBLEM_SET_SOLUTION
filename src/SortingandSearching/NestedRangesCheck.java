package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NestedRangesCheck {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] ranges = new int[n][3];

        int[] contains = new int[n];
        int[] contained = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            ranges[i][0] = Integer.parseInt(st.nextToken());
            ranges[i][1] = Integer.parseInt(st.nextToken());
            ranges[i][2] = i;
        }
        Arrays.sort(ranges,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }
            return b[1]-a[1];
        });

        StringBuilder partOfRange = new StringBuilder();
        int maxRightBoundary = -1;
        for(int i=0;i<n;i++){
            if(ranges[i][1] > maxRightBoundary){

                contained[ranges[i][2]]= 0;
            }else{

                contained[ranges[i][2]]= 1;

            }
            maxRightBoundary = Math.max(maxRightBoundary,ranges[i][1]);
        }

//        Arrays.sort(ranges,(a,b)->{
//            if(a[1]!=b[1]){
//                return b[1]-a[1];
//            }
//            return a[0]-b[0];
//        });
//
//        StringBuilder containRange = new StringBuilder();
//        int maxLeftBoundary  = -1;
//        for(int i = n-1; i >= 0; i--){
//            if(ranges[i][0] <= maxLeftBoundary){
//                contains[ranges[i][2]] = 1;
//            }
//            maxLeftBoundary = Math.max(maxLeftBoundary, ranges[i][0]);
//        }
        StringBuilder containRange = new StringBuilder();
        int minR = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (ranges[i][1] >= minR) {
                contains[ranges[i][2]] = 1;
            }
            minR = Math.min(minR, ranges[i][1]);
        }

        for(int i=0;i<n;i++){
            containRange.append(contains[i]).append(" ");
            partOfRange.append(contained[i]).append(" ");
        }
        System.out.println(containRange.toString());
        System.out.println(partOfRange.toString());
    }
}
