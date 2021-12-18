package 백준.구현;

import java.util.*;
import java.io.*;

public class Main_12100_2048 {
    static int n;
    static int[][] originBoard, copyBoard;
    static int max = Integer.MIN_VALUE;
    public static void dfs(int count, int direction){
        if(count == 6) return; // 6번 이상 갈 수 없음.
        int[][] tmpBoard = new int[n][n]; // 임시 저장 보드 생성
        if(direction == 0){ // 상
            shiftUp(copyBoard);
        }else if(direction ==1){ // 하
            shiftDown(copyBoard);
        }else if(direction == 2) { // 좌
            shiftLeft(copyBoard);
        }else{ // 우
            shiftRight(copyBoard);
        }
        recordMaxVal(copyBoard);
        initCopyBoard(copyBoard,tmpBoard); // 임시 저장 보드에 저장
        for (int i = 0; i < 4; i++) {
            dfs(count + 1, i);
            initCopyBoard(tmpBoard, copyBoard);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        originBoard = new int[n][n];
        copyBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                originBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        initCopyBoard(originBoard,copyBoard);
        for (int i = 0; i < 4; i++) {
            dfs(1, i);
            initCopyBoard(originBoard,copyBoard);
        }
        System.out.println(max);
    }

    public static void recordMaxVal(int[][] board){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }
    public static void initCopyBoard(int[][] originBoard, int[][] targetBoard){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                targetBoard[i][j] = originBoard[i][j];
            }
        }
    }
    public static void shiftDown(int[][] board) {
        for (int i = 0; i < n; i++) { //i는 열을 순환한다
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> seq = new ArrayList<>();
            for (int j = n - 1; j >= 0; j--) {
                if (board[j][i] == 0) continue;
                q.add(board[j][i]);
                board[j][i] = 0;
            }
            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    int poll = q.poll();
                    int peek = q.peek();
                    if (poll == peek) {
                        q.poll();
                        seq.add(poll + poll);
                    } else {
                        seq.add(poll);
                    }
                } else {
                    seq.add(q.poll());
                }
            }
            for (int j = 0; j < seq.size(); j++) {
                board[n - j - 1][i] = seq.get(j);
            }
        }
    }

    public static void shiftUp(int[][] board) {
        for (int i = 0; i < n; i++) { //i는 열을 순환한다
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> seq = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 0) continue;
                q.add(board[j][i]);
                board[j][i] = 0;
            }
            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    int poll = q.poll();
                    int peek = q.peek();
                    if (poll == peek) {
                        q.poll();
                        seq.add(poll + poll);
                    } else {
                        seq.add(poll);
                    }
                } else {
                    seq.add(q.poll());
                }
            }
            for (int j = 0; j < seq.size(); j++) {
                board[j][i] = seq.get(j);
            }
        }
    }

    public static void shiftRight(int[][] board) {
        for (int i = 0; i < n; i++) { //i 는 행
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> seq = new ArrayList<>();
            for (int j = n-1; j >= 0; j--) {
                if(board[i][j] == 0) continue;
                q.add(board[i][j]);
                board[i][j] = 0;
            }
            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    int poll = q.poll();
                    int peek = q.peek();
                    if (poll == peek) {
                        q.poll();
                        seq.add(poll + poll);
                    } else {
                        seq.add(poll);
                    }
                } else {
                    seq.add(q.poll());
                }
            }
            for (int j = 0; j < seq.size(); j++) {
                board[i][n-j-1] = seq.get(j);
            }
        }
    }

    public static void shiftLeft(int[][] board) {
        for (int i = 0; i < n; i++) { //i 는 행
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> seq = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0) continue;
                q.add(board[i][j]);
                board[i][j] = 0;
            }
            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    int poll = q.poll();
                    int peek = q.peek();
                    if (poll == peek) {
                        q.poll();
                        seq.add(poll + poll);
                    } else {
                        seq.add(poll);
                    }
                } else {
                    seq.add(q.poll());
                }
            }
            for (int j = 0; j < seq.size(); j++) {
                board[i][j] = seq.get(j);
            }
        }
    }

}
