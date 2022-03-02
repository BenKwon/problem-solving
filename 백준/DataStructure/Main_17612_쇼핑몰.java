package 백준.DataStructure;

import java.io.*;
import java.util.*;

public class Main_17612_쇼핑몰 {
    static int n, k;
    static ArrayList<Customer> customers = new ArrayList<>();

    static PriorityQueue<Customer> in = new PriorityQueue<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer c1, Customer c2) {
            if (c1.time == c2.time) {
                return c1.counter - c2.counter;
            } else {
                return c1.time - c2.time;
            }
        }
    });
    static PriorityQueue<Customer> out = new PriorityQueue<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer c1, Customer c2) {
            if (c1.time == c2.time) {
                return c2.counter - c1.counter;
            } else {
                return c1.time - c2.time;
            }
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customers.add(new Customer(id, w, 0));
            if (in.size() < k) {
                in.add(new Customer(id, w, i + 1));
            }
        }

        int idx = k;
        while (!in.isEmpty()) {
            Customer poll = in.poll();
            out.add(poll);
            if(idx >= n) continue;
            Customer next = customers.get(idx++);
            next.counter = poll.counter;
            next.time += poll.time;
            in.add(next);
        }

        long answer = 0;
        long multiply = 1;
        while(!out.isEmpty()){
            Customer poll = out.poll();
            answer += (multiply++) * poll.id;
        }
        bw.write(answer +"");
        bw.flush();
    }


    static class Customer {
        int id;
        int time;
        int counter;

        public Customer(int id, int time, int counter) {
            this.id = id;
            this.time = time;
            this.counter = counter;
        }
    }
}
