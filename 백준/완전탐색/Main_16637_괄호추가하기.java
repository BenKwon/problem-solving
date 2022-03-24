package 백준.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_16637_괄호추가하기 {
    static int n;
    static int k;
    static String expression;
    static int[][] bracket;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        expression = br.readLine();
        bracket = new int[n][2];
        k = (n + 1) / 2;

        dfs(0);
        System.out.println(answer);
//        calculateMultiply("-433*300+7+8*2");

    }

    static void dfs(int curIndex) {
        if (curIndex >= k - 1) {
            answer = Math.max(answer, calculate());
            return;
        }
        System.out.println("curIndex = " + curIndex);
        // 1. 현재 index에서 괄호 시작하기
        bracket[curIndex][0] = 1;
        bracket[curIndex + 1][1] = 1;
        dfs(curIndex + 2);
        bracket[curIndex][0] = 0;
        bracket[curIndex + 1][1] = 0;
        // 2. 현재 index에서 괄호 처리 안하기
        dfs(curIndex + 1);
    }


    static int calculate() {
        String newExpression = "";
        Stack<Character> st = new Stack<>();
        System.out.println("----------------------------");
        System.out.println("k = " + k);
        for (int i = 0; i < k; i++) {
            System.out.println("i = " + i);
            System.out.println(expression.charAt(i * 2));
            if (bracket[i][0] == 1) {
                st.push('(');
                st.push(expression.charAt(i * 2));
                if (i < k - 1) st.push(expression.charAt((i * 2) + 1));
            } else if (bracket[i][1] == 1) {
                st.push(expression.charAt(i * 2));
                st.push(')');
                if (i < k - 1) st.push(expression.charAt((i * 2) + 1));
            } else {
                st.push(expression.charAt(i * 2));
                if (i < k - 1) st.push(expression.charAt((i * 2) + 1));
            }
        }
        String tmp = "";
        String finalExpression = "";
        String newEx = "";
        Stack<Character> stReverse = new Stack<>();
        for (char c : st) {
            newEx += c;
        }
        while (!st.isEmpty()) {
            char pop = st.pop();
            stReverse.push(pop);
        }

        System.out.println("newEx = " + newEx);
        boolean startBracket = false;
        while (!stReverse.isEmpty()) {
            char pop = stReverse.pop();
            System.out.println("pop = " + pop);
            if (pop == '(') {
                if (tmp.length() > 0) {
                    finalExpression += tmp;
                    tmp = "";
                }
                continue;
            } else if (pop == ')') {
                int result = calculateExpression((tmp));
                finalExpression += result;
                if (stReverse.size() > 0) finalExpression += stReverse.pop();
                tmp = "";
                continue;
            }
            tmp += pop;
        }
        if (tmp.length() > 0) finalExpression += tmp;

        System.out.println("finalExpression = " + finalExpression);
        return calculateExpression(calculateMultiply(finalExpression));
//        return 0;
    }

    static int calculateExpression(String str) {
        System.out.println("str = " + str);
        int idx = 0;
        int init = 1;
        String tmp = "";
        if(str.charAt(0) ==  '-') {
            init = -1;
            idx++;
        }
        while (Character.isDigit(str.charAt(idx))) {
            tmp += str.charAt(idx++);
            if (idx >= str.length()) break;
        }
        init *= Integer.parseInt(tmp);
        System.out.println("init = " + init);
        for (int i = idx; i < str.length(); ) {
            char next = str.charAt(i);
            System.out.println("next = " + next);
            int newValue = 1;
            if (str.charAt(i + 1) == '-') {
                newValue = -1;
                i++;
            }
            String strInt = "";
            i++;
            while (Character.isDigit(str.charAt(i))) {
                strInt += str.charAt(i++);
                if (i >= str.length()) break;
            }
            if (strInt.length() > 0)
                newValue *= Integer.parseInt(strInt);

            if (next == '*') {
                init *= newValue;
            } else if (next == '-') {
                init -= newValue;
            } else if (next == '+') {
                init += newValue;
            }
        }
        System.out.println("initresult = " + init);
        return init;
    }

    static String calculateMultiply(String str){
        System.out.println("start with : " + str);
        for (int i = 0; i < str.length(); i++) {
            char next = str.charAt(i);
            if(next =='*'){
                //left
                String left = "";
                int leftIndex = i - 1;
                while(leftIndex >= 0){
                    char nextLeft = str.charAt(leftIndex);
                    if(Character.isDigit(nextLeft)){
                        left += nextLeft;
                    }else{
                        if(nextLeft == '-' && leftIndex == 0){
                            left += nextLeft;
                            leftIndex--;
                        } else if (nextLeft == '-' && (!Character.isDigit(str.charAt(leftIndex - 1)))) {
                            left += nextLeft;
                            leftIndex--;
                        }
                        break;
                    }
                    leftIndex--;
                }
                String tmpLeft = "";
                for (int k = left.length()- 1; k >= 0; k--) {
                    tmpLeft += left.charAt(k);
                }
                left = tmpLeft;
                System.out.println("left = " + left);
                System.out.println("leftIndex = " + leftIndex);
                //right
                String right = "";
                int rightIndex = i + 1;
                if(str.charAt(rightIndex) == '-'){
                    right += '-';
                    rightIndex++;
                }
                while(rightIndex < str.length()){
                    char nextRight = str.charAt(rightIndex);
                    if (Character.isDigit(nextRight)) {
                        right += nextRight;
                    }else{
                        break;
                    }
                    rightIndex++;
                }
                System.out.println("right = " + right);
                System.out.println("rightIndex = " + rightIndex);
                String mulResult = (Integer.parseInt(right) * Integer.parseInt(left)) +"";
                String newStr = str.substring(0, leftIndex + 1) + mulResult +str.substring(rightIndex, str.length());
                System.out.println("newStr = " + newStr);
                str = newStr;
                i = 0;
            }
        }
        return str;
    }

}
