package 백준.DFSxBFS;

import java.util.*;
import java.io.*;

public class Main_18513_샘터 {

    static class pos {
        int lake;
        int index;

        public pos(int lake, int index) {
            this.lake = lake;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Queue<pos> q = new LinkedList();
        //방문한 좌표 기록
        HashSet<Integer> visit = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int lake = Integer.parseInt(st.nextToken());
            q.add(new pos(lake, lake));
            //큐에 샘터 좌표 add
            visit.add(lake);
        }

        //남은 하우스 개수
        int house = k;
        //불행도의 합
        long sum = 0;

        while (!q.isEmpty()) {
            pos poll = q.poll();
            int index = poll.index; //현재 좌표
            int sourceLake = poll.lake; //기준 샘터 좌표
            if (!visit.contains(index - 1)) { //현재 좌표 기준 왼쪽 방문 여부 확인
                q.add(new pos(sourceLake, index - 1));
                visit.add(index - 1); // 방문처리
                house--;
                sum += Math.abs(sourceLake - (index - 1)); // 불행도 더하기
            }
            if(house == 0){ //모든 집을 다 지었으면 종료
                break;
            }
            if (!visit.contains(index + 1)) { //현재 좌표 기준 오른쪽 방문 여부 확인
                q.add(new pos(sourceLake, index + 1));
                visit.add(index + 1); //방문처리
                house--;
                sum += Math.abs(sourceLake - (index + 1)); // 불행도 더하기
            }
            if(house == 0){
                break;
            }
        }
        System.out.println(sum);
    }
}
