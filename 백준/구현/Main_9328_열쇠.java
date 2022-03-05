package 백준.구현;

import java.io.*;
import java.util.*;

public class Main_9328_열쇠 {
    static int n, m;
    static char[][] board;
    static HashSet<Character> keys = new HashSet<>();
    static ArrayList<pos> start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class pos {
        int row;
        int col;
        int mask;

        public pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
//        char a = 'A';
//        System.out.printf("%c",a + 32);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new char[n][m];
            start = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = str.charAt(j);
                    if ((i == 0 || j == 0 || i == n - 1 || j == m - 1)
                            &&(board[i][j] != '*')) {
                        start.add(new pos(i, j));
                    }
                }
            }
            String str = br.readLine();
            keys.clear();
            if(str.charAt(0) != '0'){
                for (int i = 0; i < str.length(); i++) {
                    char next = str.charAt(i);
                    keys.add((char) (next - 32));
                }
            }
//            for(char key : keys){
//                System.out.println("key : " + key);
//            }
            findKeys();
            bw.write(findDocs() + "\n");
        }
        bw.flush();
    }

    public static int findKeys() {
        Queue<pos> q = new LinkedList<>();
        int[][] visit = new int[n][m];
        while(true){
//            System.out.println("--------------------------------");
            //초기화
            q.clear();
            visit = new int[n][m];
            int findNewKey = 0;
            for(pos p : start){
                int row = p.row;
                int col = p.col;
                char next = board[row][col];
                if(next =='*')continue;
                if((isBigAlphabet(next)) && !keys.contains(next)){
                    continue;
                }
                if(isSmallAlphabet(next)){
                    //키를 발견하면 set에 대문자 'A'(알파벳)을 넣는다
                    keys.add((char)(board[row][col] - 32));
                }
                q.add(new pos(row, col));
                visit[row][col] = 1;
            }

            while(!q.isEmpty()){
                pos poll = q.poll();
                int row = poll.row;
                int col = poll.col;
//                System.out.printf("row :%d , col : %d\n",row,col);
                for (int i = 0; i < 4; i++) {
                    int nrow = row + dx[i];
                    int ncol = col + dy[i];
                    if(!inRange(nrow,ncol)) continue;
                    if(visit[nrow][ncol] == 1) continue;
                    char next = board[nrow][ncol];
                    if(next =='*')continue;
                    if(isBigAlphabet(next) && !keys.contains(next)){
                        continue;
                    }
                    if(isSmallAlphabet(next)){
                        if(!keys.contains((char)(next - 32))){
                            findNewKey++;
//                            System.out.println("find! = " + next);
                            keys.add((char)(next - 32));
                        }
                    }
                    visit[nrow][ncol] = 1;
                    q.add(new pos(nrow, ncol));
                }
            }
//            System.out.println("findNewKey = " + findNewKey);
            if(findNewKey == 0) break;
        }

        return 0;
    }

    public static int findDocs() {
        Queue<pos> q = new LinkedList<>();
        int[][] visit = new int[n][m];
        int answer = 0;
        for (pos p : start) {
            int row = p.row;
            int col = p.col;
            char next = board[row][col];
            if (next == '*') continue;
            if (isBigAlphabet(next) && !keys.contains(next)) {
                continue;
            }
            if (isSmallAlphabet(next)) {
                //키를 발견하면 set에 대문자 'A'(알파벳)을 넣는다
                keys.add((char) (board[row][col] - 32));
            }
            q.add(new pos(row, col));
            visit[row][col] = 1;
        }

        while (!q.isEmpty()) {
            pos poll = q.poll();
            int row = poll.row;
            int col = poll.col;
//            System.out.printf("row :%d , col : %d\n", row, col);
            if(board[row][col] == '$') answer++;
            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if (!inRange(nrow, ncol)) continue;
                if (visit[nrow][ncol] == 1) continue;
                char next = board[nrow][ncol];
                if (next == '*') continue;
                if (isBigAlphabet(next) && !keys.contains(next)) {
                    continue;
                }
                visit[nrow][ncol] = 1;
                q.add(new pos(nrow, ncol));
            }
        }
        return answer;
    }
    static boolean inRange(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }
    static boolean isSmallAlphabet(char alphabet){
        if (('a' <= alphabet && alphabet <= 'z')) {
            return true;
        }
        return false;
    }

    static boolean isBigAlphabet(char alphabet){
        if (('A' <= alphabet && alphabet <= 'Z')) {
            return true;
        }
        return false;
    }
}
