package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14867_물통 {
    static int a,b,c,d;
    static int[][] visita = new int[2][100001]; //a가 비어있거나 꽉찼을때
    static int[][] visitb = new int[100001][2]; //b가 비어있거나 꽉찼을때
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        solution();
    }
    static void solution(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visita[0][0] = 1;
        visitb[0][0] = 1;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int curA = poll.a;
            int curB = poll.b;
//            System.out.printf("a: %d, b: %d\n",curA,curB);
            int count = poll.count;
            if(curA == c && curB == d){
                System.out.println(count);
                return;
            }
            int checkA = poll.a;
            int checkB = poll.b;
            if(checkA == 0) checkA = 0;
            if(checkA == a) checkA = 1;
            if(checkB == 0 ) checkB = 0;
            if(checkB == b) checkB = 1;
            /** fill **/
            //a 물병 채우기
            if(visita[1][curB] == 0){
                visita[1][curB] = 1;
                if(checkB <= 1){
                    visitb[a][checkB] = 1;
                }
                q.add(new Node(a, curB, count + 1));
            }
            //b 물병 채우기
            if(visitb[curA][1] != 1){
                visitb[curA][1] = 1;
                if(checkA <= 1) visita[checkA][b] = 1;
                q.add(new Node(curA, b, count + 1));
            }

            /** 물병을 비우자 **/
            //a 물병 비우기
            if(visita[0][curB] != 1){
                visita[0][curB] =1;
                if(checkB <= 1) visitb[0][checkB] = 1;
                q.add(new Node(0, curB, count + 1));
            }
            //b 물병 비우기
            if(visitb[curA][0] != 1){
                visitb[curA][0] = 1;
                if(checkA <= 1) visita[checkA][0] = 1;
                q.add(new Node(curA, 0, count + 1));
            }
            /** 물 옮기기**/
            //a에서 b로 옮기기
            if(curA + curB <= b){
                if(visita[0][curA + curB] == 0){
                    visita[0][curA + curB] = 1;
                    if(curA + curB == b) visitb[0][1] = 1;
                    q.add(new Node(0, curA + curB, count + 1));
                }
            }else{
                int diff = (curA + curB) - b;
                if(visitb[diff][1] == 0){
                    visitb[diff][1] = 1;
                    q.add(new Node(diff, b, count + 1));
                }
            }

            //b에서 a로옮기기기
            if(curA + curB <= a){
                if(visitb[curA +curB][0] == 0){
                    visitb[curA + curB][0] = 1;
                    if(curA + curB == a) visita[1][0] = 1;
                    q.add(new Node(curA + curB, 0, count + 1));
                }
            }else{
                int diff = (curA + curB) - a;
                if(visita[1][diff] == 0){
                    visita[1][diff] = 1;
                    q.add(new Node(a, diff, count + 1));
                }
            }
        }
        System.out.println(-1);
    }
    static class Node{
        int a;
       int b;
        int count;
        public Node(int a, int b, int count) {
            this.a = a;
            this.b = b;
            this.count = count;
        }
    }
}
