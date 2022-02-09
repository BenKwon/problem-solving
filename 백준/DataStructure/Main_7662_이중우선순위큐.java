package 백준.DataStructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
public class Main_7662_이중우선순위큐 {
    static int k;
    static PriorityQueue<node> maxheap = new PriorityQueue<>((o1,o2)-> Long.compare(o2.priority,o1.priority));
    static PriorityQueue<node> minheap = new PriorityQueue<>((o1,o2)-> Long.compare(o1.priority,o2.priority));
    static int[] visit;
    static class node{
        long priority;
        int id;
        public node(long priority, int id){
            this.priority = priority;
            this.id = id;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {
            k = Integer.parseInt(br.readLine());
            visit = new int[k + 1];
            maxheap.clear();
            minheap.clear();
            int totalElement = 0; // 큐에 있는 원소의 개수
            int uniqueId = 0;

            for(int i = 0 ; i < k ; i++){
//                System.out.println("--------------------------------------");
                StringTokenizer st = new StringTokenizer(br.readLine());
                char operation = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());
                if (operation == 'I') { //Insert
                    node newNode = new node(value, uniqueId++);
                    maxheap.add(newNode);
                    minheap.add(newNode);
//                    System.out.printf("insert prior : %d, id :%d\n", newNode.priority, newNode.id);
                    totalElement++;
                }else{ //Delete
                    if(value == 1){ // 최댓값을 삭제합니다.
                        while(!maxheap.isEmpty()){
                            node poll = maxheap.poll();
//                            System.out.printf("poll.prior : %d, poll.id : %d\n",poll.id, poll.priority);
                            if(visit[poll.id] == 0){
                                totalElement--;
//                                System.out.printf("delete prior : %d, id :%d\n", poll.priority, poll.id);
                                visit[poll.id] = 1;
                                break;
                            }
                        }
                    }else if(value == -1){ //value -1
                        while(!minheap.isEmpty()){
                            node poll = minheap.poll();
                            if(visit[poll.id] == 0){
                                totalElement--;
//                                System.out.printf("delete prior : %d, id :%d\n", poll.priority, poll.id);
                                visit[poll.id] = 1;
                                break;
                            }
                        }
                    }
                }
//                System.out.println("totalElement = " + totalElement);
            }

            //동작 수행 끝
            if(totalElement == 0) {
                bw.write(("EMPTY\n"));
                continue;
            }
            //최댓값 출력
            while(!maxheap.isEmpty()){
                node poll = maxheap.poll();
                if(visit[poll.id] == 0){
                    bw.write(poll.priority +" ");
                    break;
                }
            }
            //최솟값 출력
            while(!minheap.isEmpty()){
                node poll = minheap.poll();
                if(visit[poll.id] == 0){
                    bw.write(poll.priority +"\n");
                    break;
                }
            }
        }
        bw.flush();
    }
}
