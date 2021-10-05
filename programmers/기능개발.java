package programmers;

import java.util.*;

/**
 * Queue, PriorityQueue
 *
 * 내가 생각한 모법 답안
 * https://skd03052.tistory.com/192
 */
class 기능개발 {
    static class node{
        int prog;
        int speed;
        int seq;

        public node(int prog, int speed, int seq){
            this.prog = prog;
            this.speed = speed;
            this.seq = seq;
        }
    }
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int total = progresses.length;
        Queue<node> q = new LinkedList<>();
        for(int i = 0 ; i < progresses.length ; i++){
            q.offer(new node(progresses[i], speeds[i],i + 1));
        }
        int day =1;
        int[] store = new int[total + 1];
        PriorityQueue<Integer> com = new PriorityQueue<>();
        ArrayList<Integer> sol = new ArrayList<>();
        while(!q.isEmpty()){
            Queue<node> tmp = new LinkedList<>();
            PriorityQueue<Integer> tmp2 = new PriorityQueue<>();

            while(!q.isEmpty()){
                node poll = q.poll();
                int n_prog = poll.prog + poll.speed;
                if(n_prog >= 100){
                    com.offer(poll.seq);
                }else{
                    tmp.offer(new node(n_prog,poll.speed,poll.seq));
                }
            }
            int size = com.size();
            int num = 0;
            for(int i = 0 ; i < size ; i++){
                int poll = com.poll();
                boolean pass = true;
                for(int j = poll - 1; j > 0 ; j--){
                    if(store[j] == 0){
                        pass = false;
                        break;
                    }
                }
                if(pass){
                    store[poll] = 1;
                    num++;
                }else tmp2.offer(poll);
            }
            com = tmp2;
            if(num > 0){
                sol.add(num);
            }
            q = tmp;
            day++;
        }
        answer = new int[sol.size()];
        for(int s = 0 ; s < sol.size(); s++){
            answer[s] = sol.get(s);
        }
        return answer;
    }
}