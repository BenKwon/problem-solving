package PrefixSums;

public class CountDiv {
    public static void main(String[] args) {
        int MAXINT = 2000000000;
        System.out.println("solution(11,345,17) = " + solution(0, MAXINT, MAXINT));
    }

    public static int solution(int A, int B, int K) {
        // write your code in Java SE 8
        int result = 1;
        int start_point;
        int last_point;
        if(A == 0 && B < K) return 1;
        if (B < K) return 0;

        if (A <= K) {
            start_point = K;
            if(A == 0){
                result++;
            }
        } else {
            if (A % K == 0) {
                start_point = (A / K) * (K);
            } else start_point = (A / K + 1) * (K);


        }

        last_point = (B / K) * K;

        return (last_point/K) - (start_point/K) + result;


    }
}
