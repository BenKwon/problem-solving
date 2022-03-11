package 백준.이분탐색;

import java.io.*;
import java.rmi.dgc.VMID;
import java.util.*;

public class Main_2110_공유기설치 {
    static int n, c;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
        solution();
    }
    public static void solution(){
        int low = 0;
        int high = 1_000_000_000;
        int sol = 0;
        while(low <= high){
            int mid = (low + high)/2;
            if(!check(mid)){
                high = mid - 1;
            }else{
                low = mid + 1;
                sol = Math.max(sol, mid);
            }
        }
        System.out.println(sol);
    }
    static boolean check(int range){
        int count = 1;
        int startValue = houses[0];
        for(int i = 1 ; i < n;i++){
            int nextValue = houses[i];
            int diff = Math.abs(nextValue-startValue);
            if(diff >= range) {
                startValue = houses[i];
                count++;
            }
        }

        return count >= c;
    }
}
