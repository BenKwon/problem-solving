package 백준.Greedy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 택배_8980 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //마을수
        int c = scanner.nextInt(); //트럭용량
        int m = scanner.nextInt(); //박스 개수
        PriorityQueue<Baggage> baggage_info = new PriorityQueue<>(); //수하물 정보 (start ,end , weight)
        PriorityQueue<Baggage> stored_baggage = new PriorityQueue<>();
        int total_point = 0;
        int truck_have = 0;  //트럭이 현재 싣고있는 수하물의 무게 총 양
        HashMap<Integer, PriorityQueue> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int weight = scanner.nextInt();
            baggage_info.offer(new Baggage(start, end, weight));

        }

        int cur_pos = 1;
        Baggage baggage = baggage_info.poll(); // 실을지 말지 고민중인 수화물
        while (cur_pos < n + 1) {
            int empty = c - truck_have;
            /*짐 내려놓는 곳*/
            Iterator<Baggage> iterator1 = stored_baggage.iterator();
            while(iterator1.hasNext()){
                Baggage next = iterator1.next();
                if(next.end_point == cur_pos){
//                    stored_baggage.poll();
                    iterator1.remove();
                    truck_have -= next.weight;
                    total_point += next.weight;
                }
            }
            /*짐 싣는 부분*/
            while (baggage.start_point == cur_pos) {
                if (empty >= baggage.weight) {
                    stored_baggage.offer(baggage);
                    truck_have += baggage.weight;
                } else if (stored_baggage.size() == 0) {
                    baggage.weight = empty;
                    stored_baggage.offer(baggage);
                    truck_have += baggage.weight;
                } else {
                    Iterator<Baggage> iterator = stored_baggage.iterator();
                    while (iterator.hasNext() && baggage.weight > 0) {
                        Baggage target_baggage = iterator.next(); // 실려있는 화물
                        if (target_baggage.end_point >= baggage.end_point) { //새로 실으려는 화물이 더 가까운 마을에서 end
                            if(target_baggage.end_point == baggage.end_point && target_baggage.start_point == baggage.start_point){
                                continue;
                            }
                            if (target_baggage.weight > baggage.weight) { //새로 실으려는 화물의 총 량보다 실려있는 화물이 더 무거우면
//                                stored_baggage.poll();
                                iterator.remove();
                                target_baggage.weight -= baggage.weight;
                                stored_baggage.offer(target_baggage);
                                stored_baggage.offer(baggage);
                            } else {
                                int tmp = baggage.weight;
                                baggage.weight = target_baggage.weight;
                                stored_baggage.offer(baggage);
                                baggage.weight = tmp - target_baggage.weight;
                                iterator.remove();
//                                stored_baggage.poll();
                            }
                        }else{
                            if(empty > 0){
                                baggage.weight = empty;
                                stored_baggage.offer(baggage);
                                truck_have += empty;
                            }
                        }
                    }
                }
                baggage = baggage_info.poll();
            }
            cur_pos++;
        }


    }

    static class Baggage implements Comparable<Baggage> {
        int start_point;
        int end_point;
        int weight;

        public Baggage(int start_point, int end_point, int weight) {
            this.start_point = start_point;
            this.end_point = end_point;
            this.weight = weight;
        }

        @Override
        public int compareTo(Baggage o) {
            if (start_point == o.start_point) {
                return end_point - o.end_point;
            } else {
                return start_point - o.start_point;
            }
        }

        @Override
        public String toString() {
            return "Baggage{" +
                    "start_point=" + start_point +
                    ", end_point=" + end_point +
                    ", weight=" + weight +
                    '}';
        }
    }
}
