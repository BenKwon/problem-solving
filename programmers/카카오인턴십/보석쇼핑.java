package programmers.카카오인턴십;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 보석쇼핑 {
    static public int[] solution(String[] gems) {
        int[] answer = {};
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> bag = new LinkedList<>();
        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }
        int n = set.size();
        int cur = 0;
        set = new HashSet<>();
        int bottom = 0;
        int[] sol = new int[]{0,Integer.MAX_VALUE};
        for (int i = 0; i < gems.length; i++) {
            String gem = gems[i];
            set.add(gem);
            bag.add(gem);
            if (map.get(gem) == null) {
                map.put(gem, 1);
            } else {
                map.put(gem, map.get(gem) + 1);
            }
            if (set.size() == n) {
                while (true) {
                    String peek = bag.peek();
                    if (map.get(peek) > 1){
                        bag.poll();
                        map.put(peek, map.get(peek) - 1);
                        bottom++;
                    }else{
                        break;
                    }
                }
                int before = calculate_length(sol);
                int candidate = calculate_length(new int[]{bottom, i});
//                System.out.printf("bef : %d , cand : %d \n",before,candidate);
                if(before > candidate){
                    sol[0] = bottom;
                    sol[1] = i;
                }
            }
        }
        return sol;
    }
    public static int calculate_length(int[] term){
        return Math.abs(term[1] - term[0]);
    }
    public static void main(String[] args) {
        int[] solution = solution(new String[]{"XYZ", "XYZ", "XYZ"});
        System.out.printf("[%d,%d]\n",solution[0] + 1,solution[1] + 1);


    }
}
