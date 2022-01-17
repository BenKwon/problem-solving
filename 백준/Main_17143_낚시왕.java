package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main_17143_낚시왕 {
    static int R, C, M;
    static PriorityQueue<Node>[] pq;
    static PriorityQueue<Node>[] pqTemp;
    static class Node  {
        int row;
        int col;
        int speed;
        int direction;
        int size;
        int time;

        public Node(int row, int col, int speed, int direction, int size, int time) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", speed=" + speed +
                    ", direction=" + direction +
                    ", size=" + size +
                    ", time=" + time +
                    '}';
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue[C + 1];
        for (int i = 1; i <= C; i++) {
            pq[i] = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.row == o2.row){
                        return o2.size - o1.size;
                    }
                    return o1.row - o2.row;

                }
            });
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Node node = new Node(r, c, s, d, z, 0);
            pq[c].add(node);

        }
        int sum = 0;
        for(int i = 1; i <= C; i++) {
            System.out.println("time : " + i);
            if(!pq[i].isEmpty()){
                Node poll = pq[i].poll();
                System.out.println("catch " + poll.toString());
                while(true){
                    if(pq[i].isEmpty()) break;
                    Node peek = pq[i].peek();
                    if (peek.row == poll.row && peek.col == poll.col) {
                        pq[i].poll();
                    }else{
                        break;
                    }
                }
                int row = poll.row;
                int col = poll.col;
                int time = poll.time;
                sum += poll.size;
            }
            sharkMove(i);
        }
        System.out.println(sum);
    }

    static void sharkMove(int time) {
        pqTemp = new PriorityQueue[C + 1];

        for (int i = 1; i <= C; i++) {
            if(pqTemp[i] == null){
                pqTemp[i] = new PriorityQueue<>(
                        new Comparator<Node>() {
                            @Override
                            public int compare(Node o1, Node o2) {
                                if(o1.row == o2.row){
                                    return o2.size - o1.size;
                                }
                                return o1.row - o2.row;
                            }
                        }
                );
            }

            System.out.println(" i : " + i);
            System.out.println(pq[i].size());
            while(!pq[i].isEmpty()){
                Node next =pq[i].poll();
                while(true){
                    if(pq[i].isEmpty()) break;
                    Node peek = pq[i].peek();
                    if (peek.row == next.row && peek.col == next.col) {
                        pq[i].poll();
                    }else{
                        break;
                    }
                }
                System.out.println(next.toString());
                int direction = next.direction;
                int speed = next.speed;
                int row = next.row;
                int col = next.col;

                if (direction == 1) { // 상
                    int diff = row - 1;
                    int result;
                    int remain = speed - (diff);
                    if (speed <= diff) {
                        result = row - speed;
                    } else {
                        int quote = remain / (R - 1);
                        int remainder = remain % (R - 1);
                        if (quote % 2 == 0) {
                            int start = 1;
                            result = start + remainder;
                            next.direction = 2;
                        } else {
                            int start = R;
                            result = start - remainder;
                        }
                    }
                    next.time++;
                    next.row = result;
                    System.out.println("1 result : " + next.toString());


                    pqTemp[i].add(next);
                } else if (direction == 2) { // 하
                    int diff = R - row ;
                    int result;
                    int remain = speed - (diff);
                    if (speed <= diff) {
                        result = row + speed;
                    } else {
                        int quote = remain / (R - 1);
                        int remainder = remain % (R - 1);
                        if (quote % 2 == 0) {
                            int start = R;
                            result = start - remainder;
                            next.direction = 1;
                        } else {
                            int start = 1;
                            result = start + remainder;
                        }
                    }
                    next.time++;
                    next.row = result;
                    System.out.println("2 result : " + next.toString());

                    pqTemp[i].add(next);

                } else if (direction == 3) { // 우
                    int diff = C - col ;
                    int result;
                    int remain = speed - (diff);
                    if (speed <= diff) {
                        result = col + speed;
                    } else {
                        int quote = remain / (C - 1);
                        int remainder = remain % (C - 1);
                        if (quote % 2 == 0) {
                            int start = C;
                            result = start - remainder;
                            next.direction = 4;
                        } else {
                            int start = 1;
                            result = start + remainder;
                        }
                    }
                    next.time++;
                    next.col = result;
                    System.out.println("3 result : " + next.toString());

                    if(result != col){
                        if(pqTemp[result] == null){
                            pqTemp[result] = new PriorityQueue<>(
                                    new Comparator<Node>() {
                                        @Override
                                        public int compare(Node o1, Node o2) {
                                            if(o1.row == o2.row){
                                                return o2.size - o1.size;
                                            }
                                            return o1.row - o2.row;
                                        }
                                    }
                            );
                        }
                        pqTemp[result].add(next);
                    }else{
                        pqTemp[i].add(next);
                    }

                } else { // 좌
                    int diff = col - 1 ;
                    int result;
                    int remain = speed - (diff);
                    if (speed <= diff) {
                        result = col - speed;
                    } else {
                        int quote = remain / (C - 1);
                        int remainder = remain % (C - 1);
                        if (quote % 2 == 0) {
                            int start = 1;
                            result = start + remainder;
                            next.direction = 3;
                        } else {
                            int start = C;
                            result = start - remainder;
                        }
                    }
                    next.time++;
                    next.col = result;
                    System.out.println(" 4 4result : " + next.toString());

                    if(result != col){
                        if(pqTemp[result] == null){
                            pqTemp[result] = new PriorityQueue<>(
                                    new Comparator<Node>() {
                                        @Override
                                        public int compare(Node o1, Node o2) {
                                            if(o1.row == o2.row){
                                                return o2.size - o1.size;
                                            }
                                            return o1.row - o2.row;
                                        }
                                    }
                            );
                        }
                        pqTemp[result].add(next);

                    }else{
                        pqTemp[i].add(next);
                    }
                }
            }

        }
        pq = pqTemp;
    }
}
