package 백준.이분탐색;

import java.io.*;
import java.util.*;

public class Main_14003_가장긴증가하는부분수열5 {
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
        ArrayList<Node>[] track = new ArrayList[n];
        ArrayList<Integer> solution = new ArrayList<Integer>();
        ArrayList<Integer> seq = new ArrayList();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq.add(Integer.parseInt(st.nextToken()));
            track[i] = new ArrayList<>();
        }
        solution.add(seq.get(0));
        track[0].add(new Node(seq.get(0),0));
        for (int i = 1; i < n; i++) {
            int next = seq.get(i);
            if(solution.get(solution.size()-1) < next){
                solution.add(next);
                track[solution.size() - 1].add(new Node(next,i));
                continue;
            }
            int idx = Collections.binarySearch(solution,next);
            if(idx < 0){
                idx = Math.abs(idx + 1);
            }
            track[idx].add(new Node(next,i));
            solution.set(idx, next);
        }
        int length = solution.size();
        //tracking
        ArrayList<Integer> way = new ArrayList<>();
        int lastIndex = track[length - 1].get(track[length - 1].size() - 1).idx;
        int lastValue = track[length - 1].get(track[length - 1].size() - 1).value;
        way.add(lastValue);
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
        }
        bw.write(length +"\n");
        for (int i = way.size() - 1; i >= 0; i--) {
            bw.write(way.get(i) +" ");
        }
        bw.flush();
    }
}
