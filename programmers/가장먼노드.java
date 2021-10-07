package programmers;

import java.util.*;

class 가장먼노드 {
    static int n;
    static ArrayList<Integer>[] connect_info;
    static class Node{
        int v;
        int count;

        public Node(int v , int count){
            this.v = v;
            this.count = count;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        this.n = n;
        connect_info = new ArrayList[n + 1];
        for(int i = 0 ; i < n + 1 ; i++){
            connect_info[i] = new ArrayList();
        }
        for(int i = 0 ; i < edge.length ; i++){
            connect_info[edge[i][0]].add(edge[i][1]);
            connect_info[edge[i][1]].add(edge[i][0]);
        }
        Queue<Node> q = new LinkedList<>();
        int[] visit = new int[n+1];
        q.add(new Node(1, 0));
        visit[1] = 1;
        HashMap<Integer, Integer> info = new HashMap<>();
        int max = 0;
        while(!q.isEmpty()){
            Node poll = q.poll();
            max = Math.max(max,poll.count);
            if(info.get(poll.count) == null){
                info.put(poll.count,1);
            }else{
                info.put(poll.count, info.get(poll.count) + 1);
            }
            ArrayList<Integer> connects = connect_info[poll.v];
            for(int i = 0 ; i < connects.size(); i++){
                int next =connects.get(i);
                if(visit[next] ==  1) continue;
                visit[next] = 1;
                q.add(new Node(next, poll.count + 1));
            }
        }
        answer = info.get(max);
        return answer;
    }
}