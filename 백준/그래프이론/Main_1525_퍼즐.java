package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_1525_퍼즐 {
    static int[][] graph;
    static int answer = Integer.MAX_VALUE;
    static int[] rowMove = {-1, 1, 0 , 0};
    static int[] colMove = {0, 0, -1 , 1};
    static HashSet<String> set = new HashSet();
    static class pos{
        String str;
        int count;

        public pos(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
    static boolean validation(int row, int col) {
        if(row < 0 || row >= 3 || col < 0 || col >= 3) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row = 0;
        int col = 0;
        graph = new int[3][3];
        String init ="";
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                init += graph[i][j];
            }
        }
        int[][] storeBoard = new int[3][3];
        Queue<pos> q = new LinkedList<>();
        set.add(init);
        q.add(new pos(init, 0));
        int solution = - 1;
        while (!q.isEmpty()) {
            pos poll = q.poll();
            int tmp = poll.str.indexOf('0');
            int pollRow = tmp / 3;
            int pollCol  = tmp % 3;
            if(poll.str.compareTo("123456780") == 0){
                solution = poll.count;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nrow = pollRow + rowMove[i];
                int ncol = pollCol + colMove[i];
                if(!validation(nrow,ncol)) continue;
                int targetIndex = (pollRow * 3) + (rowMove[i] * 3) + pollCol%3 + colMove[i];
//                if(i==0){
//                    targetIndex = (pollRow * 3) - 3 + pollCol%3;
//                }else if(i == 1){
//                    targetIndex = (pollRow * 3) + 3 + pollCol%3;
//                }else if(i == 2){
//                    targetIndex = pollRow * 3 + pollCol%3 - 1;
//                }else{
//                    targetIndex = pollRow * 3 + pollCol%3 + 1;
//                }
                char targetNum = poll.str.charAt(targetIndex);
                String newStr = poll.str;
                newStr = newStr.replace('0', 'c');
                newStr = newStr.replace(targetNum, '0');
                newStr= newStr.replace('c',targetNum);
                if(!set.contains(newStr)){
                    set.add(newStr);
                    q.add(new pos(newStr, poll.count + 1));
                }
            }
        }
        System.out.println(solution);
    }
}
