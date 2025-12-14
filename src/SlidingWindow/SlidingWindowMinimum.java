package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;

//class Node{
//    long num;
//    int index;
//
//    Node(long num,int index){
//        this.index = index;
//        this.num = num;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        Node node = (Node) object;
//        return index == node.index && num == node.num;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(index, num);
//    }
//}

public class SlidingWindowMinimum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String line = br.readLine();
        st = new StringTokenizer(line);
        int x,a,b,c;
        x = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long [] arr = new long[n];
        long curr = x;
        arr[0] = curr;

        for(int i=1; i < n; i++){
            curr = ((a * curr) + b) % c;
            arr[i] = curr;
        }


        Deque<Integer> dq = new ArrayDeque<>();
        // StringBuilder sb = new StringBuilder();
        long xor =0;
        for(int i=0;i<n;i++){
            while(!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
            if(dq.peekFirst() <= i-k){
                dq.pollFirst();
            }
            if(i>=k-1){
              //  System.out.println("Min is"+ arr[dq.peekFirst()]);
                xor^=arr[dq.peekFirst()];
               // sb.append(dq.peekFirst()).append(' ');
            }
        }
        System.out.println(xor);
    }
}
