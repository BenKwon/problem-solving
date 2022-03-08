package programmers;
import java.util.*;
import java.util.*;

public class 양과늑대 {
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        solution(info, edges);
    }
    static class Node{
        int id;
        int type; //wolf : 1, lmb: 0
        Node left;
        Node right;
        Node parent;
        public Node(int id,int type){
            this.id = id;
            this.type= type;
        }
    }
    static int n ;
    static Node[] nodes;
    static int[] visit;
    public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        n = info.length;
        visit = new int[n];
        nodes = new Node[n];
        for(int i = 0 ; i < n ; i++){
            nodes[i] = new Node(i,info[i]);
        }
        for(int i = 0 ; i < edges.length; i++){
            int parentId = edges[i][0];
            int childId = edges[i][1];
            Node parent = nodes[parentId];
            Node child = nodes[childId];
            if(parent.left == null){
                parent.left = child;
                child.parent =parent;
            }else{
                parent.right = child;
                child.parent = parent;
            }
        }
        visit[0] = 1;
        start(0,1,0);
        return answer;
    }
    static int solution = 0;
    static void start(int id, int sheep, int wolf){
//        System.out.println("id = " + id);
        if(sheep <= wolf) return;
        Node startNode = nodes[id];
        ArrayList<Integer> nexts = new ArrayList<>();
        HashSet<Integer> seqCheck = new HashSet<>();

        seqCheck.clear();
        if(startNode.left != null && visit[startNode.left.id] == 0){
            nexts.add(startNode.left.id);
            seqCheck.add(startNode.left.id);
        }
        if(startNode.right != null && visit[startNode.right.id] == 0){
            nexts.add(startNode.right.id);
            seqCheck.add(startNode.right.id);

        }
        for (int i = 0; i < n; i++) {
            if(visit[i] == 0){
                Node next = nodes[i];
                if(visit[next.parent.id] == 1 && !seqCheck.contains(i)){
                    nexts.add(i);
                }
            }
        }
        if(nexts.size()==0) {
            solution = Math.max(solution, sheep);
            return;
        }
        int[] sort = new int[nexts.size()];
        int[] tmpVisit = new int[nexts.size()]; //tmpVisit에  index 들어가는건 nexts의 index이지 노드의 id가아니다. visit는index가 노드의 id를 가르킨다.
        generateSeq(nexts,tmpVisit,sort,0,sheep,wolf);
    }
    static void generateSeq(ArrayList<Integer> nexts, int[] tmpVisit,int[] sort,int count,int sheep, int wolf){
        if(count == nexts.size()){
            for(int i = 0 ; i < sort.length ;i++){
                visit[sort[i]] = 1;
                if(nodes[sort[i]].type == 0) sheep++;
                else wolf++;
//                System.out.println("--------------------------------");
                start(sort[i],sheep,wolf);
//                System.out.println("--------------------------------");
                visit[sort[i]] = 0;
            }
            return;
        }
        for(int i = 0 ; i< nexts.size(); i++){
            if(tmpVisit[i] == 0){
                tmpVisit[i] = 1;
                sort[count] = nexts.get(i);
                generateSeq(nexts,tmpVisit,sort,count + 1,sheep,wolf);
                tmpVisit[i] = 0;
            }
        }
    }

}
