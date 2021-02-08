package programmers;

//깊이/너비 우선탐색(DFS/BFS)
public class 네트워크 {
    public static int[] visit; //0 : not visited, 1 : visted
    public static int network = 0; // 네트워크 개수
    public static int total; // n
    public static int[][] computer; // 컴퓨터 사본

    public static void main(String[] args) {
        int[][] cpu = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(3,cpu));
    }

    public static int solution(int n, int[][] computers) {
        visit = new int[n];
        total = n;
        computer = computers;
        for (int i = 0; i < n; i++) {
            if (visit[i] == 0) {
                connect(i);
                network++;
            }
        }
        int answer = network;
        return answer;
    }

    public static void connect(int cpu_num) {
        if (visit[cpu_num] != 0) {
            return;
        } else {
            visit[cpu_num] = 1;
            for (int i = 0; i < total; i++) {
                if (i != cpu_num && computer[cpu_num][i] == 1) {
                    connect(i);
                }
            }
        }
    }
}
