package programmers;
import java.util.*;

public class 타겟넘버 {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int n = numbers.length;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,numbers[0]));
        q.add(new Node(0, numbers[0] * -1));
        while(!q.isEmpty()){
            Node poll = q.poll();
            int level = poll.level;
            int sum = poll.sum;
            if(level == n - 1){
                if(sum == target) answer++;
                continue;
            }
            int next = numbers[level + 1];
            q.add(new Node(level + 1, sum + next));
            q.add(new Node(level + 1 , sum + (next * -1)));
        }
        return answer;
    }
    static class Node{
        int level;
        int sum;
        public Node(int level, int sum){
            this.level = level;
            this.sum = sum;
        }
    }
}
