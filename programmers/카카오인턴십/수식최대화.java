package programmers.카카오인턴십;

public class 수식최대화 {
    static char[][] sequence = new char[][]{
            {'*', '+', '-'}, //0
            {'*', '-', '+'}, //1
            {'+', '*', '-'}, //2
            {'+', '-', '*'}, //3
            {'-', '+', '*'}, //4
            {'-', '*', '+'}  //5
    };
    ;

    /**
     * 재귀적으로 함소 호출을 해서
     * 마지막 level에서는 우선순위가 가장 큰 연산자가 oper인자로 들어가서
     * 우선순위가 높은 연산자 부터 먼저 계산되어 반환되어 이어서 다음 우선순위 연산자가 계산된다.
     */
    static public long recursive(String expression, int oper, int seq_type) { //expression : 계산해야할 문자열 , oper : 현재 호출 함수에서 연산할 연산자 ,seq_type: 우선순위 타입
        long result;
        //현재 expression이 숫자로만 이루어져 있는지 확인
        try {
            result = Integer.valueOf(expression);
            return result;
        } catch (NumberFormatException e) {
        }

        char operator = sequence[seq_type][oper];
        String[] split;
        switch (operator) {
            case '+':
                split = expression.split("\\+");
                break;
            case '-':
                split = expression.split("-");
                break;
            case '*':
                split = expression.split("\\*");
                break;
            default:
                split = expression.split("\\*");
                break;
        }

        //계산 초기값 세팅
        result = recursive(split[0], oper - 1, seq_type);
        //계산
        for (int i = 1; i < split.length; i++) {
            if (operator == '-') {
                long tmp = recursive(split[i], oper - 1, seq_type);
                System.out.println("tmp = " + tmp);
                System.out.printf("result : %d , - tmp : %d \n" ,result, tmp);

                result -= tmp;

            } else if (operator == '+') {
                long tmp = recursive(split[i], oper - 1, seq_type);
                System.out.println("tmp = " + tmp);
                System.out.printf("result : %d , + tmp : %d = \n" ,result, tmp);

                result += tmp;
            } else {
                long tmp = recursive(split[i], oper - 1, seq_type);
                System.out.printf("result : %d , * tmp : %d = \n" ,result, tmp);
                result *= tmp;
            }
        }
//        System.out.println("result = " + result);
        return result;
    }

    static public long solution(String expression) {
        long answer = Integer.MIN_VALUE;

        for (int i = 0; i < 6; i++) {
            answer = Math.max(answer, Math.abs(recursive(expression, 2, i)));
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("10-0-1*100"));

    }
}

// 4  7 9  10 12 24 25