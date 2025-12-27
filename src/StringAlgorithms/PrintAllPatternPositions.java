package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/*
Extension of Pattern Possition in CSES
 */
public class PrintAllPatternPositions {
    static Node root = new Node();
    static Node[] terminal;               // terminal node for each pattern
    static List<Node> allNodes = new ArrayList<>();
    static Map<Node, List<Node>> failTree = new HashMap<>();
    static final int INF = Integer.MAX_VALUE;
    static List<List<Integer>> result; // result positions for each pattern
    static int[] patternLen;
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        String text = fs.nextString();
        int n = fs.nextInt();
        //Aho-Corasick Algorithm
        terminal = new Node[n];
        allNodes.add(root);
        patternLen = new int[n];
        result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        allNodes.add(root);
        for (int i = 0; i < n; i++) {
            String pattern = fs.nextString();
            insert(i,pattern);
            patternLen[i] = pattern.length();
        }
        buildFailureLinks();
        processText(text);
       // dfs(root);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (result.get(i).isEmpty()) {
                sb.append("-1\n");
            } else {
                for (int pos : result.get(i)) {
                    sb.append(pos).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static void processText(String text) {
        Node curr = root;
        //for(char ch: text.toCharArray()){
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i)-'a';
            while(curr!=root && curr.next[c]==null){
                curr=curr.fail;
            }
            if(curr.next[c]!=null){
                curr = curr.next[c];
            }

            for(int pid: curr.out){
                int start = i - patternLen[pid]+2;
                result.get(pid).add(start);
            }
        }
    }

    private static void buildFailureLinks() {
        Queue<Node> queue = new ArrayDeque<>();
        root.fail = root;
        //level1
        for(int c=0;c<26;c++){
            if(root.next[c]!=null){
                root.next[c].fail = root;
                queue.add(root.next[c]);
            }
        }

        //level2
        /*
        will check for suffix in trie Node which is also prefix in the trie
         */

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            for(int c=0;c<26;c++){
                Node child = curr.next[c];
                if(child==null){
                    continue;
                }

                Node f = curr.fail;
                while (f!=root && f.next[c]==null){
                    f=f.fail;
                }
                if(f.next[c]!=null){
                    f=f.next[c];
                }
                child.fail = f;
                child.out.addAll(f.out);
                queue.add(child);
            }
        }
        // build failure tree (for count propagation)

        for(Node node: allNodes){
            if(node==root){
                continue;
            }
            failTree.computeIfAbsent(node.fail,k-> new ArrayList<>()).add(node);
        }
    }
    static class Node{
        Node[] next;
        List<Integer> out;
        Node fail;
        Node(){
            next = new Node[26];
            out = new ArrayList<>();
        }

    }
    static void insert(int ind,String s){
        Node curr = root;
        for(char c: s.toCharArray()){
            if(curr.next[c-'a']==null){
                curr.next[c-'a'] = new Node();
                allNodes.add(curr.next[c-'a']);
            }
            curr = curr.next[c-'a'];
        }
        curr.out.add(ind);
        terminal[ind]= curr;
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
/*
aybabtu
3
bab
abc
a
------------------
3
-1
1 4
 */