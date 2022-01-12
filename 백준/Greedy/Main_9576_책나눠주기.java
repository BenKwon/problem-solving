package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_9576_책나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //책 수
            int n = Integer.parseInt(st.nextToken());
            //사람 수
            int m = Integer.parseInt(st.nextToken());
            int[] takeBook = new int[m];
            //책 별 가능한 사람 리스트
            ArrayList<Integer>[] listForEachBook = new ArrayList[1001];
            HashMap<Integer, Integer> deadlineMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                listForEachBook[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                deadlineMap.put(i, end);
                for (int j = start; j <= end; j++) {
                    listForEachBook[j].add(i);
                }
            }
            int sol = 0;
            for (int i = 0; i < n; i++) {
                int minDeadline =  10000;
                int minDeadlinePersonNum = 10000;
                for (int person : listForEachBook[i]) {
                    if(takeBook[person] == 1) continue;
                    int deadline = deadlineMap.get(person);
                    if(deadline < minDeadline){
                        minDeadline = deadline;
                        minDeadlinePersonNum = person;
                    }
                }
                if(minDeadline == 10000){
                    continue;
                }else{
                    takeBook[minDeadlinePersonNum] = 1;
                    sol++;
                }
            }
            System.out.println(sol);
        }


    }
}
