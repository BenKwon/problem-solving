package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 골드1 푼 방법
 * 각 구역에서는 자기 왼쪽 오른쪽 아래로 내려갈 수 있다.
 * dfs방식으로 자기 왼쪽 오른쪽 아래로 갈때를 재귀형식으로 불러서 가장 최댓값을 자기 자리에 넣는 것이다.
 * 하지만 왼쪽 오른쪽 두 방향으로 동시에 갈 수 있기 때문에 단순 dfs 재귀방식으로 하면 자기 자신으로 다시 돌아온다. 문제 조건에는 구역 재방문은 안된다.
 * 따라서 재귀로 호출할때 인자로 부모 좌표를 넣어주고 이동시 부모좌표랑 일치하면 패스한다.
 * 하지만 이렇게해도 문제가 생긴다
 * 3 3
 * 10 25 13
 * 4 -24 -23
 * 6 8 9
 * 가 입력으로 들어왔을때
 * 10 -> 4 를 먼저 방문한다 이후 계산이 끝나고
 * 이어서 10 - > 25 경로탐색이 시작되는데 10 -> 25 -> -24일때 문제가 발생한다.
 * 그 이유는 -24인 구역은 이미 10 -> 4 방문했을때 재귀로 이미 방문된 구역이다. 하지만 -24 -> 4로 가는 경로는 탐색이 된 적없다.
 * 그 이유는 4 -> -24 로 가서 -24에서 탐색이 시작되었을때 -24의 부모 구역이 4이기 때문에 -24 -> 4로 가는 경로는 스킵된다.
 * 따라서 각 구역마다 자기 아래 왼쪽 오른쪽을 visit했는지 확인 시켜줄수있는 visit[n][m][3] 이 필요하다.
 * 처음에 4에서 -24로 가서 탐색할 경우 -24는 아래하고 오른쪽만 탐색해서 visit[2][2]의 합은 2이다. 즉 -24 구역은 자기가 이동할수있는 모든 경로에 대해 탐색이 안 끝났다.(3보다 작으므로)
 * 따라서 25에서 -24로 가서 체크할때 -24구역이 탐색이 안끝났으므로 visit[2][2]를 보고 탐색이 안된 경우를 찾아 다시 탐색을 시작한다.
 *
 */
public class Main_로봇조종하기_2169 {
    static int graph[][];
    static int dp[][][][];
    static int visit[][][];
    static int[] row_move = new int[]{1, 0, 0};
    static int[] col_move = new int[]{0, 1, -1};
    static int n, m;
    public static void dfs(int row, int col, int parent_row, int parent_col) {
        if (row == n && col == m) {
            return;
        }
        for (int i = 0; i < 3; i++) { // i = 0 아래쪽 1 : 오른쪽 2; 왼쪽
            int n_row = row + row_move[i];
            int n_col = col + col_move[i];
            if (n_row < 1 || n_row > n || n_col < 1 || n_col > m) {
                visit[row][col][i] = 1;
                continue;
            }
            if(visit[row][col][i] ==1) continue;

            if (n_row == parent_row && n_col == parent_col) continue;

            if (Arrays.stream(visit[n_row][n_col]).sum() < 3) {
                dfs(n_row, n_col, row, col);
            }

            int target_value = -1000000001;
            for (int t = 0; t < 3; t++) {
                if (dp[n_row][n_col][t][1] == row && dp[n_row][n_col][t][2] == col) continue;
                if (dp[n_row][n_col][t][0] > target_value) {
                    target_value = dp[n_row][n_col][t][0];
                }
            }
            dp[row][col][i][0] = target_value + graph[row][col];
            dp[row][col][i][1] = n_row; dp[row][col][i][2] = n_col; visit[row][col][i] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1][3][3];
        visit = new int[n + 1][m + 1][3]; // 0 : 아래 , 1: 오른쪽 2 : 왼쪽

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k][0] = -1000000001;
                }
            }
        }

        dp[n][m][0] = new int[]{graph[n][m], -1, -1};
        dfs(1, 1, 0, 0);
        int max = -1000000001;
        for (int k = 0; k < 3; k++) {
            max = Math.max(max, dp[1][1][k][0]);
        }
        System.out.println(max);


    }
}
