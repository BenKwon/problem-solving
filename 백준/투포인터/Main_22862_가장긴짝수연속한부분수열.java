package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_22862_가장긴짝수연속한부분수열 {
    static class Node{
        int start;
        int end;
        int count;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
            this.count = end - start + 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Integer> seq = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            seq.add(num);
        }
        int count = 0;
        int start = -1;
        ArrayList<Node> evens = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = seq.get(i);
            if(num % 2 == 0){ // 짝수
                if(count == 0){
                    start = i;
                }
                count++;
            }else{ // 홀수
                if(count > 0){
                    int end = i - 1;
                    evens.add(new Node(start, end));
                }
                count = 0;
            }
        }
        if(count > 0){
            int end = n - 1;
            evens.add(new Node(start, end));
        }
//        for(Node node : evens){
//            System.out.printf("[%d, %d] : %d\n",node.start,node.end,node.count);
//        }
        start = 0;
        if(evens.isEmpty()){
            System.out.println(0);
            return;
        }
        int sum = evens.get(0).count;
        int max = sum;
        int remove = 0;
        for (int end = 1; end < evens.size(); end++) {
            Node cur =  evens.get(end);
            remove += cur.start - evens.get(end -1 ).end - 1;
//            System.out.printf("start : %d, end : %d, remove : %d\n",start, end,remove);
            if(remove > k){ // start,sum 갱신해야함
                int newStart = -1;
                for (int s = start; s < end; s++) {
                    int btw = evens.get(s+1).start - evens.get(s).end - 1;
                    remove -= btw;
                    if(remove <= k){
                        newStart = s + 1;
                        break;
                    }
                }
                if(remove == 0){
                    start = end;
                    sum = cur.count;
                }else{
                    for (int p = start; p < newStart; p++) {
                        sum -= evens.get(p).count;
                    }
                    start = newStart;
                    sum += cur.count;
                }
            }else{
                sum += cur.count;
            }
//            System.out.printf("sum : %d \n",sum);
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
