package SortingandSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ConcertTickets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Integer> tickets = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int price = Integer.parseInt(st.nextToken());
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<m;i++){
            int maxPriceCustomerCanPay = Integer.parseInt(st.nextToken());
            Integer ticket = tickets.floorKey(maxPriceCustomerCanPay);
            if(ticket==null){
                sb.append(-1).append('\n');
            }else{
                sb.append(ticket).append('\n');
                if(tickets.get(ticket) == 1){
                    tickets.remove(ticket);
                }else{
                    Integer ticketCnt = tickets.get(ticket);
                    tickets.put(ticket,ticketCnt-1);
                }
            }
        }
        System.out.println(sb.toString());

    }
}
