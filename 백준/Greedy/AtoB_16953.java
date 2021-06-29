package 백준.Greedy;


import java.util.Scanner;

//난이도 silver1
public class AtoB_16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String init_str = sc.next();
        String result_str = sc.next();
        int init = Integer.parseInt(init_str);
        int result = Integer.parseInt(result_str);
        int solution = 0;
        boolean sol_exist = false;
        while (result >= init) {
            int len = result_str.length();
            result = Integer.parseInt(result_str);
            if(init == result){
                sol_exist = true;
                break;
            }
            if (result_str.charAt(len - 1) == '1') {
                result_str = result_str.substring(0, len-1);
                result = Integer.parseInt(result_str);
                solution++;
            } else if (result % 2 == 0) {
                result = result / 2;
                result_str = Integer.toString(result);
                solution++;
            }else{
                break;
            }
        }

        if(sol_exist){
            System.out.println(solution+1);
        }else System.out.println(-1);

    }
}
