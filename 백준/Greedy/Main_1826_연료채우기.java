package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1826_연료채우기 {
    static class Station implements Comparable<Station> {
        int pos;
        int gas;

        public Station(int pos, int gas) {
            this.pos = pos;
            this.gas = gas;
        }

        @Override
        public int compareTo(Station station) {
            return this.pos - station.pos;
        }

        @Override
        public String toString() {
            return "Station{" +
                    "pos=" + pos +
                    ", gas=" + gas +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Station> stations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int gas = Integer.parseInt(st.nextToken());
            stations.add(new Station(pos, gas));
        }
        Collections.sort(stations);
        PriorityQueue<Station> pq = new PriorityQueue<>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o2.gas - o1.gas;
            }
        });
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int initGas = Integer.parseInt(st.nextToken());
        int index = 0;
        int curPos = 0;
        int accum = 0;
        int countVisit = 0;
//        System.out.println("dest" + dest);
        if(initGas >= dest){
            System.out.println("0");
            return;
        }
        while (true) {
            Station station = new Station(1000000000, 0);
            if(index < n) station = stations.get(index);
            while (station.pos <= accum + initGas) {
                pq.add(station);
                if(++index >= n) break;
                station = stations.get(index);
            }
            if (pq.isEmpty()) break;
            Station visitStation = pq.poll();
            if (curPos < visitStation.pos) curPos = visitStation.pos;
            accum += visitStation.gas;
            countVisit++;
//            System.out.println(visitStation.toString());
//            System.out.printf("accum , init gas : %d, %d\n",accum,initGas);
            if (accum + initGas >= dest) {
                System.out.println(countVisit);
                return;
            }
        }
        System.out.println("-1");
    }
}
