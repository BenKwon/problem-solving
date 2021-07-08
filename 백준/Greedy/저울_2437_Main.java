package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울_2437_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for(int i=0 ; i < n ;i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);
        int min = weight[0];
        int max = weight[0];
        if(weight[0] != 1){
            System.out.println(1);
            return;
        }
        int solution = 1;
        for(int i = 1 ; i < n ;  i++){
//            System.out.println("==========================");
            int next = weight[i];
//            System.out.println("next = " + next);
//            System.out.println("max = " + max);
            if(min + next > max + 1 && next != max +1){
                solution = max + 1;
                break;
            }
            max += next;
            solution = max + 1;
        }

        System.out.println(solution);



    }
}
