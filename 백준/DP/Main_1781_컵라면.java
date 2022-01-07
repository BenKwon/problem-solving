package 백준.DP;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Problem{
    int deadline;
    int ramen;

    public Problem(int deadline, int ramen) {
        this.deadline = deadline;
        this.ramen = ramen;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "deadline=" + deadline +
                ", ramen=" + ramen +
                '}';
    }
}

public class Main_1781_컵라면 {

    //Union Find 구현
    public static int getParent(int[] parent, int node){
        if(parent[node] == node )return  node;
        return parent[node] = getParent(parent, parent[node]);
    }
    public static void union(int[] parent, int nodeA, int nodeB){
        nodeA = getParent(parent, nodeA);
        nodeB = getParent(parent, nodeB);
        if(nodeA < nodeB){
            parent[nodeB] = nodeA;
        }else{
            parent[nodeA] = nodeB;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> pq = new PriorityQueue<>(new Comparator<Problem>(){
            @Override
            public int compare(Problem p1, Problem p2){
                if(p2.ramen == p1.ramen){
                    return p2.deadline - p2.deadline;
                }else{
                    return p2.ramen - p1.ramen;
                }
            }
        });
        int[] parent = new int[200001];
        int[] time = new int[200001];
        for (int i = 0; i < 200001; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            pq.add(new Problem(deadline, ramen));
        }

        int countRamen = 0;
        while(!pq.isEmpty()){
            Problem poll = pq.poll();
            int targetTime = getParent(parent, poll.deadline);
            if(time[targetTime] == 0 && targetTime > 0){
                time[targetTime] = poll.ramen;
                union(parent, targetTime, getParent(parent, targetTime - 1));
                countRamen += poll.ramen;
            }
            //            if(time[poll.deadline] == 0){
//                time[poll.deadline] = poll.ramen;
//                union(parent, poll.deadline, getParent(parent, poll.deadline - 1));
//            }else{
//                time[getParent(parent, poll.deadline)] = poll.ramen;
//                union(parent, getParent(parent, poll.deadline), getParent(parent, poll.deadline - 1));
//
//            }
        }
        System.out.println(countRamen);
    }
}
