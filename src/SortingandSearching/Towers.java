package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Towers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        //int[] tower = new int[n];

        TreeMap<Integer, Integer> towerheads = new TreeMap<>();
        for(int i=0;i<n;i++){
            int tower = Integer.parseInt(st.nextToken());
            Integer head = towerheads.higherKey(tower);

            if(head==null){
                towerheads.put(tower,towerheads.getOrDefault(tower,0)+1);
            }else{
                towerheads.put(head,towerheads.get(head)-1);
                if(towerheads.get(head)==0){
                    towerheads.remove(head);
                }
                towerheads.put(tower,towerheads.getOrDefault(tower,0)+1);
            }

        }

        int cnt = 0;
        for(int headCnt: towerheads.values()){
            cnt+=headCnt;
        }
        System.out.println(cnt);


    }
}
