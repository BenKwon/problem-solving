package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Main_8983_사냥꾼 {
    static int m,n,l;
    static ArrayList<Integer> shootPlace = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            shootPlace.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(shootPlace);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(check(x,y)) {
//                System.out.printf("x : %d , y : %d\n",x,y);
                answer++;
            }
        }
        System.out.println(answer);
    }

    static boolean check(int x, int y) {
        if(y > l) return false; // x좌표에 닿을 수 없는 경우
        int remain = l - y;
        int low = 0;
        int high = m - 1;
        while(low <= high){
            int mid = (low + high) / 2;
//            System.out.printf("low : %d, high : %d\n",low,high);
            int value = shootPlace.get(mid);

            if(value == x) return true;
            if(value < x){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        //결국 low값이 나올 것임
        int[] checkSide = {-1, 0, 1};
        for (int i = 0; i < 3; i++) {
            int nextPos = low + checkSide[i];
            if(nextPos < 0 || nextPos >= m) continue;
            int diff = Math.abs(shootPlace.get(nextPos) - x);
            if(diff <= remain) return true;
        }
        return false;
    }
}
