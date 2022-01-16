package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1041_주사위 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dices = new int[6];
        long minForOne = Integer.MAX_VALUE;
        int maxIdx = 0;
        int diceSum =  0;
        int maxForOne = Integer.MIN_VALUE;
        for (int i = 0; i < 6; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
            minForOne = Math.min(minForOne, dices[i]);
            maxForOne = Math.max(maxForOne, dices[i]);
            diceSum += dices[i];
        }
        if(n == 1){
            System.out.println(diceSum - maxForOne);
            return;
        }
        //면 2개의 최소합
        long minForTwo = extractedTwo(dices);
        //면 3개의 최소합
        long minForThree = extractedThree(dices);
        long sum = (4 * minForThree) + (4 * minForTwo) +
                (4 * minForTwo * (n - 2)) + (minForOne * ((n - 2) * (n - 2))) + (4 * minForTwo * (n - 2))
                + (4 * minForOne * ((n - 2) * (n - 2))) + (4 * minForOne * (n - 2));

        System.out.println(sum);
    }

    private static long extractedThree(int[] dices) {
        int[][] checkListForThree = {{2,0,4},{2,0,1},{2,1,5},{2,4,5},
                {0,1,3},{0,3,4},{1,3,5},{3,4,5}};
        long minForThree = Integer.MAX_VALUE;
        for(int[] diceNums : checkListForThree){
            int sum = 0;
            for(int num : diceNums){
                sum += dices[num];
            }
            minForThree = Math.min(minForThree, sum);
        }
        return minForThree;
    }

    private static int extractedTwo(int[] dices) {
        int minForTwo= Integer.MAX_VALUE;
        HashMap<Integer, Integer> mapForTwo = new HashMap<>();
        mapForTwo.put(2, 3);
        mapForTwo.put(3, 2);
        mapForTwo.put(0,5);
        mapForTwo.put(5,6);
        mapForTwo.put(4, 1);
        mapForTwo.put(1, 4);
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if(mapForTwo.get(i).intValue() == j || mapForTwo.get(j).intValue() == i) continue;
                minForTwo = Math.min(minForTwo, dices[i] + dices[j]);
            }
        }
        return minForTwo;
    }
}
