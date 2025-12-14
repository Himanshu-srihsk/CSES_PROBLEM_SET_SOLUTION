package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int freq;
    int num;

    Node(int freq,int num){
        this.freq = freq;
        this.num = num;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Node node = (Node) object;
        return freq == node.freq && num == node.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(freq, num);
    }
}

public class SlidingWindowMode {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int idx = 0;
        int[] arr = new int[n];
        while (idx < n) {
            if (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break; // Should not happen with valid input
                st = new StringTokenizer(line);
            }
            arr[idx++] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> map = new HashMap<>();
//        TreeSet<Node> set = new TreeSet<Node>((a,b)->{
//            if(a.freq!=b.freq){
//                return a.freq-b.freq;
//            }else{
//                return a.num-b.num;
//            }
//        });
        /*
        Per window we do:
        2–4 TreeSet remove/add operations
        each = O(log distinctElements)
        heavy object creation (new Node)
        comparisons on (freq, num)
        so TLE
         */

        // frequency -> sorted values (to break ties by max value)
        Map<Integer, TreeSet<Integer>> freqMap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int maxFreq = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            set.add(new Node(entry.getValue(),-1 * entry.getKey()));
            TreeSet<Integer> old = freqMap.getOrDefault(entry.getValue(), new TreeSet<>());
            old.add(entry.getKey());
            freqMap.put(entry.getValue(), old);
            maxFreq = Math.max(maxFreq, entry.getValue());
        }
        StringBuilder sb = new StringBuilder();
//        sb.append(set.last().num*-1).append(' ');

        sb.append(freqMap.get(maxFreq).first()).append(' ');
        for (int i = k; i < n; i++) {
            int elementLeaving = arr[i - k];
            int elementEntering = arr[i];
            int outFreq = map.get(elementLeaving);
            freqMap.get(outFreq).remove(elementLeaving);
            //set.remove(new Node(map.get(elementLeaving),-1*elementLeaving));
            if (freqMap.get(outFreq).isEmpty()) {
                freqMap.remove(outFreq);
                if (outFreq == maxFreq) {
                    maxFreq--;
                }
            }

            map.put(elementLeaving, map.get(elementLeaving) - 1);

            if (map.get(elementLeaving) == 0) {
                map.remove(elementLeaving);
            } else {
                // set.add(new Node(map.get(elementLeaving),-1*elementLeaving));
                freqMap.computeIfAbsent(outFreq - 1, x -> new TreeSet<>()).add(elementLeaving);
            }
            int inFreq = map.getOrDefault(elementEntering, 0);
            if (map.containsKey(elementEntering)) {
                // set.remove(new Node(map.get(elementEntering),-1*elementEntering));
                freqMap.get(inFreq).remove(elementEntering);
                if (freqMap.get(inFreq).isEmpty()) {
                    freqMap.remove(inFreq);
                }
            }


            map.put(elementEntering, map.getOrDefault(elementEntering, 0) + 1);

            //set.add(new Node(map.get(elementEntering),-1*elementEntering));
            freqMap.computeIfAbsent(inFreq + 1, x -> new TreeSet<>()).add(elementEntering);
            maxFreq = Math.max(maxFreq, inFreq + 1);
            sb.append(freqMap.get(maxFreq).first()).append(' ');
        }
        System.out.print(sb.toString());
    }
}