package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 5 15
 * 2 8
 * 2 10
 * 2 12
 * 2 10
 * 2 5
 */
public class Main_2841_외계인의기타연주 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer>[] lines = new PriorityQueue[7];
        for (int i = 1; i <= 6; i++) {
            lines[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int pr = Integer.parseInt(st.nextToken());
            while (true) {
                if (lines[line].isEmpty()) break;
                if (lines[line].peek() > pr) {
                    int poll = lines[line].poll();
                    result++;
                } else if(lines[line].peek() == pr){
                    lines[line].poll();
                    result--;
                    break;
                }    else{
                    break;
                }
            }
            lines[line].offer(pr);
            result++;
        }
        System.out.println(result);
    }
}
