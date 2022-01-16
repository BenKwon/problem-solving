package 백준.DP;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1256_사전 {
    static int n, m, k;
    static long[][] dp;
    static long[][] visit;
    static String sol = "";
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long dfs(int numA, int numZ) {
        if (numA == 0 || numZ == 0) return 1;
        if (visit[numA][numZ] == 1) return dp[numA][numZ];
        visit[numA][numZ] = 1;
        return dp[numA][numZ] = Math.min(1000000001,dfs(numA - 1, numZ) + dfs(numA, numZ - 1));
    }
    static void findWord(int A, int Z , long K) throws IOException {
        if(A == 0){
            for (int i = 0; i < Z; i++) {
                bw.write("z");
            }
            return;
        }
        if(Z == 0){
            for (int i = 0; i < A; i++) {
                bw.write("a");
            }
            return;
        }
        long numFront = dfs(A - 1,Z);
        if(K <= numFront){
            bw.write("a");
            findWord(A - 1, Z ,K);
        }else{
            bw.write("z");
            findWord(A, Z - 1 ,K - numFront);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new long[n + 1][m + 1];
        visit = new long[n + 1][m + 1];
//        System.out.println(dfs(n,m));
        if(dfs(n,m) < k){
            System.out.println(-1);
            return;
        }
        findWord(n,m,k);
        bw.flush();
    }
}
