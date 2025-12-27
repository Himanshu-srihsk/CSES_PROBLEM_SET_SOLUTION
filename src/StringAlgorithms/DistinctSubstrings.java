package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DistinctSubstrings {
    static  State[] st;
    static int sz; // num of states
    static int last; //last state
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        /*
        SUFFIX Automation

        SAM ka har state ye represent karta hai:

        “Kuch substrings ka ek GROUP,
        jinke end positions same type ke hain”

        len[v]  = is state ka longest substring length
        link[v] = is state ka longest proper suffix ka state
        Start:
        State 0 (root)
        len = 0
        Add 'a'
        (0) --a--> (1)
        State 1:
        len = 1
        link = 0
        Contribution:
        len[1] - len[link[1]] = 1 - 0 = 1
        -> "a"

        Add 'b'
        Substrings: "a", "b", "ab"

        SAM:
        (0) --a--> (1)
        (0) --b--> (2)
        (1) --b--> (2)
        State 2:
        len = 2
        link = 0
        Contribution:
        2 - 0 = 2
        -> "b", "ab"

        Add 'a'
        Substrings:
        a, b, a, ab, ba, aba
        distinct = a, b, ab, ba, aba
        SAM:
        (0) --a--> (1)
        (0) --b--> (2)
        (1) --b--> (2)
        (2) --a--> (3)
        State 3:
        len = 3
        link = 1

        Contribution:
        3 - 1 = 2
        -> "ba", "aba"

        Now Calculation Total distinct substrings:
        State 1 -> 1
        State 2 -> 2
        State 3 -> 2
        ----------------
        Total = 5

        a, b, ab, ba, aba

        Formula:
        Σ ( len[v] − len[link[v]] )

        Iska matlab:

        Har state v ye bol raha hai:

        “Main itni new substring lengths introduce kar raha hoon
        jo pehle kabhi nahi aayi”

          Ex:

          For "abab":

        Suffixes:

        "bab" wrong
        "ab"  right


        So:

        link("abab") = state("ab")


        NOT root.
        ->

        Suffix Automaton is string-based, not tree-based
         */

        String s = fs.nextString();
        int n = s.length();
        saInit(n);
        for(char c: s.toCharArray()){
            saExtend(c);
        }
        long ans = 0;
        for(int v=1;v<sz;v++){
            ans = ans+ st[v].len - st[st[v].link].len;
        }
        System.out.println(ans);

    }
    static void saExtend(char c){
       int curr = sz++;
       st[curr].len = st[last].len+1;

       int p = last;
       while(p!=-1 && !st[p].next.containsKey(c)){
           st[p].next.put(c, curr);
           p = st[p].link;
       }
       if(p==-1){
           st[curr].link = 0;
       }else{
           /*
           check if cloning required
            */
           int q = st[p].next.get(c);
           if(st[p].len+1 == st[q].len){
               st[curr].link = q;
           }else{
               int clone = sz++;
               st[clone].len = st[p].len+1;
               st[clone].next.putAll(st[q].next);
               st[clone].link = st[q].link;

               while (p!=-1 && st[p].next.get(c)==q){
                   st[p].next.put(c,clone);
                   p = st[p].link;
               }
               st[q].link = st[curr].link = clone;
           }
       }
       last = curr;
    }
    static void saInit(int maxLen){
        st = new State[2*maxLen]; //{ 2*MaxLen required because of cloning }
        for(int i=0;i<2*maxLen;i++){
            st[i] = new State();
        }
        st[0].len = 0;
        st[0].link = -1;
        sz = 1;
        last = 0;

    }
    static class State{
        int len;  //longest String length
        int link; //suffix link
        Map<Character,Integer> next = new HashMap<>();
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sgn = 1, res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sgn;
        }
        long nextLong() throws IOException {
            int c, sgn = 1;
            long res = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res * sgn;
        }
        String nextString() throws IOException {
            int c;
            StringBuilder sb = new StringBuilder();

            // skip whitespace
            do {
                c = read();
            } while (c <= ' ');

            // read characters until whitespace
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }
    }
}
