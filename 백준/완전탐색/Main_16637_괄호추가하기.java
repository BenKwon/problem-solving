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
    }

    static void dfs(int curIndex) {
        if (curIndex >= k - 1) {
            answer = Math.max(answer, calculate());
            return;
        }
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
        for (int i = 0; i < k; i++) {
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

        boolean startBracket = false;
        while (!stReverse.isEmpty()) {
            char pop = stReverse.pop();
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
        return calculateExpression(finalExpression);
//        return 0;
    }

    static int calculateExpression(String str) {
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
        for (int i = idx; i < str.length(); ) {
            char next = str.charAt(i);
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
        return init;
    }

}
