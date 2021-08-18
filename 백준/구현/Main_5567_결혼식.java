package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_5567_결혼식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> connect_info = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            connect_info.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            connect_info.get(f1).add(f2);
            connect_info.get(f2).add(f1);
        }
        HashSet<Integer> invited = new HashSet<>();

        ArrayList<Integer> sang_friends = connect_info.get(1);
        for (Integer sang_friend : sang_friends) {
            invited.add(sang_friend);
            ArrayList<Integer> f_of_f = connect_info.get(sang_friend);
            for (Integer f : f_of_f) {
                invited.add(f);
            }
        }
        if(invited.contains(1)){
            invited.remove(1);
        }
//        for (Integer integer : invited) {
//            System.out.println("integer = " + integer);
//
//        }
        System.out.println(invited.size());

    }
}
