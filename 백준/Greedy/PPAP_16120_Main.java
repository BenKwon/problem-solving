package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Queue;
import java.util.Stack;

/**
 * substring은 O(n)이라고 한다... 스택으로 풀자.
 * 스택은 참고로 O(1)
 */
public class PPAP_16120_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int len = s.length();
        Stack<Character> ppap = new Stack<>();

        for(int i = 0 ; i < len ; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                char pop1,pop2;
                try {
                    pop1 = ppap.pop();
                    pop2 = ppap.pop();
                }catch (EmptyStackException e){
                    System.out.println("NP");
                    return;
                }
                if(i == len - 1){
                    System.out.println("NP");
                    return;
                }
                char next = s.charAt(i + 1);
                if(pop1 == 'P' && pop2 == 'P' && next =='P'){
                    ppap.push('P');
                    i++;
                }else{
                    System.out.println("NP");
                    return;
                }
            }else{
                ppap.push(c);
            }
        }

        if(ppap.pop() == 'P' && ppap.size() == 0){
            System.out.println("PPAP");
        }else{
            System.out.println("NP");
        }

    }
}
