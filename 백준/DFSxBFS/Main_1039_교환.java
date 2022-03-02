package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1039_교환 {
    static int n ,k,m;
    static int[][] visit;
    static String nStr;
    static int ans = -1;
    static void dfs(int count, int num){
        if(visit[num][count] == 1) return;
        if(count == k){
            ans = Math.max(ans, num);
            return;
        }
        visit[num][count] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                int next = changePos(num, i, j);
                if(next < 0 || visit[next][count + 1] == 1) continue;
                dfs(count + 1, next);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nStr = st.nextToken();
        n = Integer.parseInt(nStr);
        k = Integer.parseInt(st.nextToken());
        visit = new int[1000001][11];
        m = nStr.length();
        if(m > 1){
            for (int i = 1; i < m; i++) {
                for (int j = i + 1; j <= m; j++) {
                    int next = changePos(n, i, j);
                    if(next < 0) continue;
                    dfs(1, next);
                }
            }
        }
        System.out.println(ans);
    }

    public static int changePos(int num, int i, int j) {
        String tmp = Integer.toString(num);
        String newStr = "";
        char ichar = tmp.charAt(i - 1);
        char jchar = tmp.charAt(j - 1);
        for (int k = 0; k < tmp.length(); k++) {
            char next = tmp.charAt(k);
            if(k == i - 1){
                newStr += jchar;
            }else if(k == j - 1){
                newStr += ichar;
            }else{
                newStr += next;
            }
        }
        if(newStr.charAt(0) == '0') return -1;
        return Integer.parseInt(newStr);
    }
}
