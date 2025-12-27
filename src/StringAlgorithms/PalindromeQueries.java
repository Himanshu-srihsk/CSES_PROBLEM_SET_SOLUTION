package StringAlgorithms;

import java.io.IOException;
import java.io.InputStream;

public class PalindromeQueries {
    static long[] pow;
    static final long MOD = 1_000_000_007L;
    static final long BASE = 31L;
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int m = fs.nextInt();

       //precompute power
        pow = new long[n+1];
        pow[0]=1;
        for(int i=1;i<=n;i++){
            pow[i] = (pow[i-1]*BASE)%MOD;
        }

        //SEGMENT TREE + ROLLING HASH
         String s = fs.nextString();
        SegmentTree forward = new SegmentTree(s);
        SegmentTree reverse = new SegmentTree(new StringBuilder(s).reverse().toString());
        StringBuilder ans = new StringBuilder();
        while(m-->0){
            int type = fs.nextInt();
            if(type ==1){
                int a = fs.nextInt()-1;
                char ch = fs.nextString().charAt(0);
                forward.update(1,0,n-1,a,ch);
                reverse.update(1,0,n-1,n-1-a,ch);
            }else{
                int a = fs.nextInt()-1;
                int b = fs.nextInt()-1;

                Node hash1 = forward.queryTree(1,0,n-1,a,b);

                int ra = n - 1 - b;
                int rb = n - 1 - a;

                Node hash2 = reverse.queryTree(1,0,n-1,ra,rb);
                if(hash2.hash == hash1.hash){
                    ans.append("YES").append("\n");
                }else{
                    ans.append("NO").append("\n");
                }
            }

        }
        System.out.println(ans.toString());


    }
    static class Node{
       long hash;
       int length;
       Node(long hash,int length){
           this.hash = hash;
           this.length = length;
       }
    }
    static class SegmentTree{
        Node[] tree;
        String s;
        int n;
        SegmentTree(String s){
            this.s = s;
            this.n = s.length();
            tree = new Node[4*n+1];
            buildTree(1,0,n-1);
        }
        void buildTree(int idx,int l,int r){
            if(l==r){
                tree[idx] = new Node(s.charAt(l),1);
                return;
            }
            int mid = (l+(r-l)/2);
            buildTree(2*idx,l,mid);
            buildTree(2*idx+1,mid+1,r);
            tree[idx] = merge(tree[2*idx],tree[2*idx+1]);
        }
        Node merge(Node left,Node right){
            long hash = (left.hash + right.hash * pow[left.length])%MOD;
            return new Node(hash,left.length+right.length);
        }
        void update(int idx,int l,int r,int pos,char c){
            if(l==r){
                tree[idx] = new Node(c,1);
                return;
            }
            int mid = (l+(r-l)/2);
            if(mid<pos){
                update(2*idx+1,mid+1,r,pos,c);
            }else{
                update(2*idx,l,mid,pos,c);
            }
            tree[idx] = merge(tree[2*idx],tree[2*idx+1]);
        }
        Node queryTree(int idx,int l,int r,int ql,int qr){
            if(qr< l ||  r < ql){
                return null;
            }
            if (ql <= l && r <= qr) return tree[idx];
            int mid = (l+(r-l)/2);
            Node left = queryTree(2*idx,l,mid,ql,qr);
            Node right = queryTree(2*idx+1,mid+1,r,ql,qr);
            if (left == null) return right;
            if (right == null) return left;
            return merge(left,right);
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
