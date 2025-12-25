package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Playlist {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] song = new int[n];
        for(int i=0;i<n;i++){
            song[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer> deque = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        int uniqueSongSeq = 0;
        for(int i=0;i<n;i++){
            if(!set.contains(song[i])){
                set.add(song[i]);
                deque.addLast(song[i]);

            }else{
                while (!deque.isEmpty() && deque.peekFirst() != song[i]){
                    int songData = deque.peekFirst();
                    deque.pollFirst();
                    set.remove(songData);
                }
                deque.pollFirst();
                deque.addLast(song[i]);
            }
            uniqueSongSeq = Math.max(uniqueSongSeq,deque.size());
        }
        System.out.println(uniqueSongSeq);
    }
}
