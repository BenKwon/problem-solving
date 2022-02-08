package programmers.카카오인턴십;
import java.util.*;
public class 무지의먹방라이브 {
    static class Solution {
        static class node{
            int idx;
            int num;
            public node(int idx, int num){
                this.idx = idx;
                this.num = num;
            }
        }
        static int[] parent;
        static int getParent(int node){
            if(parent[node] == node) return node;
            return parent[node] = getParent(parent[node]);
        }
        static void union(int node1, int node2){
            node1 =getParent(node1);
            node2 =getParent(node2);
            if(node1 < node2){
                parent[node1] = node2;
            }else{
                parent[node2] = node1;
            }
        }
        public int solution(int[] food_times, long k) {
            int answer = 0;
            PriorityQueue<node> pq = new PriorityQueue<>((a,b)->a.num - b.num);
            long turn = 0;
            long time = k;
            parent = new int[food_times.length + 1];
            for(int i = 0 ; i < food_times.length; i++){
                pq.add(new node(i, food_times[i]));
            }
            for (int i = 0; i < food_times.length + 1; i++) {
                parent[i] = i;
            }
            long seq = 0;
            boolean find = false;
            while(!pq.isEmpty()){
                node peek = pq.peek();
                int num = peek.num;
                int idx =peek.idx;
                long quot = time / pq.size();
                long remain = time % pq.size();
                if(quot < num - turn){
                    seq = remain + 1;
                    //solution
                    find =true;
                    break;
                }else{
                    time -= pq.size() * (num - turn);
                    turn = num;
                    union(idx , idx + 1);
                    while( pq.peek().num == num){
//                        System.out.printf("num :%d, pq.peek().num : %d\n",num ,pq.peek().num);
                        node tmpPoll = pq.poll();
                        union(tmpPoll.idx, tmpPoll.idx + 1);
                        if(pq.isEmpty()) break;
//                        System.out.printf("union %d , %d\n",tmpPoll.idx, tmpPoll.idx + 1);
                    }
                }
            }

            if(!find) return -1;
//            System.out.println("----------------");
//            System.out.println("seq = " + seq);
            for(int i = 0 ; i < food_times.length;){
                int parent = getParent(i);
                if(i != parent) i = parent;
                else{
                    i++;
                    answer++;
                    if(answer == seq) {
                        answer = i;
                        break;
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{3, 1, 2}, 4));
    }
}
