package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 배_1092_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String crain = br.readLine();
        StringTokenizer crain_token = new StringTokenizer(crain);
        int m = Integer.parseInt(br.readLine());
        String box = br.readLine();
        StringTokenizer box_token = new StringTokenizer(box);
        ArrayList<Integer> crains = new ArrayList<>();
        ArrayList<Integer> boxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            crains.add(Integer.parseInt(crain_token.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(box_token.nextToken()));
        }

        Collections.sort(crains, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());
        int sol = 0;

        if(boxes.get(0) > crains.get(0)){
            System.out.println(-1);
            return;
        }

        while(!boxes.isEmpty()){
            int box_idx = 0 ;
            for(int i = 0 ; i < n;){
                int crain_ability = crains.get(i);
                if(box_idx  == boxes.size()) break;
                else if(crain_ability >= boxes.get(box_idx)){
                    boxes.remove(box_idx);
                    i++;
                }else box_idx++;
            }
            sol++;
//            System.out.println("sol = " + sol);
        }
        System.out.println(sol);
    }
}
