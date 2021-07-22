package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가장 큰 정사각형
 * 1 2  3  4
 * 5 6  7  8
 * 9 10 11 12
 *
 * grapy의 가장 마지막행과 가장 마지막 열은 초기화 되어있는 상태이다.
 * 그 이유는 자신을 기준으로 오른쪽, 아래쪽, 오른쪽 대각선을 보고 더 큰 정사각형이
 * 만들어 질수 있는지 확인을 해야하는데 마지막 행과 열은 더이상 확인할수있는 원소가 없으므로
 * 이미 입력된 값 자체가 그 자리에서 만들수있는 정사각형 변 길이의 최댓 값이다.
 * 그다음 n 과 m의 값에서 -1을 뺀 곳에서 다시 시작한다.
 * 즉 graph[n-1][m-1]이 0이 아니라면
 * n-1,m-1을 기준으로 오른쪽,아래쪽,대각선을 확인해서 그 3개의 값중 가장 최솟값에서 1을 더한값으로 바꿔준다.
 * 그 다음 grpah[n-1][m-1]을 기준으로 열과 행을 모두 같은방법으로 update시켜준다.
 * 위 그래프에서 예를 들면 7자리가 update되면 6,5 그리고 3 순서대로 혹은 3그리고 6,5 순으로
 * update시켜준다 . 그 이유은 오른쪽 아래쪽 대각선쪽이 모두 최신값으로 update되어있어야 알맞은 값으로 자기 자신을
 * update할수 있기 때문임임
*
 */
public class Main_가장큰정사각형_1915 {
    public static int n, m;
    public static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                int elem = Integer.parseInt(String.valueOf(str.charAt(j)));
                graph[i][j] = elem;
            }
        }
        int k = Math.min(n, m);
        int tmpn = n;
        int tmpm = m;
        n-=2;
        m-=2;
        int max = 0;
        while(n >= 0 && m>= 00){
            if(graph[n][m] != 0){
                int min = Math.min(graph[n+1][m], Math.min(graph[n][m+1],graph[n+1][m+1]));
                graph[n][m] = min + 1;
                max = Math.max(max, min + 1);
            }
            for(int col = m - 1; col >=0 ;col--){
                if(graph[n][col] == 0) continue;
                int min = Math.min(graph[n+1][col], Math.min(graph[n][col+1],graph[n+1][col+1]));
                graph[n][col] = min+ 1;
                max = Math.max(max, min + 1);
            }
            for(int row = n-1 ; row >=0 ;row--){
                if(graph[row][m] == 0) continue;
                int min = Math.min(graph[row+1][m], Math.min(graph[row][m+1],graph[row+1][m+1]));
                graph[row][m] = min + 1;
                max = Math.max(max, min + 1);

            }
            n--;m--;
        }

        if(max == 0){
            for(int i = 0;i< tmpn ; i++){
                for (int j = 0; j < tmpm; j++) {
                    max = Math.max(max, graph[i][j]);
                }
            }
        }

        System.out.println(max*max);
    }



}
