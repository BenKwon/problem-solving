package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 회문_17609 {
    static int t;
    static String s;
    static char[] arr;
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        t=sc.nextInt();
        for (int tc = 1; tc <=t; tc++) {
            s=sc.next();
            arr=s.toCharArray();
            int left=0;
            int right=s.length()-1;
            if(check(left,right)) {
                System.out.println(0);
                continue;
            }
            if(checkS(left,right)) {
                System.out.println(1);
            }else {
                System.out.println(2);
            }

        }

    }

    private static boolean check(int left,int right) {
        while(left<=right) {
            if(arr[left]!=arr[right]) {//다름
                return false;
            }
            left+=1;
            right-=1;
        }
        return true;
    }

    private static boolean checkS(int left,int right) {
        while(left<=right) {
            if(arr[left]!=arr[right]) {//다름
                boolean a=check(left+1,right);
                boolean b=check(left,right-1);
                if(a==false && b==false) {
                    return false;
                }else return true;
            }
            left+=1;
            right-=1;
        }
        return true;
    }
}
//public class 회문_17609 {
//    static int check_back_or_front_remove(int front, int back, String word){
//        if(word.charAt(front+1) == word.charAt(back)){
//            return 1; //
//        }
//        else if(word.charAt(front) == word.charAt(back-1)){
//            return 2;
//        }
//        else{
//            return -1;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        String[] words = new String[N];
//        for (int i = 0; i < N; i++) {
//            String word = scanner.next();
//            int front_pointer = 0;
//            int back_pointer = word.length() - 1;
//            int result = 0;
//            while(front_pointer < back_pointer){
//                if(word.charAt(front_pointer) == word.charAt(back_pointer)) {
//                    front_pointer++;
//                    back_pointer--;
//                }
//                else{
//                    int check = check_back_or_front_remove(front_pointer, back_pointer, word);
//                    if(check == 1){
//                        front_pointer++;
//                        result++;
//                    }else if(check ==2){
//                        back_pointer--;
//                        result++;
//                    }else{ // check == -1
//                        result = 2;
//                        break;
//                    }
//                    if(result>2){
//                        result=2;
//                        break;
//                    }
//                }
//            }
//
//            System.out.println(result); // 회문 판정 결과  회문:0  유사회문 :1 둘다아님:@
//        }
//
//    }
//
//
//}
