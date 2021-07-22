package 백준.DP;

import java.util.ArrayList;

public class sampel {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        
        list.set(2,6);
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
            
        }
    }
}
