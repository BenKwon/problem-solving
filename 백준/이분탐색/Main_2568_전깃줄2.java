package 백준.이분탐색;

import java.io.*;
import java.util.*;

public class Main_2568_전깃줄2 {
    static class Node{
        int value;
        int idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[500001];
        Arrays.fill(seq, -1);
        ArrayList<Node>[] track = new ArrayList[500001];
        ArrayList<Integer> solution = new ArrayList<Integer>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            seq[start] = end;
            max = Math.max(max, Math.max(start,end));
        }
        n = max;
        for (int i = 0; i <= n; i++) {
            track[i] = new ArrayList<>();
        }
        solution.add(seq[1]);
        track[0].add(new Node(seq[1], 1));
        for (int i = 2; i <= n; i++) {
            int next = seq[i];
            if(next < 0) continue;
            if(solution.get(solution.size() -1 )< next){
                solution.add(next);
                track[solution.size() - 1].add(new Node(next, i));
            }else{
                int idx = Collections.binarySearch(solution,next);
                if(idx < 0) idx = Math.abs(idx + 1);
                track[idx].add(new Node(next, i));
                solution.set(idx, next);
            }

        }
        int length = solution.size();
        //tracking
        ArrayList<Integer> way = new ArrayList<>();
        int lastIndex = track[length - 1].get(track[length - 1].size() - 1).idx;
        int lastValue = track[length - 1].get(track[length - 1].size() - 1).value;
        way.add(lastValue);
        HashSet<Integer> remain = new HashSet<>();
        remain.add(lastIndex);
        for (int i = length - 2; i >= 0; i--) {
            ArrayList<Node> nodes = track[i];
            int idx = -1;
            int value = -1;
            for (int j = nodes.size() - 1; j >= 0; j--) {
                Node node = nodes.get(j);
                if(node.idx < lastIndex){
                    idx = node.idx;
                    value = node.value;
                    break;
                }
            }
            lastIndex = idx;
            way.add(value);
            remain.add(idx);
        }
        int numRemove = 0;
        ArrayList<Integer> sol = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(seq[i] < 0 || remain.contains(i))continue;
            sol.add(i);
//            bw.write(i +"\n");
            numRemove++;
        }
        bw.write(numRemove + "\n");
        for(int i : sol){
            bw.write(i + "\n");
        }
        bw.flush();
    }
}
