package SlidingWindow;

import java.util.Scanner;

public class SlidingWindowSum {
    public static void main(String[] args) {
        int n,k,x,a,b,c;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k= sc.nextInt();

        x=sc.nextInt();
        a=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();

       // List<Integer> nums = new ArrayList<>();
        long curr = x;
        long [] prefixSum = new long[n];
        prefixSum[0] = curr;

        for(int i=1;i<n;i++){
            curr =  (((long)a*curr + b) % c);
            prefixSum[i] = (long)(prefixSum[i-1]+curr*1l);
        }
        long res  = 0;

        int start = 0;
        int end = k-1;
        int moves = n-k+1;
        res = (long) res ^ prefixSum[end];
        for(int i=1;i<moves;i++){
            long sum =  (long)(prefixSum[++end] - prefixSum[start++]);
            res ^= sum;
        }
        System.out.println(res);

    }
}
