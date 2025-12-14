package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SlidingWindowCost {
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
    static long  lowerSumOfEle = 0l;
    static long  upperSumOfEle = 0l;
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


        TreeSet<Element> lower = new TreeSet<>();
        TreeSet<Element> upper = new TreeSet<>();
        StringBuilder sb = new StringBuilder();

        long min_cost = 0;

        for(int i=0;i<k;i++){
            Element currentElement = new Element(arr[i], i);
            if (lower.isEmpty() || currentElement.compareTo(lower.last()) <= 0) {
                lower.add(currentElement);
                lowerSumOfEle += currentElement.value;
            } else {
                upper.add(currentElement);
                upperSumOfEle += currentElement.value;
            }
            balanceTreeSet(lower, upper);
        }
        long medianK = lower.last().value;

        min_cost = lower.size()* medianK - lowerSumOfEle + upperSumOfEle - upper.size()*medianK;
        sb.append(min_cost).append(' ');

        for(int i=k;i<n;i++){
            Element elementEntering = new Element(arr[i], i);
            if (lower.isEmpty() || elementEntering.compareTo(lower.last()) <= 0) {
                lower.add(elementEntering);
                lowerSumOfEle += elementEntering.value;
            } else {
                upper.add(elementEntering);
                upperSumOfEle += elementEntering.value;
            }
            balanceTreeSet(lower, upper );

            Element elementLeaving = new Element(arr[i-k], i-k);
            if(lower.remove(elementLeaving)){
                lowerSumOfEle -= elementLeaving.value;
            }else{
                upper.remove(elementLeaving);
                upperSumOfEle -= elementLeaving.value;
            }
            balanceTreeSet(lower, upper);

            medianK = lower.last().value;

            min_cost = lower.size()* medianK - lowerSumOfEle + upperSumOfEle - upper.size()*medianK;

            sb.append(min_cost).append(' ');
        }
        System.out.println(sb.toString());

    }
    private static void balanceTreeSet(TreeSet<Element> lower, TreeSet<Element> upper) {
        while(lower.size() < upper.size()){
            Element croppedEle = upper.pollFirst();
            upperSumOfEle -= croppedEle.value;
            lowerSumOfEle += croppedEle.value;
            lower.add(croppedEle);
        }
        while (upper.size()+1 < lower.size()){
            Element croppedEle = lower.pollLast();
            lowerSumOfEle -= croppedEle.value;
            upperSumOfEle += croppedEle.value;
            upper.add(croppedEle);
        }
    }
}
