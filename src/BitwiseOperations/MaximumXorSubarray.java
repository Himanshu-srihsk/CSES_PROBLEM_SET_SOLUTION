package BitwiseOperations;

import java.io.IOException;
import java.io.InputStream;

/*
Step 1: Prefix XOR
px[i] = a1 ^ a2 ^ ... ^ ai

Step 2:
XOR(l..r) = px[r] ^ px[l-1]

So problem becomes:

Find two prefix XORs whose XOR is maximum
 */
public class MaximumXorSubarray {
    public static void main(String[] args) throws IOException {
       FastScanner fs = new FastScanner(System.in);
       int n = fs.nextInt();
       int[] arr = new int[n];
       for(int i=0;i<n;i++){
           arr[i] = fs.nextInt();
       }
        int result = maxSubarrayXOR(arr, n);
       System.out.println(result);
    }

    private static int maxSubarrayXOR(int[] arr, int n) {
       Trie root = new Trie();
      insertIntoTrie(root,0);
        int px = 0;
        int ans = 0;
        for(int i=0;i<n;i++){
            px^=arr[i];
            ans = Math.max(ans,findMax(root,px));
            insertIntoTrie(root,px);
        }
        return ans;
    }
    public static int findMax(Trie root, int num){
        int maxi = 0;
        Trie curr = root;
        for(int i=31;i>=0;i--){
            int bit = ((1 << i) & num) != 0 ? 1 : 0;

            if(curr.child[1-bit]==null){
                curr = curr.child[bit];
            }else{
                maxi = (maxi|(1<<i));
                curr = curr.child[1-bit];
            }
        }
        return maxi;
    }
    public static void insertIntoTrie(Trie root,int num){
        Trie curr = root;
        for(int i=31;i>=0;i--){
            int bit = ((1 << i) & num) != 0 ? 1 : 0;

            if(curr.child[bit]==null){
                curr.child[bit] = new Trie();
            }
            curr = curr.child[bit];
        }
        curr.num = num;
    }
    static class Trie{
        Trie[] child= new Trie[2];
        int num;
        Trie(){
            for(int i=0;i<2;i++){
                child[i]= null;
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
