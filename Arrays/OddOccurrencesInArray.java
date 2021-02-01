package Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OddOccurrencesInArray {
    public static void main(String[] args) {
        int[] A = {9, 3, 9, 3, 9, 7, 9};
        System.out.println("solution = : " + solution(A));
    }

    public static int solution(int[] A) {
        Map<Integer, Integer> log = new HashMap<>();
        for(int i : A){
            if(log.get(i) == null){
                log.put(i, 1);
            }
            else{
                log.replace(i, log.get(i)+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry : log.entrySet()){
            if(entry.getValue()%2 ==1){
                return entry.getKey();
            }
            
        }
        return 0;
    }


}

