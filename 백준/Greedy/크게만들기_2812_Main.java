package 백준.Greedy;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 스택을 사용하여 풀어야한다.
 * 스택에 숫자를 하나하나 넣어가며 이미 들어가있는 숫자들중 현재 넣을 숫자보다 작은 숫자들을
 * 제거해나가면 된다.
 * 그냥 이렇게만 하면 예외 상황이 생긴다.
 * 같을때는 제거를 안해야하는데 이렇게하면
 * 5 2
 * 99899 의경우
 * 9999출력값이 나와서
 * k가 남아있는 경우 스택에 남은 숫자가 다 똑같은 숫자이므로
 * k만큼 반복문을 돌려 pop을 해줘야한다.
 */
public class 크게만들기_2812_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String num = br.readLine();
        char[] c = num.toCharArray();
        Stack<Character> result = new Stack<>();
        for(int i = 0 ; i < n ; i++){
            char cur = c[i];
            int numeric_car = Character.getNumericValue(cur);
            if(result.size() >= 1){
                while(!result.empty() && k > 0){
                    char pop = result.pop();
                    int numeric_pop= Character.getNumericValue(pop);
                    if(numeric_car > numeric_pop){
                        k--;
                    }else{
                        result.push(pop);
                        break;
                    }
                }
                result.push(cur);
            }else{
                result.push(cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(k>0){
            result.pop();
            k--;
        }
        while(true){
            if(result.empty()){
                break;
            }else{
                sb.append(result.pop());
            }
        }
        StringBuilder reverse = sb.reverse();
        System.out.println(reverse);



    }
}
