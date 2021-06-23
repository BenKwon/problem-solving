package 백준.Greedy;

import java.util.Scanner;

public class UCPC약자_15904 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int length = s.length();
        char[] ucpc = {'U','C','P','C'};
        int ucpc_target = 0;
        boolean solution = false;
        for (int i = 0; i < length; i++){
            if(s.charAt(i) == ucpc[ucpc_target]){
                ucpc_target++;
            }
            if(ucpc_target == 4){
                solution = true;
                break;
            }
        }

        if(solution){
            System.out.println("I love UCPC");
        }
        else{
            System.out.println("I hate UCPC");
        }
    }
}
