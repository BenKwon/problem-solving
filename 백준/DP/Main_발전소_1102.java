package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 사용 알고리즘 : dp, HashMap, , BFS
 */
public class Main_발전소_1102 {
    public static int[][] graph;
    static int n,p;
    static int sol = Integer.MAX_VALUE;
    public static void bfs(int initMask, int initCount){
        Queue<Level> q = new LinkedList<>();
        q.add(new Level(initMask, initCount));
        HashMap<Integer, Integer> weight = new HashMap<>();
        weight.put(initMask,0);
        
        while (!q.isEmpty()) {
            Level poll = q.poll();
//            System.out.println("poll.count = " + poll.count);
            if(poll.count >= p) {
                sol = Math.min(sol, weight.get(poll.bitmask));
            }
            ArrayList<Integer> NotWorking = findNotWorking(poll.bitmask);
            ArrayList<Integer> Working = findWorking(poll.bitmask);

            for(Integer WorkPlant : Working){
//                System.out.println("WorkPlant = " + WorkPlant);
                for (Integer NotWorkplant : NotWorking) {
//                    System.out.println("NotWorkplant = " + NotWorkplant);
                    int n_mask = poll.bitmask | (1 << NotWorkplant);
                    if(weight.get(n_mask) != null){ //이미 현재 발전소들 상태에 온적 있는경우
                        weight.put(n_mask, Math.min(weight.get(n_mask), weight.get(poll.bitmask) + graph[WorkPlant][NotWorkplant]));
                    }else{ //새로운 발전소 상태일 경우
                        weight.put(n_mask, weight.get(poll.bitmask) + graph[WorkPlant][NotWorkplant]);
                        q.add(new Level(n_mask, poll.count + 1));
                    }
                }
            }
        }
        if(sol == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(sol);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> s = new PriorityQueue<>();
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int init = 0 ;
        String str = br.readLine();
        p = Integer.parseInt(br.readLine());
        int init_on_num = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'Y'){
                init = init | (1 << i);
                init_on_num++;
            }
        }
//        System.out.printf("initmask : %d , initcount : %d \n",init,init_on_num);
        bfs(init,init_on_num);

    }

    static ArrayList<Integer> findNotWorking(int bitmask) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if((bitmask & (1<<i)) == 0) result.add(i);
        }
        return result;
    }
    static ArrayList<Integer> findWorking(int bitmask) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if((bitmask & (1<<i)) != 0) result.add(i);
        }
        return result;
    }
    static class Level{
        int bitmask;
        int count;

        public Level(int bitmask, int count) {
            this.bitmask = bitmask;
            this.count = count;
        }
    }
}
