package programmers.카카오인턴십;

import java.util.Stack;

public class 아오 {
    static public String solution(int n, int k, String[] cmd) {
        Stack<Node> zNode = new Stack<>();
        Node root = new Node(0);
        Node curNode = root;
        for (int i = 1; i < n; i++) {
            Node node = new Node(i);
            curNode.next = node;
            node.prev = curNode;
            curNode = node;
        }
        //root tail 서로 연결 - 순환구조처럼
        Node tail = curNode;
        root.prev = tail;
        tail.next = root;
        curNode = root;

        //처음 선택된 노드로 curNode 업데이트
        while (k-- > 0) {
            curNode = curNode.next;
        }
        System.out.printf("haed node : %d \n",root.prev.data);
        System.out.printf("tail node : %d \n",tail.next.data);
        System.out.println("cur node : " + curNode.data);
        for (int i = 0; i < cmd.length; i++) {
            String[] s = cmd[i].split(" ");
            if (s.length == 1) {
                if (s[0].equals("C")) {//삭제C
                    curNode.prev.next = curNode.next;
                    curNode.next.prev = curNode.prev;
                    zNode.push(curNode);
                    //root가 삭제될경우 next노드가 root노드가됨
                    if (curNode == root) {
                        root = curNode.next;
                        curNode = root;
                    }
                    //tail노드가 삭제될 경우 prev노드가 tail노드가됨
                    else if (curNode == tail) {
                        tail = curNode.prev;
                        curNode = tail;
                    } else {
                        curNode = curNode.next;
                    }
                } else {//되돌리기Z
                    Node node = zNode.pop();
                    Node tmp = node.prev.next;
                    node.prev.next = node;
                    tmp.prev = node;
                    //되돌리고 난 후 root,tial 확인해서 업데이트
                    if (node.data < root.data)
                        root = node;
                    else if (node.data > tail.data)
                        tail = node;
                }
            } else {
                int num = Integer.parseInt(s[1]);
                if (s[0].equals("U")) {//위로이동U
                    while (num-- > 0) {
                        curNode = curNode.prev;
                    }
                } else {//아래로이동D

                }
            }
        }
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", root.data);
            root = root.next;
//            if(root.data == i) {
//                ans.append("O");
//                root=root.next;
//            }
//            else {
//                ans.append("X");
//            }
        }
        return ans.toString();

    }

    static class Node {
        Node prev;
        Node next;
        int data;

        Node(int data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }

    public static void main(String[] args) {
        String[] str = new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(solution(8, 2, str));
//        Integer a = 3;
//        Integer b =4;
//        if(a < b){
//            System.out.println("dsf");
//        }

    }

}
