package programmers.카카오인턴십;
import java.util.*;

public class 길찾기게임 {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int num;
        Node parent;
        Node leftChild;
        Node rightChild;
        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
        @Override
        public int compareTo(Node o){
            if(this.y == o.y){
                return this.x - o.x;
            }else{
                return o.y - this.y;
            }
        }
    }

    static class Solution {
        static HashMap<Integer,Integer> map = new HashMap<>();
        static ArrayList<Node> nodes = new ArrayList<>();
        static ArrayList<int[]> levelInfo = new ArrayList<>();
        static int[] visit = new int[100001];
        static void dfs(int index){
            if(index == nodes.size() - 1) return;
            // System.out.println("=====================index : " + index);
            int curLevel = nodes.get(index).y;
            int curX = nodes.get(index).x;
            int levelIndex = map.get(curLevel);
            if(levelIndex + 1 >= levelInfo.size()) return;
            int[] info = levelInfo.get(levelIndex + 1); // 다음에에 대한 정보'
            int start = info[1];
            int end = info[2];

            for(int i = start ; i <= end ; i++){
                if(visit[i] == 1) continue;
                // System.out.println(" i : " + i );
                int nextX = nodes.get(i).x;
                if(nodes.get(index).leftChild !=null && nodes.get(index).rightChild != null) break;
                Boolean superpass = false;
                if(nextX < curX && nodes.get(index).leftChild == null){ //왼쪽 자식 노드
                    if(!possibleLeftChild(nodes.get(index), nextX)) {
                        continue;
                    }
                    nodes.get(index).leftChild = nodes.get(i);

                    nodes.get(i).parent = nodes.get(index);
                    visit[i] = 1;
                    dfs(i);
                }else if(curX < nextX && nodes.get(index).rightChild == null){ // 오른쪽 자식 노드
                    if(!possibleRightChild(nodes.get(index), nextX)) {
                        continue;
                    }
                    nodes.get(index).rightChild = nodes.get(i);
                    nodes.get(i).parent = nodes.get(index);
                    visit[i] = 1;
                    dfs(i);
                }

            }
        }
        static boolean possibleRightChild(Node node, int childX){
            // System.out.printf("start : [%d, %d]\n",node.x , node.y);
            while(true){
                if(node.parent == null) break;
                Node parentNode= node.parent;

                if(parentNode.leftChild != null){
                    Node leftChild =parentNode.leftChild;
                    if(leftChild.x == node.x && leftChild.y == node.y){
                        if(parentNode.x <= childX) return false;
                    }
                }
                node = parentNode;
            }
            return true;
        }
        static boolean possibleLeftChild(Node node, int childX){
            while(true){
                if(node.parent == null) break;
                Node parentNode= node.parent;
                if(parentNode.rightChild != null){
                    Node rightChild =parentNode.rightChild;
                    if(rightChild == node){
                        if(parentNode.x >= childX )return false;
                    }
                }
                node = parentNode;
            }
            return true;
        }
        static int seq;
        //루 왼 오
        static void preorder(Node node, int[] answer){
            answer[seq++] = node.num;
            // System.out.printf("answer[%d] : %d\n",seq, node.num);
            if(node.leftChild!= null)
                preorder(node.leftChild,answer);
            if(node.rightChild!=null)
                preorder(node.rightChild,answer);
        }
        static void postorder(Node node, int[] answer){

            if(node.leftChild!= null)
                postorder(node.leftChild,answer);
            if(node.rightChild!=null)
                postorder(node.rightChild,answer);
            answer[seq++] = node.num;

        }
        public int[][] solution(int[][] nodeinfo) {
            int[][] answer = {};
            int nodeNum = nodeinfo.length;
            answer = new int[2][nodeNum];
            for(int i = 0; i < nodeinfo.length ; i++){
                int x = nodeinfo[i][0];
                int y = nodeinfo[i][1];
                nodes.add(new Node(x,y, i + 1));
            }
            Collections.sort(nodes);
            int idx = 0;
            for(int i = 0 ; i < nodes.size(); i++){
                int y = nodes.get(i).y;
                if(map.containsKey(y)){
                    int position = map.get(y);
                    levelInfo.set(position,new int[]{y,levelInfo.get(position)[1],i});
                }else{
                    map.put(y,idx++);
                    levelInfo.add(new int[]{y,i,i});
                }
            }

            dfs(0);

            seq = 0;
            preorder(nodes.get(0), answer[0]);
            seq = 0;
            postorder(nodes.get(0),answer[1]);
            return answer;
        }
    }
}
