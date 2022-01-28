package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_22302_수고르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(seq);
        int solution = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int find = seq.get(i) + m;
            int i1 = Collections.binarySearch(seq, find);
//            System.out.printf("seq: %d, find : %d, index : %d\n",seq.get(i),find,i1);
            if(i1 > 0){
                solution = m;
                break;
            }else{
                i1 = Math.abs(i1) - 1;
                if(i1 < n ){
                    solution = Math.min(solution, seq.get(i1) - seq.get(i));
                }
            }
        }
        System.out.println(solution);
    }
}
