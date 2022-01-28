package 백준;

import java.util.*;
import java.io.*;

public class Main_2637_장난감조립 {
    static int n, m;
    static HashMap<Integer, Integer>[] map;
    static HashSet<Integer> basicComponents = new HashSet<>();
    static int[][] dp;
    static int[] visit;
    static int[] recursive(int component){
        if(visit[component] == 1) return dp[component];
        if(basicComponents.contains(component)){
            dp[component][component] = 1;
            return dp[component];
        }
        HashMap<Integer, Integer> need = map[component];
        Set<Integer> integers = need.keySet();
        Iterator<Integer> iter = integers.iterator();
        while (iter.hasNext()) {
            int num = iter.next();
            int[] recursive = recursive(num);
            for (int i = 1; i <= n; i++) {
                if(recursive[i] > 0){
                    dp[component][i] += recursive[i]* need.get(num);
                }
            }
        }
        visit[component] = 1;
        return dp[component];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new HashMap[n + 1];
        dp = new int[n + 1][n + 1];
        visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new HashMap<>();
            basicComponents.add(i);
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (basicComponents.contains(x))
                basicComponents.remove(x);
            if (map[x].containsKey(y)) {
                map[x].put(y, map[x].get(y) + k);
            } else {
                map[x].put(y, k);
            }
        }

        int[] solution = recursive(n);
        for (int i = 1; i <= n; i++) {
            if(solution[i] != 0){
                bw.write(i +" " + solution[i] +"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
