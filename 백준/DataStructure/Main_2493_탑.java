package 백준.DataStructure;

import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class Main_2493_탑 {
    static class Node{
        int index;
        int num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int[] sol = new int[n + 1];
        Stack<Node> stack =new Stack<>();
        for (int i = n; i >= 1; i--) {
            int next = seq[i];
            while(!stack.isEmpty()){
                if(stack.peek().num <= next){
                    Node pop = stack.pop();
                    sol[pop.index] = i;
                }else{
                    break;
                }
            }
            stack.push(new Node(i, next));
        }
        for (int i = 1; i <= n; i++) {
            bw.write(sol[i] +" ");
        }
        bw.flush();
    }
}
