package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1135_뉴스전하기 {
    static int n;
    static ArrayList<Integer>[] connectInfo;
    static int[] childNum;
    static int solution = 0;
    static int dfs(int num) {
        if (connectInfo[num].size() == 0) return 0;
        int count = 0;
        ArrayList<Integer> timeSeq = new ArrayList<>();
        for (int child : connectInfo[num]) {
            timeSeq.add(dfs(child));
        }
        Collections.sort(timeSeq,Collections.reverseOrder());
        for (int i = 0; i < timeSeq.size(); i++) {
            count = Math.max(count, timeSeq.get(i) + i);
        }
        return count + 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        connectInfo = new ArrayList[n + 1];
        childNum = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            connectInfo[parent].add(i);
        }
        System.out.println(dfs(0));
    }
}
