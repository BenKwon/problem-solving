package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 통학버스_2513_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        ArrayList<int[]> apart_left = new ArrayList<>();
        ArrayList<int[]> apart_right = new ArrayList<>();
        int replace = 0;
        if(s>0){
            replace = -1 * s;
        }else if(s <0){
            replace = s;
        }
        int left_total = 0;
        int right_total = 0;
        for(int i = 0; i < n ;i ++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int pos = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(pos > s){ // right
                right_total += num;
                apart_right.add(new int[]{pos + replace,num});
            }else if(pos < s){ // left
                left_total += num;
                apart_left.add(new int[]{Math.abs(pos + replace), num});
            }
        }
        Collections.sort(apart_right, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Collections.sort(apart_left, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int sol = 0;
        //왼쪽, 아파트 단지 위치들이 음수
        if(apart_left.size()>0){
            while(left_total > 0){
                int max_bus = k;
                int i = apart_left.size() - 1;
                sol += apart_left.get(i)[0]*2;
                while(max_bus > 0 && i >= 0){
                    if(max_bus >= apart_left.get(i)[1]){
                        max_bus -= apart_left.get(i)[1];
                        left_total -= apart_left.get(i)[1];
                        apart_left.remove(i);
                    }else if(max_bus < apart_left.get(i)[1]){
                        apart_left.set(i,new int[]{
                                apart_left.get(i)[0], apart_left.get(i)[1] - max_bus
                        });
                        left_total -= max_bus;
                        max_bus = 0;
                    }
                    i--;
                }
            }

        }



        //오른쪽
        if(apart_right.size()>0){
            while(right_total> 0){
                int max_bus = k;
                int i = apart_right.size() - 1;
                sol += apart_right.get(i)[0]*2;
                while(max_bus > 0 && i >= 0){
                    if(max_bus >= apart_right.get(i)[1]){
                        max_bus -= apart_right.get(i)[1];
                        right_total -= apart_right.get(i)[1];
                        apart_right.remove(i);
                    }else if(max_bus < apart_right.get(i)[1]){

                        apart_right.set(i,new int[]{
                                apart_right.get(i)[0], apart_right.get(i)[1] - max_bus
                        });
                        right_total -= max_bus;
                        max_bus = 0;
                    }
                    i--;
                }

            }
        }

        System.out.println(sol);
        br.close();
    }
}
