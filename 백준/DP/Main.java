package 백준.DP;
import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Step{
        int emoticon_num;
        int clipboard_num;
        int time;

        public Step(int emoticon_num, int clipboard_num, int time) {
            this.emoticon_num = emoticon_num;
            this.clipboard_num = clipboard_num;
            this.time = time;
        }
    }

    static boolean[][] visited;
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        visited = new boolean[2001][2001]; // 행: 화면 이모티콘의 개수, 열:클립보드 이모티콘 개수

        bfs();
    }

    public static void bfs() {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(1, 0, 0));

        while (!queue.isEmpty()) {
            Step now = queue.poll();

            int emoticon_num = now.emoticon_num;
            int clipboard_num = now.clipboard_num;
            int time = now.time;

            if(emoticon_num == S){
                System.out.println(time);
                return;
            }

            if(emoticon_num > 0 && emoticon_num < 2000){
                // 1. 복사
                if(!visited[emoticon_num][emoticon_num]){
                    visited[emoticon_num][emoticon_num] = true;

                    queue.offer(new Step(emoticon_num, emoticon_num, time + 1));
                }

                // 3. 삭제500
                if (!visited[emoticon_num - 1][clipboard_num]) {
                    visited[emoticon_num - 1][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num - 1, clipboard_num, time + 1));
                }
            }

            // 2. 붙여넣기
            if (clipboard_num > 0 && emoticon_num + clipboard_num < 2000) {
                if (!visited[emoticon_num+clipboard_num][clipboard_num]) {
                    visited[emoticon_num+clipboard_num][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num + clipboard_num, clipboard_num, time + 1));
                }
            }
        }
    }
}