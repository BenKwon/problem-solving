package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_20922_겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> seq = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(st.nextToken());
            seq.add(next);
//            if (!map.containsKey(next)) {
//                map.put(next, new ArrayList<>());
//            }
//            map.get(next).add(i);
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int start = 0;
        int max = Integer.MIN_VALUE;
        for (int end = 0; end < n; end++) {
            int num = seq.get(end);
            if(!map.containsKey(num)){
                map.put(num, 1);
//                indexMap.put(num, end);
            }else{
                map.put(num, map.get(num) + 1);
            }

            if(map.get(num) > k ){
                boolean find = false;
                int newStart = -1;
                for (int i = start; i < end; i++) {
                    int target = seq.get(i);
//                    map.put(target, map.get(target) - 1);
                    if(!find && target == num){
                        find = true;
                        newStart = i + 1;
                        break;
                    }
                }
//                if(k > 1) newStart = newStart + 1;
//                else newStart = end;
                for (int i = start; i < newStart; i++) {
                    int target = seq.get(i);
                    map.put(target, map.get(target) - 1);
                }
                start = newStart;
            }else{
                max = Math.max(max, end - start + 1);
            }
            indexMap.put(num, end);
        }


        System.out.println(max);
    }
}
