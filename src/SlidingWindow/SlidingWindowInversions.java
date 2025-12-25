package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
class SegmentTree{
    int[] ST;
    int n;
    public SegmentTree(int n) {
        this.n = n;
        ST = new int[4 * n +1];
    }
    public void build(int[] arr){
        buildTree(arr, 1, 0, arr.length - 1);
    }
    public void print(){
        for(int i:ST){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public void buildTree(int[] arr, int idx, int start, int end){
        if(start==end){
            ST[idx] = arr[start];
            return;
        }

        int mid = start + ((end - start) >> 1);
        buildTree(arr, 2 * idx+1, start, mid);
        buildTree(arr, 2*idx+2, mid+1, end);
        ST[idx] = ST[2*idx+1] + ST[2*idx+2];
    }
    public int query(int idx, int start, int end, int qs,int qe){
        if(qe< start ||  end < qs){
            return 0;
        }
        if(qs<=start && end<=qe){
            return ST[idx];
        }
        int mid = start + ((end - start) >> 1);

        return query(2*idx+1, start, mid, qs, qe) + query(2*idx+2, mid+1, end, qs, qe);
    }
    public void update(int idx, int start, int end,int updateIdx, int val){
        if( start == end){
            ST[idx]+= val;

            return;

        }
        int mid = start + ((end - start) >> 1);

        if( updateIdx <=mid){
            update(2*idx+1, start, mid, updateIdx, val);
        }else{
            update(2*idx+2, mid+1, end, updateIdx, val);
        }
        ST[idx]= ST[2*idx+1] + ST[2*idx+2];

    }

}


public class SlidingWindowInversions {
    public static void main(String[] args) throws IOException {
      // Sliding window + segment Tree Range + Coordinate Compression (as max can be 1 e9) Sum concepts
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int idx = 0;
        while(idx < n) {
            if (!st.hasMoreTokens()) {
                // Read the next line if the current one is exhausted
                String line = br.readLine();
                if (line == null) break; // Should not happen with valid input
                st = new StringTokenizer(line);
            }
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        //coordinate compression

        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> compMap = new HashMap<>();
        int id = 0;
        for (int x : sorted) {
            if (!compMap.containsKey(x)) {
                compMap.put(x, id++);
            }
        }

        int[] comp = new int[n];
        for (int i = 0; i < n; i++) {
            comp[i] = compMap.get(arr[i]);
        }

        SegmentTree seg = new SegmentTree(id);
        long inversions = 0;
        StringBuilder sb = new StringBuilder();
        // Initial window
        for (int i = 0; i < k; i++) {
            int pos = comp[i];
            inversions += seg.query(0, 0, id - 1, pos + 1, id - 1);
            seg.update(0, 0, id - 1, pos, 1);
        }
        sb.append(inversions).append(" ");

        for(int i=k;i<n;i++){

            int elementLeaving = comp[i-k];
            inversions -= seg.query(0,0,id-1,0,elementLeaving-1);
            seg.update(0,0,id-1,elementLeaving,-1);

            int elementEntering = comp[i];
            inversions += seg.query(0,0,id-1,elementEntering+1,id-1);
            seg.update(0,0,id-1,elementEntering,1);
            sb.append(inversions).append(" ");

        }
        System.out.println(sb.toString());
    }
}
