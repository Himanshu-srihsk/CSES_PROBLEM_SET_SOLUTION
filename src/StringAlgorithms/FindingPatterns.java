package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FindingPatterns {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
        String s = fs.nextString();
        int q = fs.nextInt();
        String[] patterns = new String[q];
        Node root = new Node();
        for(int i=0;i<q;i++){
            patterns[i] = fs.nextString();
            root.insert(i,patterns[i]);
        }
        root.buildFailureLinks();

        //Aho-Corasick Algorithm
        Node curr = root;
        boolean[] found = new boolean[q];
        Arrays.fill(found,false);
        for(char ch: s.toCharArray()){
            int c = ch-'a';
            while (curr!=root && curr.next[c]==null){
                curr = curr.fail;
            }

            if(curr.next[c]!=null){
                curr = curr.next[c];
            }
//            if(curr.out.size()>0){
//                for(int idx: curr.out){
//                    found[idx] = true;
//                }
//            }


            Node temp = curr;
            while (temp != root) {
                if (!temp.out.isEmpty()) {
                    for (int idx : temp.out) {
                        found[idx] = true;
                    }
                    temp.out.clear();
                }
                temp = temp.fail;
            }



        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.append(found[i] ? "YES\n" : "NO\n");
        }
        System.out.println(sb.toString());
    }
    static class Node{
        Node[] next;
        Node fail;
        List<Integer> out;
        Node(){
            next = new Node[26];
            out = new ArrayList<>();
        }
        void insert(int ind,String s){
            Node curr = this;
            for(char c: s.toCharArray()){
                if(curr.next[c-'a']==null){
                    curr.next[c-'a'] = new Node();
                }
                curr = curr.next[c-'a'];
            }
            curr.out.add(ind);
        }
        void buildFailureLinks(){
            /*
            For node representing string X:
            Take all proper suffixes of X
            Find the longest suffix that exists in trie
            Point failure link there
             */

            //Failure link batata hai:
            //Agar yahan match toot gaya, to kahan se dubara try karein
            Queue<Node> q = new ArrayDeque<>();
            this.fail = this;

            for(int c=0;c<26;c++){
                if(this.next[c]!=null){
                    this.next[c].fail = this;
                    q.add(this.next[c]);
                }
            }

            while (!q.isEmpty()){
                Node curr = q.poll();
                for(int c=0;c<26;c++){
                    Node child = curr.next[c];
                    if(child==null){
                        continue;
                    }
                    Node failNode = curr.fail;
                    while (failNode!=this && failNode.next[c]==null){
                        failNode = failNode.fail;
                    }

                    if(failNode.next[c]!=null){
                        failNode=failNode.next[c];
                    }
                    child.fail = failNode;
//                    child.out.addAll(failNode.out);
                    q.add(child);
                }
            }


        }
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
