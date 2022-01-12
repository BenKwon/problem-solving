package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_10775_공항 {
    static int[] parents;
    static int getParent(int gate) {
        if(parents[gate] == gate) return gate;
        return parents[gate] = getParent(parents[gate]);
    }
    static void union(int gate1, int gate2){
        gate1 = getParent(gate1);
        gate2 = getParent(gate2);
        if(gate1 > gate2){
            parents[gate1] = parents[gate2];
        }else{
            parents[gate2] = parents[gate1];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        parents = new int[g + 2];
        for (int i = 0; i <= g + 1; i++) {
            parents[i] = i;
        }

        int numOfDocking = 0;
        for (int i = 0; i < p; i++) {
            int endGate = Integer.parseInt(br.readLine());
            int gateNum = getParent(endGate);
            if(gateNum <= 0) break;
            numOfDocking++;
            union(gateNum, gateNum - 1);
        }

        System.out.println(numOfDocking);

    }
}
