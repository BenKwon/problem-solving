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
        HashSet<Integer> visit = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int lake = Integer.parseInt(st.nextToken());
            q.add(new pos(lake, lake));
            visit.add(lake);
        }
        int house = k;
        long sum = 0;
        while (!q.isEmpty()) {
            pos poll = q.poll();
            int index = poll.index;
            int sourceLake = poll.lake;
            if (!visit.contains(index - 1)) {
                q.add(new pos(sourceLake, index - 1));
                visit.add(index - 1);
                house--;
                sum += Math.abs(sourceLake - (index - 1));
            }
            if(house == 0){
                break;
            }
            if (!visit.contains(index + 1)) {
                q.add(new pos(sourceLake, index + 1));
                house--;
                visit.add(index + 1);
                sum += Math.abs(sourceLake - (index + 1));
            }
            if(house == 0){
                break;
            }
        }
        System.out.println(sum);
    }
}
