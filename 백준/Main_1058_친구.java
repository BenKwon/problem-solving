package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1058_친구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        int[][] visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            String next = br.readLine();
            for (int j = 0; j < next.length(); j++) {
                if (next.charAt(j) == 'Y') {
                    graph[i][j] = 1;
                } else {
                    graph[i][j] = 0;
                }
            }
        }

        int maxNumOfFriend = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int numOfFriend = 0 ;
            for (int j = 0; j < n; j++) {
                if(graph[i][j] == 1){
                    numOfFriend++;
                    for (int k = 0; k < n; k++) {
                        if(i != k && graph[j][k]  == 1 && graph[i][k] == 0 && visit[i][k] == 0){
                            visit[i][k] = 1;
                            numOfFriend++;
                        }
                    }
                }

            }
            maxNumOfFriend = Math.max(maxNumOfFriend, numOfFriend);
        }

        System.out.println(maxNumOfFriend);

    }
}
