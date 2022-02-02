package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2 {
    static int n, m;
    static int[][] board;
    static ArrayList<ArrayList<int[]>> islands = new ArrayList<>();
    static int[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] infoShortest;
    static int numOfIsland = 0;
    static int[] parent;

    static int getParent(int node) {
        if(parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if(node1 < node2){
            parent[node2] = node1;
        }else{
            parent[node1] = node2;
        }
    }
    static void findIslandGroup(int row, int col, int group) {
        visit[row][col] = 1;
        islands.get(group).add(new int[]{row, col});
        for (int i = 0; i < 4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if (!inRange(nrow, ncol)) continue;
            if (board[nrow][ncol] != 1 || visit[nrow][ncol] == 1) continue;
            findIslandGroup(nrow, ncol, group);
        }
    }

    static boolean inRange(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    static int findWay(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            if (startCol < endCol) {
                for (int i = startCol + 1; i < endCol; i++) {
                    //가는 중간에 섬이 있으면
                    if (board[startRow][i] == 1) {
                        return -1;
                    }
                }
            } else {
                for (int i = endCol + 1; i < startCol; i++) {
                    //가는 중간에 섬이 있으면
                    if (board[startRow][i] == 1) {
                        return -1;
                    }
                }
            }
            return Math.abs(startCol - endCol) > 2 ? Math.abs(startCol - endCol) - 1 : -1 ;
        } else {
            if (startRow < endRow) {
                for (int i = startRow + 1; i < endRow; i++) {
                    //가는 중간에 섬이 있으면
                    if (board[i][startCol] == 1) {
                        return -1;
                    }
                }
            } else {
                for (int i = endRow + 1; i < startRow; i++) {
                    //가는 중간에 섬이 있으면
                    if (board[i][startCol] == 1) {
                        return -1;
                    }
                }
            }
            return Math.abs(startRow - endRow) > 2 ? Math.abs(startRow - endRow) - 1: -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int groupIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && visit[i][j] == 0) {
                    islands.add(new ArrayList<>());
                    findIslandGroup(i, j, groupIdx++);
                }
            }
        }
        numOfIsland = islands.size();
        parent = new int[numOfIsland];
        infoShortest = new int[numOfIsland][numOfIsland];
        for (int i = 0; i < numOfIsland; i++) {
            parent[i] = i;
            ArrayList<int[]> cur = islands.get(i);
            for (int j = i + 1; j < numOfIsland; j++) {
                ArrayList<int[]> next = islands.get(j);
                int min = -1;
//                System.out.printf("i : %d, j : %d\n",i,j);
                for (int k = 0; k < cur.size(); k++) {
                    int[] start = cur.get(k);
                    for (int d = 0; d < next.size(); d++) {
                        int[] end = next.get(d);
                        if (start[0] != end[0] && start[1] != end[1]) continue;
                        int result = findWay(start[0], start[1], end[0], end[1]);
                        if(result == -1) continue;
//                        System.out.printf("[%d, %d] <-> [%d, %d]\n",start[0],start[1],end[0],end[1]);
//                        System.out.println("result = " + result);
                        if(min < 0) min = result;
                        else min = Math.min(min, result);
                    }
                }
//                System.out.println("min = " + min);
                infoShortest[i][j] = min;
                infoShortest[j][i] = min;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < numOfIsland; i++) {
            for (int j = i + 1; j < numOfIsland; j++) {
                if(infoShortest[i][j] < 0)continue;
                pq.add(new int[]{i, j, infoShortest[i][j]});
            }
        }
        int check = 0;
        int solution = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int island1 = poll[0];
            int island2 = poll[1];
            int weight = poll[2];
            if(getParent(island1) == getParent(island2)) continue;
            union(island1, island2);
//            System.out.printf("%d, %d연결\n",island1,island2);
            solution += weight;
            if(++check == numOfIsland - 1)break;
        }
//        System.out.println("check = " + check);
        if(check + 1 != numOfIsland) solution = -1;
        System.out.println(solution);
    }
}
