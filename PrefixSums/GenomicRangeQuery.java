package PrefixSums;

// 시도1. 무작정 쉽게 풀면 안될것 같아서 DP로 풀려고 한다.
// DP범위는 아닌데 이게 맞을지는 모르겠음.
public class GenomicRangeQuery {
    public static void main(String[] args) {
        String S = "CAGCCTA";
        int[] P = {2, 5, 0};
        int[] Q = {4, 5, 6};
        int[] test = new int[];
        int[] sol = solution(S, P, Q);
        for(int i : sol){
            System.out.println("i = " + i);
        }
        

    }

    public static int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 8F
        int[] sequence = new int[S.length()];
        int length = S.length();
        int[][] dp_store = new int[][];
        int[] result = new int[P.length];
        int rs_length = result.length;
        for (int i = 0; i < length; i++) {
            switch (S.charAt(i)) {
                case 'A':
                    sequence[i] = 1;
                    break;
                case 'C':
                    sequence[i] = 2;
                    break;
                case 'G':
                    sequence[i] = 3;
                    break;
                case 'T':
                    sequence[i] = 4;
                    break;

            }
        }

        for (int i = 0; i < length; i++) {
            dp_store[0][i] = sequence[i];
        }

        //범위가 i+1개일때
        for (int i = 1; i < length; i++) {
            //범위 마다의 최솟값 계산
            for (int j = 0; j < length - i; j++) {
                //각 범위의 최솟값 계산
                dp_store[i][j] = Math.min(dp_store[i - 1][j], dp_store[i - 1][j + 1]);
            }
        }

        for(int i = 0 ; i < rs_length ; i ++){
            result[i] = dp_store[Q[i]-P[i]][P[i]];
        }

        return result;
    }

}
