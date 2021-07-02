package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드정렬하기_1715_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0 ; i < n ;i++){
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sol = 0;

        while(true){
            if(pq.size() >=2){
                int poll = pq.poll() + pq.poll();
                sol += poll;
                pq.offer(poll);
            }else{
                break;
            }
        }

        System.out.println(sol);
    }
}
