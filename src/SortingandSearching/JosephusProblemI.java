package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JosephusProblemI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=1;i<=n;i++){
            dq.add(i);
        }

        StringBuilder sb = new StringBuilder();

        performJosephus(dq,sb);
        System.out.println(sb.toString());
    }

    private static void performJosephus(Deque<Integer> dq, StringBuilder sb) {
        while (!dq.isEmpty()){
            dq.offerLast(dq.pollFirst());
            sb.append(dq.pollFirst()).append(" ");
        }

    }
}
