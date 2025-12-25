package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class CollectingNumbersII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
//        Map<Long,Integer> pos = new HashMap<>();
        int[] pos = new int[n+1];
        for(int i=0;i<n;i++){

            arr[i] = Integer.parseInt(st.nextToken());
//            pos.put(arr[i],i+1);
            pos[arr[i]]= i+1;
        }

        Set<Integer> set = new HashSet<>();
        int round = 0;
        for(int i=0;i<n;i++){
            int curr = arr[i];
            if(!set.contains(curr-1)){
                round++;

            }
            set.add(arr[i]);
        }
       // System.out.println("Starting round is "+round);

//        for(int i=1;i<=n;i++){
//            System.out.println("Pos i ="+pos[i] + " for "+i);
//        }

        StringBuilder out = new StringBuilder();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a>b){
                int tmp = a;
                a = b;
                b = tmp;
            }
            int aVal = arr[a-1];
            int bVal = arr[b-1];

            if( aVal-1 > 0 && pos[aVal-1] > a && pos[aVal-1] <b){
              //  System.out.println("pos[aVal-1] =" +pos[aVal-1] + " a= "+a+" n="+b);
                round--;
            }
            if( aVal+1 <=n && pos[aVal+1] > a && pos[aVal+1] <b){
               // System.out.println("pos[aVal+1] =" +pos[aVal+1] + " a= "+a+" n="+b);
                round++;
            }
            if(bVal-1 > 0 && pos[bVal-1] > a && pos[bVal-1] <b){
              //  System.out.println("pos[bVal-1] =" +pos[bVal-1] + " a= "+a+" n="+b);
                round++;
            }
            if(bVal+1 <=n && pos[bVal+1] > a && pos[bVal+1] <b){
               // System.out.println("pos[bVal+1] =" +pos[bVal+1] + " a= "+a+" n="+b);
                round--;
            }

            if(aVal + 1 == bVal){
              //  System.out.println("aVal + 1 = bVal ) " +aVal + " bVal= "+bVal);
                round++;
            }

            if(bVal+1 == aVal){
                //System.out.println("bVal + 1 = aVal ) " +aVal + " bVal= "+bVal);
                round--;
            }
//            pos.put(aVal,b);
//            pos.put(bVal,a);


            pos[aVal] = b;
            pos[bVal] = a;

            swap(a,b,arr);

            //System.out.println(round);

            out.append(round).append('\n');
        }
        System.out.println(out.toString());

    }
    private static void swap(int i, int j, int[] arr){
        int temp = arr[i-1];
        arr[i-1] = arr[j-1];
        arr[j-1] = temp;
    }
}
