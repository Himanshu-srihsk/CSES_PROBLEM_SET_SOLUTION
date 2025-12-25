package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DistinctNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int idx = 0;
        Set<Integer> set = new HashSet<>();
        while(idx < n) {
            if (!st.hasMoreTokens()) {
                // Read the next line if the current one is exhausted
                String line = br.readLine();
                if (line == null) break; // Should not happen with valid input
                st = new StringTokenizer(line);
            }
            set.add(Integer.parseInt(st.nextToken()));
            idx++;
        }
        System.out.println(set.size());
    }
}
