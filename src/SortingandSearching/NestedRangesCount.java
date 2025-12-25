package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
class FenwickTree1{
    int n;
    int[] bit;
    FenwickTree1(int n){
        this.n = n;
        bit = new int[n+1];
    }
    void update(int idx,int delta){
        while(idx<=n){
            bit[idx]+=delta;
            idx = idx+(idx&-idx);
        }
    }
    int sum(int idx){
        int sum = 0;
        while(idx>0){
            sum+=bit[idx];
            idx = idx - (idx&-idx);
        }
        return sum;
    }
}
public class NestedRangesCount {
    public static void main(String[] args) throws IOException {
        /////////////////////////////////TLE/////////////////////////////////////////////////
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
//        int[][] ranges = new int[n][3];

        int[][] ranges = new int[n][4];

        int[] contains = new int[n];
        int[] contained = new int[n];

        int[] rights = new int[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            ranges[i][0] = Integer.parseInt(st.nextToken());
            ranges[i][1] = Integer.parseInt(st.nextToken());
            ranges[i][2] = i;
            rights[i] = ranges[i][1];
        }
        Arrays.sort(ranges,(a, b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }
            return b[1]-a[1];
        });

        //Coordinate Compression
//        Map<Integer,Integer> compress = new HashMap<>();
//        Arrays.sort(rights);
//        int id = 1;
//        for(int r: rights){
//            if(!compress.containsKey(r)){
//                compress.put(r,id++);
//            }
//        }

        Arrays.sort(rights);
        int id = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || rights[i] != rights[i - 1]) {
                rights[id++] = rights[i];
            }
        }

        for (int i = 0; i < n; i++) {
            ranges[i][3] = Arrays.binarySearch(rights, 0, id, ranges[i][1]) + 1;
        }

        //Fenwick Tree
        FenwickTree1 fw1 = new FenwickTree1(id);
        for(int i=0;i<n;i++){
          //  int r = compress.get(ranges[i][1]);
           // int r = Arrays.binarySearch(rights, 0, id, ranges[i][1]) + 1;
            int r = ranges[i][3];
            contained[ranges[i][2]]= fw1.sum(id) - fw1.sum(r-1);
            fw1.update(r,1);
        }
        FenwickTree1 fw2 = new FenwickTree1(id);
        for(int i=n-1;i>=0;i--){
            //int r = compress.get(ranges[i][1]);
            //int r = Arrays.binarySearch(rights, 0, id, ranges[i][1]) + 1;
            int r = ranges[i][3];
            contains[ranges[i][2]] = fw2.sum(r);  // kitne chote hai i.e sum up to r
            fw2.update(r,1);
        }
        StringBuilder containRange = new StringBuilder();
        StringBuilder partOfRange = new StringBuilder();
        for(int i=0;i<n;i++){
            containRange.append(contains[i]).append(" ");
            partOfRange.append(contained[i]).append(" ");
        }
        System.out.println(containRange.toString());
        System.out.println(partOfRange.toString());
    }
}
