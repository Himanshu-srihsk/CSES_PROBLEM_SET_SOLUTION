package SlidingWindow;

import java.util.Scanner;

public class SlidingWindowXor {
    public static void main(String[] args) {
        int n,k,x,a,b,c;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k= sc.nextInt();

        x=sc.nextInt();
        a=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();
        long curr = x;
        long res = 0;
        long currRes = 0;

        long []nums = new long[n];

        for(int i=0;i<k;i++){
            nums[i] = curr;
            currRes = currRes ^ nums[i];
            curr =  (((long)a*curr + b) % c);
        }
        res  = currRes;

        for(int i=k;i<n;i++){
            nums[i] = curr;
            currRes= currRes ^ curr ^ nums[i-k];
            res = res ^ currRes;
            curr =  (((long)a*curr + b) % c);
        }
        System.out.println(res);
    }
}
