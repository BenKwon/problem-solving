package 백준.그래프이론;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Main_17471_게리맨더링 {
    static int n;
    static int[] nodes;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<ArrayList<Integer>>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = new int[n + 1];
        connectInfo.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
            nodes[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                int connect = Integer.parseInt(st.nextToken());
                connectInfo.get(i).add(connect);
                connectInfo.get(connect).add(i);
            }
        }
        solution();

    }

    static void solution(){
        boolean[] visit = new boolean[1 << n];
        int solution = INF;
        Queue<node> q = new LinkedList<>();
        q.add(new node(1, 1, nodes[1]));
        visit[1] = true;
        while(!q.isEmpty()){
            node poll = q.poll();
            int id = poll.id;
            int mask = poll.mask;
            int sum = poll.sum;
            System.out.println("id = " + id +" , sum = " + sum);

            int result = calOthers(mask);

            if(result > 0){
                solution = Math.min(solution, Math.abs(sum - result));
                System.out.println("solution = " + solution);
            }
            System.out.println("-----------------------------");

            for(int i = 1 ; i <= n ;i++){
                if((mask & (1<<(i - 1))) == 0) continue;
                ArrayList<Integer> connects = connectInfo.get(i);
                for(int next: connects){
                    int nextMask = 1 << (next - 1);
                    if((mask & nextMask) != 0) continue;
                    int newMask = mask | nextMask;
                    if(visit[newMask])continue;
                    q.add(new node(next, newMask, sum + nodes[next]));
                    visit[newMask] = true;
                }
            }

        }
        if(solution != INF) System.out.println(solution);
        else System.out.println(-1);
    }

    static int calOthers(int mask){
        for(int i = 1 ;i<= n; i++){
            int newMask = (1 << (i - 1));
            if((mask & newMask) != 0){
                System.out.printf("1 ");
            }else{
                System.out.printf("0 ");
            }
        }
        System.out.println();
        HashSet<Integer> visit = new HashSet<>();
        int startId = -1;
        int otherGroupNum = 0;
        for(int i = 1 ;i<= n; i++){
            int newMask = (1 << (i - 1));
            if((mask & newMask) == 0){
                startId = i;
            }else{
                otherGroupNum++;
                visit.add(i);
                System.out.println("visit = " + i);
            }
        }

        if(startId < 0) return -1;
        int sum = 0;
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(startId);
        visit.add(startId);
        while(!q.isEmpty()){
            int poll = q.poll();
//            System.out.println("poll = " + poll);
            sum += nodes[poll];
            count++;
            ArrayList<Integer> connects = connectInfo.get(poll);
            for(int next : connects){
                if(visit.contains(next)) continue;
                q.add(next);
                visit.add(next);
            }
        }
        System.out.println("otherGroupNum = " + otherGroupNum);
        System.out.println("startId = " + startId);
        System.out.println("count = " + count);
        System.out.println("sum = " + sum);
        if((n-otherGroupNum) == count) return sum;
        return -1;
    }
    static class node{
        int id;
        int mask;
        int sum;
        public node(int id, int mask,int sum) {
            this.id = id;
            this.mask = mask;
            this.sum = sum;
        }
    }

}
