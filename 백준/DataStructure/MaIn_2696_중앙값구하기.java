package 백준.DataStructure;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MaIn_2696_중앙값구하기 {
    static int m;
    static ArrayList<Integer> seq;
    static ArrayList<Integer> sol;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            m = Integer.parseInt(br.readLine());
            int quotient = m / 10;
            int remainder = m % 10;
            seq = new ArrayList<>();
            sol = new ArrayList<>();
            StringTokenizer st;
            if(quotient > 0){
                for (int i = 0; i < quotient; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < 10; j++) {
                        seq.add(Integer.parseInt(st.nextToken()));
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < remainder; i++) {
                seq.add(Integer.parseInt(st.nextToken()));
            }

            solution();
            //정답 출력
            int size = sol.size();
            quotient = size / 10;
            remainder = size % 10;
            int idx = 0;
            bw.write(size + "\n");
            if(quotient > 0){
                for (int i = 0; i < quotient; i++) {
                    for (int j = 0; j < 10; j++) {
                        bw.write(sol.get(idx++) +" ");
                    }
                    bw.write("\n");
                }
            }
            for (int i = 0; i < remainder; i++) {
                bw.write(sol.get(idx++) +" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
    static void solution() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i <= seq.size(); i++) {
            int next = seq.get(i -1);
            if(i % 2 == 0){ // 짝수 번째 일때
                minHeap.add(next);
            }else{ // 홀수 번째 일때때
                maxHeap.add(next);
            }
            try{
                //큐 최적화
                int minPeek = minHeap.peek();
                int maxPeek = maxHeap.peek();
                if(minPeek < maxPeek){
                    minHeap.poll();
                    maxHeap.poll();
                    maxHeap.add(minPeek);
                    minHeap.add(maxPeek);
                }
            } catch (Exception e){

            }

            if(i % 2 != 0){
                sol.add(maxHeap.peek());
            }
        }
    }
}
