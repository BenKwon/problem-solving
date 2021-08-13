package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main_2504_괄호의값 {
    static char[] start = new char[]{'(', '['};
    static char[] end = new char[]{')', ']'};
    static boolean possible = true;

    public static int recursive(String seq) {

        if (seq.length() == 0) {
            return 1;
        }
        char c = seq.charAt(0);
        char close;
        int answer = 0;
        int mul = 0;
        if (c == '(') {
            close = ')';
            mul = 2;
        }else{
            close = ']';
            mul = 3;
        }
        int count = 1;
        String next = "";

        for (int i = 1; i < seq.length(); i++) {
            System.out.println("next = " + next);
            if (seq.charAt(i) == close) {
                count--;
            }else if( seq.charAt(i) == c){
                System.out.println("hi2");

                count++;
            }
            if(count == 0){
                answer += mul * recursive(next);
                next = "";
                System.out.println("answer = " + answer);
                System.out.printf("seq : %s , i : %d , count : %d \n", seq,i, count);
                if(i == seq.length() - 1) continue;
                System.out.println("hi");
                c = seq.charAt(i + 1);
                if (c == '(') {
                    close = ')';
                    mul = 2;
                }else{
                    close = ']';
                    mul = 3;
                }
                count = 1;
                i++;
            }else{
                next += seq.charAt(i);
            }
        }
        if(count != 0) possible = false;
        System.out.println("seq : " +  seq + " , count = " + count);
        return answer;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String seq = br.readLine();
        int recursive = recursive(seq);
        if(possible) System.out.println(recursive);
        else System.out.println(0);

    }
}
