package 백준.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1662_압축_복습 {
    static class Char{
        char elem;
        int index;

        public Char(char elem, int index) {
            this.elem = elem;
            this.index = index;
        }
    }
    static int[] pair;
    static String seq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        seq = str;
        Stack<Char> st = new Stack<>();
        pair = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            st.push(new Char(str.charAt(i), i));
        }
        Stack<Char> tmp = new Stack<>();
        while (!st.isEmpty()) {
            Char pop = st.pop();
            if(pop.elem == '('){
                while(true){
                    Char next = tmp.pop();
                    if(next.elem == ')'){
                        pair[pop.index] = next.index;
                        pair[next.index] = pop.index;
                        break;
                    }
                }
            }else{
                tmp.push(pop);
            }
        }

        int answer = dfs(0, seq.length() - 1);
        System.out.println(answer);
    }

    static int dfs(int start, int end){
        int count = 0;
//        System.out.printf("start : %d, end : %d\n",start, end);
        for (int i = start; i <= end; i++) {
            char next = seq.charAt(i);
//            System.out.println("next = " + next);
            if(Character.isDigit(next))count++;
            if(next =='('){
                count--;
                int newEnd = pair[i];
                count += Integer.parseInt(seq.charAt(i - 1)+"") * dfs(i + 1, newEnd  - 1);
                i = newEnd;
            }
        }
//        System.out.printf("start : %d , end : %d , count : %d\n",start,end,count);

        return count;
    }
//33(562(71(9)))

}
