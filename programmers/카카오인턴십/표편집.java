package programmers.카카오인턴십;

import javax.xml.parsers.SAXParser;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 표편집 {
    //int n,
    static public String solution(int n, int k, String[] cmd) {
        Stack<Node> trash = new Stack<>();
        Node head = new Node(0);
        Node curNode = head;
        for (int i = 1; i < n; i++) {
            Node next = new Node(i);
            curNode.next = next;
            next.prev = curNode;
            curNode = next;
        }
        Node tail = curNode;
        tail.next = head;
        head.prev = tail;
        curNode = head;
        while (k-- > 0) {
            curNode = curNode.next;
        }

        System.out.printf("haed node : %d \n",head.prev.data);
        System.out.printf("tail node : %d \n",tail.next.data);
        System.out.println("cur node : " + curNode.data);
        for (int i = 0; i < cmd.length; i++) {
            char[] chars = cmd[i].toCharArray();
//            System.out.printf("command : %c\n",chars[0]);
            if (chars.length == 1) {
                if (chars[0] == 'C') {
                    System.out.println("hi1");
                    curNode.prev.next = curNode.next;
                    curNode.next.prev = curNode.prev;
                    trash.push(curNode);
                    if (curNode == head) {
                        head = curNode.next;
                        curNode = head;
                    }else if(curNode == tail){
                        tail = curNode.prev;
                        curNode = tail;
                    }else{
                        curNode = curNode.next;
                    }
                } else { //"Z" 되돌리기
                    Node node = trash.pop();
                    Node tmp = node.prev.next;
                    node.prev.next = node;
                    tmp.prev = node;
                    if(node.data <  head.data){
                        head = node;
                    }
                    else if(node.data >  tail.data){
                        tail = node;
                    }
                }
            } else {
                int move = chars[2] -48;
                if(chars[0] == 'U'){
                    System.out.println("hi2");
                    for (int m = 0; m < move; m++) {
                        curNode = curNode.prev;
                    }
                }else{ //'D'
                    System.out.println("hi3");

                    for (int m = 0; m < move; m++) {
                        curNode = curNode.next;
                    }
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(i == head.data){
                sb.append("O");
                head = head.next;
            }else{
                sb.append("X");
            }
        }
        return sb.toString();
    }

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        String[] str =new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

        solution(8, 2, str);
    }
}
