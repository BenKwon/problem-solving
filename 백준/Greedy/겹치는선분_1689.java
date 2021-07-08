package 백준.Greedy;

import javax.swing.text.MaskFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 비슷한 유형을 풀었던적있는데 뭐였는지 기억이 안남..
 * 각 시작점을 s 끝점을 e라고 하면 배열에 {s,1} {e,-1}로  추가한다.
 * 배열을 지점을 기준으로 오름차순으로 정렬하되 지점이 같으면 -1이 우선적으로 정렬 되게 만든다.
 *
 * 반복문을 돌면서 각 배열의 2번째위치 즉 1번 인덱스에 있는 값을 count에 더하고 현재 max값과 비교해서 max를 update할지 안할지 결정한다.
 * 각 배열 원소의 인덱스의 있는 값이 더해진 count값의 의미는 해당 지점에서부터 다음 지점 전까지의 간격에서 겹쳐져있는 선분의 갯수이다.
 * -1이 우선적으로 정렬되어야 하는 이유는 만약 {5,1}이라면 5~6지점 위치에 놓이기되는데 이때 {5,1} 이 추가되면 count 에 1이 더해진다
 * 하지만 5에서 끝나는 선분들 즉 {5,-1}인 지점들은 5~6지점 위치에 놓이지 않으므로 미리 빼준후 max값을 업데이트 해줘야 정상적으로 최대 겹치는 선분의 갯수가 계산된다.
 */
class 겹치는선분_1689_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] lines = new int[2 * n][2];
        for (int i = 0; i < 2 * n; i += 2) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            lines[i] = new int[]{Integer.parseInt(st.nextToken()), 1};
            lines[i + 1] = new int[]{Integer.parseInt(st.nextToken()), -1};

        }
        Arrays.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else
                    return o1[0] - o2[0];
            }
        });

        int count = 0;
        int max = 0;
        for (int i = 0; i < 2 * n; i++) {
            count += lines[i][1];
            max = Math.max(count, max);
        }
        System.out.println(max);

    }
}
