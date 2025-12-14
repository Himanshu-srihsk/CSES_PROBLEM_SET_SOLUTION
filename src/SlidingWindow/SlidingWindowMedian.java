package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SlidingWindowMedian {
    static class Element implements Comparable<Element> {
        long value;
        int index;

        Element(long value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element other) {
            if (this.value != other.value) {
                return Long.compare(this.value, other.value);
            }
            return Integer.compare(this.index, other.index);
        }
    }
    public static void main(String[] args) throws IOException {
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

//        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
//        PriorityQueue<Integer> upper = new PriorityQueue<>();
        // TLE with PriorityQueue as remove operation take O(n^2) time in worst case

        TreeSet<Element> lower = new TreeSet<>();
        TreeSet<Element> upper = new TreeSet<>();

        Map<Integer, Integer> pendingDeletion = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<k;i++){
//            int element = arr[i];
//            if(lower.isEmpty() || lower.peek()>=element){
//                lower.add(element);
//            }else {
//                upper.add(element);
//            }
//            balanceHeaps(lower, upper);

            Element currentElement = new Element(arr[i], i);
            if (lower.isEmpty() || currentElement.compareTo(lower.last()) <= 0) {
                lower.add(currentElement);
            } else {
                upper.add(currentElement);
            }
            balanceTreeSet(lower, upper);
        }
        sb.append(lower.last().value).append(' ');
        for(int i=k;i<n;i++){
//            int elementEntering = arr[i];
//            int elementLeaving = arr[i-k];
//            if(lower.isEmpty() || lower.peek()>=elementEntering){
//                lower.add(elementEntering);
//            }else {
//                upper.add(elementEntering);
//            }
//
//            if(!lower.isEmpty() && lower.peek()>=elementLeaving){
//                lower.remove(elementLeaving);
//            }else{
//                upper.remove(elementLeaving);
//            }
//            balanceHeaps(lower, upper);

            Element elementEntering = new Element(arr[i], i);
            if (lower.isEmpty() || elementEntering.compareTo(lower.last()) <= 0) {
                lower.add(elementEntering);
            } else {
                upper.add(elementEntering);
            }
            balanceTreeSet(lower, upper);

            Element elementLeaving = new Element(arr[i-k], i-k);
            if(lower.contains((elementLeaving))){
                lower.remove(elementLeaving);
            }else{
                upper.remove(elementLeaving);
            }
            balanceTreeSet(lower, upper);

            sb.append(lower.last().value).append(' ');
        }
        System.out.println(sb.toString());

    }

    private static void balanceTreeSet(TreeSet<Element> lower, TreeSet<Element> upper) {
        while(lower.size() < upper.size()){
            lower.add(upper.pollFirst());
        }
        while (upper.size()+1 < lower.size()){
            upper.add(lower.pollLast());
        }
    }

    private static void balanceHeaps(PriorityQueue<Integer> lower, PriorityQueue<Integer> upper) {
        while(lower.size() < upper.size()){
            lower.add(upper.poll());
        }
        while (upper.size()+1 < lower.size()){
            upper.add(lower.poll());
        }
    }


}
