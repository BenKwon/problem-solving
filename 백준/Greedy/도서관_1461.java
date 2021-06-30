package 백준.Greedy;

import java.util.*;

//골드 5
public class 도서관_1461 {

        public static int m;
        public static int turn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        PriorityQueue<Integer> negative = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            int book_pos = sc.nextInt();
            if (book_pos < 0) {
                negative.offer(Math.abs(book_pos));
            } else {
                positive.offer(book_pos);
            }
        }

        if(negative.size() != 0 && positive.size() != 0){
            if(negative.peek() > positive.peek()){
                turn = -1;
            }else turn = 1;
        }
        int total= 0;
        if(negative.size() != 0 || positive.size() != 0){
            if(negative.size() != 0 && positive.size() != 0){
                total += solution(positive, 1);
                total += solution(negative, -1);
            }else if(positive.size() ==0){
                turn = -1;
                total += solution(negative, -1);
            }else{
                turn = 1;
                total += solution(positive, 1);
            }
        }
        System.out.println(total);
//        int len = negative.size();

    }
    //turn -> 양수, 음수 큐를 합한 리스트중 절댓값이 가장큰 값이 양수에 속하면 양수 음수에 속하면 음수
    //target -> 지금 solution함수를 실행시킨 큐가 음수큐인지 양수큐인지
    public static int solution(PriorityQueue<Integer> book_pos, int target){
        int sol = 0;
        int t = 0;
        while (true) {
            int book = 0;
            int len = Math.min(book_pos.size(), m);
            for (int i = 0; i < len; i++) {
                if(i==0){
                    book = book_pos.poll();
                }else{
                    book_pos.poll();
                }
            }
            if(t==0 && target == turn){
                sol += book;
            }else {
                sol += book * 2;
            }
            if (book_pos.size() == 0) {
                break;
            }
            t++;
        }
        return sol;
    }

}
