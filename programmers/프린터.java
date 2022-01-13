package programmers;

import java.util.*;
//https://programmers.co.kr/learn/courses/30/lessons/42587
public class 프린터 {
    static class Node{
        int index;
        int prior;
        public Node(int index, int prior){
            this.index = index;
            this.prior = prior;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] nodePrior = new int[10];
        Queue<Node> q =new LinkedList<>();

        for(int i = 0 ; i < priorities.length ; i++){
            int prior = priorities[i];
            q.add(new Node(i,prior));
            nodePrior[prior]++;
        }
        while(!q.isEmpty()){
            Node poll = q.poll();
            int pollPrior = poll.prior;
            boolean canPrint = true;
            for(int i = pollPrior + 1 ; i  < 10 ; i++){
                if(nodePrior[i] > 0){
                    canPrint = false;
                    break;
                }
            }
            if(canPrint){
                answer++;
                nodePrior[pollPrior]--;
                if(poll.index == location){
                    break;
                }
            }else{
                q.add(poll);
            }
        }
        return answer;
    }

}
