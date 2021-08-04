package 백준.DP;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1번째 2번째  ... w번째 사건이 있다고 하자.
 * 그렇다면 t번째 사건이 해결되는 순간 경찰차 1 또는 2가 반드시 t번째 사건이 일어나는 경우에 있어야한다.
 * 그렇다면 경찰차들은 항상 현재 일어난 사건을 포함해서 이전에 일어났던 사건들의 좌표 그리고 시작점 (0,0) (n,n)에만 위치한다.
 * 그렇다면 (0,0) (n ,n) 1번째 좌표, 2번째좌표 , 3번째 좌표... w번째 좌표라고 하고
 * dp[i][j] 는 경찰차 위치가 각각 i ,  j 번째의 위치일때 모든 사건을 해결할때 까지 걸리는 최소 거리
 * dp[경찰차1의 위치][경찰차2의 위치) = min(dp[다음에 일어난 사건의 위치][경찰차2의 위치] + 다음 사건과 경찰차1의 거리 ,
 *                                      dp[경찰차1의 위치][다음에 일어난 사건의 위치] + 다음 사건과 경찰차2의 거리)
 * dp[w][j]나 dp[i][w]는 항상 0이므로
 * dp[w-1][j] 나 d[[i][w-1]부터 구해나가면 된다
 */
public class Main_경찰차_2618 {
    static int[][] events;
    public static int n, w;
    static int[][] visit;
    static int[][] dp;
    static int[][][] way;
    static int k = 0;
    public static int dfs(int level, int car1_row, int car1_col, int car2_row, int car2_col, int car1, int car2) {
        if(level == w + 1) return 0;
        int cur_event_row = events[level + 1][0];
        int cur_event_col = events[level + 1][1];
        if(visit[car1][car2] == 1) {
            return dp[car1][car2];
        }
        int diff1 = Math.abs(car1_row - cur_event_row) + Math.abs(car1_col - cur_event_col);
        diff1 += dfs(level + 1, cur_event_row, cur_event_col, car2_row, car2_col, level + 1, car2);
        int diff2 = Math.abs(car2_row - cur_event_row) + Math.abs(car2_col - cur_event_col);
        diff2 += dfs(level + 1, car1_row, car1_col, cur_event_row, cur_event_col, car1 , level + 1);
        if(diff1 < diff2){
            way[car1][car2][0] = 1;
            way[car1][car2][1] = level + 1;
            way[car1][car2][2] = car2;

        }else{
            way[car1][car2][0] = 2;
            way[car1][car2][1] = car1;
            way[car1][car2][2] = level + 1;
        }
        dp[car1][car2] = Math.min(diff1, diff2);
        visit[car1][car2] = 1;
        return dp[car1][car2];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        events = new int[w + 2][2]; // events[i][0] : i번째에 발생한 사건의 row 좌표 vents[i][1] : i번째에 발생한 사건의 col 좌표
        dp = new int[w + 2][w + 2]; // dp[i][j] : i번째 좌표(evnets 배열으)와 j번째 좌표
        visit = new int[w + 2][w + 2]; // dp[i][j] : i번째 좌표(evnets 배열으)와 j번째 좌표
        way = new int[w + 2][w + 2][3];
        events[0] = new int[]{1, 1};
        events[1] = new int[]{n, n};
        for (int i = 2; i <= w + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }
        int dfs = dfs(1, 1, 1, n, n,0,1);
        System.out.println(dfs);

        int row = 0;
        int col = 1;
        while(true){
            int car_num = way[row][col][0];
            int next_row = way[row][col][1];
            int next_col = way[row][col][2];
            bw.write(car_num +"\n");
            if(next_col == w + 1|| next_row == w + 1)break;
            row = next_row;
            col = next_col;
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
