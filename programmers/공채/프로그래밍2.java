package programmers.공채;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 0으로 나누어서 소수를 구하는문제
 */
public class 프로그래밍2 {
    static String convertResult = "";

    public static int solution(int n, int k) {
        int answer = 0;
        convertResult = "";
        convert(n, k);
        String[] split = convertResult.split("0");
        StringTokenizer st = new StringTokenizer(convertResult, "0");
        int start = -1;
        int end = -1;
        boolean set_start = false;
        String num = "";
//        for (String s : split) {
//            if (s.length() > 0)
//                if (is_prime(Long.parseLong(s))) answer++;
//        }

        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.length() > 0)
                if (is_prime(Long.parseLong(s))) answer++;
        }

        return answer;
    }


    public static boolean is_prime(long num) {
        if (num % 2 == 0) return (num == 2);
        if (num < 2) return false;
        int root = (int)Math.sqrt((double)num);
        for (int i = 3; i <= root; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    public static void convert(int n, int k) {
        if (n / k > 0) convert(n / k, k);
        if (n / k > 0) convertResult += (n % k + "");
        else convertResult += (n + "");
    }

    public static void main(String[] args) throws IOException {
//        convert(437674, 7);
//        System.out.println(convertResult);
        int n = 437674;
        int k = 3;
        System.out.println(solution(n, k));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


//        for (int i = 900000; i <= 1000000; i++) {
//            for (int j = 3; j <= 10; j++) {
//                bw.write("n : " + i + ", k : " + j + "\n");
//                bw.flush();
//                solution(i, j);
//            }
//        }
//        System.out.println("끝");
        /**
         * n : 55768 , k :3
         * Exception in thread "main" java.lang.NumberFormatException: For input string: "2211111111"
         * 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.base/java.lang.Integer.parseInt(Integer.java:652)
         * 	at java.base/java.lang.Integer.parseInt(Integer.java:770)
         * 	at programmers.공채.프로그래밍2.solution(프로그래밍2.java:20)
         * 	at programmers.공채.프로그래밍2.main(프로그래밍2.java:71)
         */
    }

}
