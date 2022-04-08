package 백준.트리;

import jdk.swing.interop.SwingInterOpUtils;

import javax.lang.model.SourceVersion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2233_사과나무 {
    static int n;
    static int a,b;
    static int index = 0;
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String seq = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        nodes = new Node[(2*n) + 1];
        nodes[0] = new Node(index++, null, new ArrayList<>());
        Stack<Node> stack = new Stack<>();
        stack.push(nodes[0]);
        for (int i = 0; i < seq.length(); i++) {
            char next = seq.charAt(i);
            if(next =='0'){
                Node parent = stack.peek();
                Node newNode = new Node(index++, parent, new ArrayList<>());
                parent.children.add(newNode);
                stack.push(newNode);
                nodes[i + 1] = newNode;
            }else{
                nodes[i + 1] = stack.pop();
            }
        }
        Node aNode = nodes[a];
        Node bNode = nodes[b];
        HashSet<Integer> parents = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        //aNode 부모 탐색
        q.add(aNode);
        while (!q.isEmpty()) {
            Node poll = q.poll();
//            System.out.println(poll.toString());
            parents.add(poll.id);
            if(poll.parent != null){
                q.add(poll.parent);
            }
        }
        //bNode 부모 탐색하면서 최소 공통 조상 탐색
        q.clear();
        q.add(bNode);
//        System.out.println("----------------------");
        Node commonParent = new Node(39820, null, new ArrayList<>());
        while (!q.isEmpty()) {
            Node poll = q.poll();
//            System.out.println(poll.toString());
            if(parents.contains(poll.id)){
                commonParent = poll;
                break;
            }
            if(poll.parent != null){
                q.add(poll.parent);
            }
        }
        int ansA = 0;
        int ansB = 0;
        for (int i = 0; i < nodes.length; i++) {
            if(nodes[i].id == commonParent.id){
                if(ansA == 0){
                    ansA = i;
                }else{
                    ansB = i;
                }
            }

        }
        System.out.printf("%d %d",ansA,ansB);
    }
    static class Node{
        int id;
        Node parent;
        ArrayList<Node> children;

        public Node(int id, Node parent, ArrayList<Node> children) {
            this.id = id;
            this.parent = parent;
            this.children = children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }
}
