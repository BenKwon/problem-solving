package 백준.Greedy;

import java.util.Scanner;

public class 문자열화폐_17828 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int empty_box =  n;
        char[] box = new char[n];
        int max =26;
        if(x < 26){
            max = x;
        }
        if(max*n < x || x < n){
            System.out.println("!");
            return;
        }
        if(n == 1){
            char a = (char) (64 + x);
            System.out.println(a);
            return;
        }
        if(max + empty_box - 1 <= x){
            int idx;
            for(idx= n-1; max+empty_box -1<=x ; idx--){
                x -= max;
                box[idx] = (char) (max + 64);
                empty_box--;
            }

            for(int i = 0; empty_box > 1; i++){
                box[i] =(char) 65;
                x -= 1;
                empty_box--;
            }
            if(empty_box !=0){
                box[idx] = (char)(x+64);
            }

        }else{
            int idx;
            for(idx = 0; empty_box > 1; idx++){
                box[idx] = (char) 65;
                x -= 1;
                empty_box--;
            }
            if(empty_box !=0){
                box[idx] = (char)(x+64);
            }
        }

        for (char c : box) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
