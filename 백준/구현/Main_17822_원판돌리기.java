package 백준.구현;

import java.io.*;
import java.util.*;

public class Main_17822_원판돌리기 {
    static int n, m, t;
    static int[][] circles;
    static int[][] visit;

    static void rotateCircle(int num, int dir, int k) {
        int[] newCircle = new int[m];
        if (dir == 0) { //시계 방향
            for (int i = 0; i < m; i++) {
                int newIdx = (i + k) % m;
                newCircle[newIdx] = circles[num][i];
            }
            circles[num] = newCircle;
        } else { // 반시계 방향
            for (int i = 0; i < m; i++) {
                int newIdx = (i + (m - k)) % m;
                newCircle[newIdx] = circles[num][i];
            }
            circles[num] = newCircle;
        }
    }


    static int remove(int num, int idx, int count, int circleNum) {
        if (visit[num][idx] == 1) return 0;
        if (circles[num][idx] == -1000000) return 0;
        int removeCount = 0;
        if (count > 1) {
            removeCount++;
            circles[num][idx] = -1000000;
        }
//        System.out.printf("num :%d, idx :%d,count : %d, circlenum : %d\n", num, idx, count, circleNum);
        visit[num][idx] = 1;
        //같은 원판 오른쪽 인접
        int right = rightIdx(idx);
        if (circleNum == circles[num][right]) {
            removeCount += remove(num, right, count + 1, circleNum);
        }
        //같은 원판 왼쪽 인접
        int left = leftIdx(idx);
        if (circleNum == circles[num][left]) {
            removeCount += remove(num, left, count + 1, circleNum);
        }
        //다른 원판  안쪽
        if (num - 1 >= 1 && circleNum == circles[num - 1][idx]) {
            removeCount += remove(num - 1, idx, count + 1, circleNum);
        }
        //다른 원판 바깥쪽
        if (num + 1 <= n && circleNum == circles[num + 1][idx]) {
            removeCount += remove(num + 1, idx, count + 1, circleNum);
        }
        if (removeCount > 0) circles[num][idx] = -1000000;
        return removeCount;
    }

    static int rightIdx(int idx) {
        return (idx + 1) % m;
    }

    static int leftIdx(int idx) {
        return (idx + (m - 1)) % m;
    }

    static int leftIdxTest(int idx, int k) {
        return (idx + (m - k)) % m;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        circles = new int[n + 1][m];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                circles[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        extracted();
//        System.out.println("시작");
        for (int i = 0; i < t; i++) {
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            //x의 배수인 원판 loop
            for (int j = 1; x * j <= n; j++) {
                rotateCircle(x * j, d, k);
            }
//            System.out.println("회전시작");
//            extracted();
            //인접하면서 같은수 제거
            visit = new int[n + 1][m];
            int removeCount = 0;
            float average = 0;
            int totalNum = 0;
            for (int j = 1; j <= n; j++) {
                for (int q = 0; q < m; q++) {
                    if (circles[j][q] != -1000000) {
                        average += circles[j][q];
                        totalNum++;
                    }
                    removeCount += remove(j, q, 1, circles[j][q]);
//                    System.out.println("//");
                }
            }
//            System.out.println("removeCount = " + removeCount);
            if (removeCount == 0) {
//                System.out.println("totalNum = " + totalNum);
//                System.out.println("average = " + average);
                average = average / (float) totalNum;
//                System.out.println("average = " + average);
                for (int j = 1; j <= n; j++) {
                    for (int q = 0; q < m; q++) {
                        if (circles[j][q] == -1000000) continue;
                        if (circles[j][q] < average) {
                            circles[j][q]++;
                        } else if (circles[j][q] > average) {
                            circles[j][q]--;
                        }
                    }
                }

            }
//            extracted();
        }
        int sol = 0;
        for (int j = 1; j <= n; j++) {
            for (int q = 0; q < m; q++) {
                if (circles[j][q] == -1000000) continue;
                sol += circles[j][q];
            }
        }
        System.out.println(sol);
    }

    private static void extracted() {
        System.out.println("------------------------------------------");
        for (int j = 1; j <= n; j++) {
            System.out.printf("%d : { ", j);
            for (int q = 0; q < m; q++) {
                if (circles[j][q] == -1000000) System.out.printf("%c , ", 'x');
                else System.out.printf("%d , ", circles[j][q]);

            }
            System.out.println("}");
        }
        System.out.println("------------------------------------------");
    }
}
