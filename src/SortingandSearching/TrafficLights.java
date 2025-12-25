

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrafficLights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int x = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        TreeMap<Integer,Integer > passagesLen = new TreeMap();
        TreeSet<Integer> trafficLightPos = new TreeSet<>();
        trafficLightPos.add(0);
        trafficLightPos.add(x);
        passagesLen.put(x,passagesLen.getOrDefault(x,0)+1);
        int maxPassageLength = x;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            int pos = Integer.parseInt(st.nextToken());

            int leftTrafficLightPos = trafficLightPos.floor(pos);
            int rightTrafficLightPos = trafficLightPos.ceiling(pos);

            int passageLength = rightTrafficLightPos - leftTrafficLightPos;

            passagesLen.put(passageLength,passagesLen.get(passageLength)-1);
            if(passagesLen.get(passageLength) == 0){
                passagesLen.remove(passageLength);
            }
            passagesLen.put(pos - leftTrafficLightPos, passagesLen.getOrDefault(pos - leftTrafficLightPos,0)+1);
            passagesLen.put(rightTrafficLightPos - pos, passagesLen.getOrDefault(rightTrafficLightPos - pos,0)+1);

            maxPassageLength = passagesLen.lastKey();
           // sb.append(maxPassageLength).append(' ');
            out.print(maxPassageLength + " ");
            trafficLightPos.add(pos);
        }
        //System.out.println(sb.toString());
        out.flush();
        out.close();
    }
}
