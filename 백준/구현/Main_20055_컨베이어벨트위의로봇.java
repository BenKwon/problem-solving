package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] a = new int[n * 2][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
        }

        boolean finish = false;
        int level = 1;
        while (!finish) {
            //1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            int[][] tmp = a.clone();
            for (int i = 0; i < n * 2; i++) {
                if (i == 0) {
//                    System.out.printf("tmp[%d] : [%d, %d] \n", 2*n - 1, tmp[2*n - 1][0], tmp[2*n - 1][1]);
                    a[i] = tmp[2*n - 1].clone();
                } else
                    a[i] = tmp[i - 1].clone();
            }

            //도착한 로봇 하차
            if (a[n - 1][1] == 1) {
                a[n - 1][1] = 0;
            }
            //2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동가능하다면 이동
            //화물은 n번째에서 항상 내리기 때문에 1~(n-1)구간 내 코드에서는 0~(n-2)구간을 뒤에서부터 확인한다.
            for (int i = n - 2; i >= 0; i--) {
                if (a[i][1] == 1 && a[i + 1][1] == 0) {
                    if (a[i + 1][0] > 0) {
                        a[i][1] = 0;
                        a[i + 1][1] = 1;
                        a[i + 1][0]--;
                    }
                }
            }
            //도착한 로봇 하차
            if (a[n - 1][1] == 1) {
                a[n - 1][1] = 0;
            }
            //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (a[0][1] == 0 && a[0][0] > 0) {
                a[0][1] = 1;
                a[0][0]--;
            }

            //4. 내구도가 0인 칸의 개수가 k개 이상이라면 과정을 종료한다.
            int sum_zero = 0;
            for (int i = 0; i < n * 2; i++) {
                if (a[i][0] == 0) {
                    sum_zero++;
                }
            }
            if (sum_zero >= k) {
                break;
            }
            level++;
        }
        System.out.println(level);

    }
}
