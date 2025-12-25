package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class FenwickTree{
    int n;
    int[] bit;
    FenwickTree(int n){
        this.n = n;
        bit = new int[n+1];
    }
    void update(int index,int delta){
        while (index<=n){
            bit[index]+=delta;
            index = index + (index & -index);
        }
    }

    int sum(int index){
        int sum = 0;
        while (index>0){
          sum+=bit[index];
          index = index - (index&-index);
        }
        return sum;
    }
    //find smallest index such that sum(index) >= k
    int findKthSum(int k){
        int idx = 0;
        int bitMask = Integer.highestOneBit(n);

        while (bitMask>0){
            int next = (idx + bitMask);
            if(next<=n && bit[next] <k){
                k-=bit[next];
                idx = next;
            }
            bitMask>>=1;
        }
        return idx+1;
    }
}
public class JosephusProblemII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        FenwickTree fw = new FenwickTree(n);
        for(int i=1;i<=n;i++){
            fw.update(i,1);
        }
        StringBuilder sb = new StringBuilder();

        int alive = n;
        long curr = 0;
        for(int i=0;i<n;i++){
            curr = (curr+k)%alive;
            int index = fw.findKthSum((int)curr+1);
            sb.append(index).append(" ");
            fw.update(index,-1); //person marked as dead
            alive--;
        }
        System.out.println(sb.toString());


    }
}


//https://www.youtube.com/watch?v=9uaXG62Y8Uw