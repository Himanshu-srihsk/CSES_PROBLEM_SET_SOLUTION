

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MovieFestival {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] movies = new int[n][2];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            movies[i][0] = Integer.parseInt(st.nextToken());
            movies[i][1] = Integer.parseInt(st.nextToken());

        }

        Arrays.sort(movies,(a,b)-> Integer.compare(a[1],b[1]));

        int count = 0;
        int lastEnd = 0;
        for(int i=0;i<n;i++){
            int stTime = movies[i][0];
            int etTime = movies[i][1];
            if(stTime >= lastEnd){
                count++;
                lastEnd = etTime;
            }
        }
        System.out.println(count);


    }
}
