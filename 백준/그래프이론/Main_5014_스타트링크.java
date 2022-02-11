package 백준.그래프이론;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5014_스타트링크 {
    static int f, s, g, u, d;
    static int[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visit = new int[f + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s, 0});
        visit[s] = 1;
        while (!q.isEmpty()) {
            int[] poll =  q.poll();
            int stair = poll[0];
            int time = poll[1];
            if(stair == g){
                System.out.println(time);
                return;
            }
            if(stair - d > 0 && visit[stair -d] == 0){
                q.add(new int[]{stair - d, time + 1});
                visit[stair - d] = 1;
            }
            if(stair + u <= f && visit[stair + u] == 0){
                q.add(new int[]{stair + u, time + 1});
                visit[stair + u] = 1;
            }
        }
        System.out.println("use the stairs");
    }
}
