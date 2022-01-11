package 백준.구현;

import java.util.*;
import java.io.*;

public class Main_14890_경사로 {
    static int[][] board;
    static int n , l;

    static void copyRow(int row ,int[] rows){
        for (int i = 0; i < n; i++) {
            rows[i] = board[row][i];
        }
    }
    static void copyCol(int col ,int[] cols){
        for (int i = 0; i < n; i++) {
            cols[i] = board[i][col];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        System.out.println(horizontal());
//        System.out.println(vertical());

        System.out.println(horizontal() + vertical());
    }

    static int horizontal(){
//        System.out.println("-----------------------");
        int sol = 0;
        for (int i = 0; i < n; i++) {
            int[] check = new int[n];
            int[] seq = new int[n];
            int continuous  = 0 ;
            int beginNum = 0;
            copyRow(i, seq);
            boolean forward = true;
            //forward
            for (int j = 0; j < n; j++) {
                int next = seq[j];
                if(continuous == 0){
                    continuous++;
                    beginNum = next;
                }else if(beginNum == next){
                    continuous++;
                }else{
                    if (next - beginNum == 1) {
                        if(continuous >= l){
                            forward = drawStarToLeft(check, seq, j);
                            continuous = 1;
                            beginNum = next;
                        }else{
                            forward = false;
                        }
                    }else if(next - beginNum < 0){
                        continuous = 1;
                        beginNum = next;
                    }else{
                        forward = false;
                    }
                }
                if(!forward) break;
            }
            //reverse
            continuous = 0;
            beginNum = 0;
            for (int j = n -1; j >= 0; j--) {
                int next = seq[j];
                if(continuous == 0){
                    continuous++;
                    beginNum = next;
                }else if(beginNum == next){
                    continuous++;
                }else{
                    if (next - beginNum == 1) {
                        if(continuous >= l){
                            forward = drawStarToRight(check, seq, j);
                            continuous = 1;
                            beginNum = next;
                        }else{
                            forward = false;
                        }
                    }else if(next - beginNum < 0){
                        continuous = 1;
                        beginNum = next;
                    }else{
                        forward = false;
                    }
                }
                if(!forward) break;
            }
            if(forward) {
                sol++;
            }
        }
        return sol;
    }

    static int vertical(){
//        System.out.println("-----------------------");
        int sol = 0;
        for (int i = 0; i < n; i++) {
            int[] check = new int[n];
            int[] seq = new int[n];
            int continuous  = 0 ;
            int beginNum = 0;
            copyCol(i, seq);
            boolean forward = true;
            for (int j = 0; j < n; j++) {
                int next = seq[j];
                if(continuous == 0){
                    continuous++;
                    beginNum = next;
                }else if(beginNum == next){
                    continuous++;
                }else{
                    if (next - beginNum == 1) {
                        if(continuous >= l){
                            forward = drawStarToLeft(check, seq, j);
                            continuous = 1;
                            beginNum = next;
//                            if(!forward) System.out.println("hifdsj");
                        }else{
//                            System.out.println("hello");
                            forward = false;
                        }
                    }else if(next - beginNum < 0){
                        continuous = 1;
                        beginNum = next;
                    }else{
//                        System.out.println("hello2");
                        forward = false;
                    }
                }
                if(!forward) break;
            }
            //reverse
            continuous = 0;
            beginNum = 0;
            for (int j = n -1; j >= 0; j--) {
                int next = seq[j];
                if(continuous == 0){
                    continuous++;
                    beginNum = next;
                }else if(beginNum == next){
                    continuous++;
                }else{
                    if (next - beginNum == 1) {
                        if(continuous >= l){
                            forward = drawStarToRight(check, seq, j);
                            continuous = 1;
                            beginNum = next;
                        }else{
//                            System.out.println("여기다");
                            forward = false;
                        }
                    }else if(next - beginNum < 0){
                        continuous = 1;
                        beginNum = next;
                    }else{
//                        System.out.println("여기다2");
                        forward = false;
                    }
                }
                if(!forward) break;
            }
            if(forward) {
                sol++;
//                System.out.printf("i : %d , solved\n",i);

            }else{
//                System.out.printf("i : %d , error\n",i);
            }
        }
        return sol;
    }

    static boolean drawStarToLeft(int[] check, int[] seq, int index) {
        int count = 0;
        index--;
        while (true) {
            if(check[index] == -1) return false;
            else{
                check[index]= -1;
                count++;
                index--;
            }
            if(index < 0 || count == l){
                break;
            }
        }
        if(count == l) return true;
        else return false;
    }

    static boolean drawStarToRight(int[] check, int[] seq, int index) {
        int count = 0;
        index++;
//        System.out.println("check");
//        for(int i : check){
//            System.out.printf("%d ", i);
//        }
//        System.out.println("Seq");
//        for(int i : seq){
//            System.out.printf("%d ", i);
//        }
//        System.out.println("index  :" + index);
        while (true) {
            if(check[index] == -1) return false;
            else{
                check[index]= -1;
                count++;
                index++;
            }
            if(index >= n || count == l){
                break;
            }
        }
        if(count == l) return true;
        else return false;
    }
}
