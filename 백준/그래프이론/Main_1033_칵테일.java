package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1033_칵테일 {
    static int n;
    static ArrayList<ArrayList<int[]>> connectInfo = new ArrayList<>();
    static int[] curRecipe;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        curRecipe = new int[n];
        visit = new int[n];
        for (int i = 0; i < n; i++) {
            connectInfo.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            connectInfo.get(a).add(new int[]{b, p, q});
            connectInfo.get(b).add(new int[]{a, q, p});
        }
        int start = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit[0] = 1;
        curRecipe[0] = 1;
        int tmp = 1;
        while (!queue.isEmpty()) {
            int a = queue.poll();
//            System.out.println("a = " + a);
            ArrayList<int[]> connects = connectInfo.get(a);
            for (int[] next : connects) {
                int b = next[0];
                int p = next[1];
                int q = next[2];
                if (visit[b] == 1) continue;
                if (curRecipe[b] == 0) {
                    curRecipe[b] = curRecipe[a] * q;
                    curRecipe[a] *= p;
                    int count = 2;
                    for (int i = 0; i < n; i++) {
                        if(i != a && i != b){
                            if(curRecipe[i] != 0) count++;
                            curRecipe[i] *= p;
                        }
                    }
                }
//                System.out.printf("cur[%d] : %d, cur[%d] : %d\n", a, curRecipe[a], b, curRecipe[b]);
                visit[b] = 1;
                queue.add(b);
            }
        }

        int g = curRecipe[0];
        for (int i = 1; i < n; i++) g = gcd(g, curRecipe[i]);
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", curRecipe[i]/g);
        }
    }

    static int gcd(int a, int b){
        while(b> 0){
            int tmp = a;
            a= b;
            b = tmp % b;
        }
        return a;
    }
}
