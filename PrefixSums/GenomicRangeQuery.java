package PrefixSums;

// 시도1. 무작정 쉽게 풀면 안될것 같아서 DP로 풀려고 한다.
// DP범위는 아닌데 이게 맞을지는 모르겠음.
public class GenomicRangeQuery {
    public static void main(String[] args) {
        String S = "CAGCCTA";
        int[] P = {2, 5, 0};
        int[] Q = {4, 5, 6};
        int[] sol = solution(S, P, Q);
        for (int i : sol) {
            System.out.println("i = " + i);
        }


    }
/*
원래 DP로 풀려고 했으나... 바보 같은 생각.. O(N^2) 시간 복잡도가 나온다.
그래서 아래 방법으로 다시 풀었다.
각 A,C,G,T배열은 모두 A[K]라고 했을때 염기서열 문자열에서 K 인덱스 전까지의 A의 갯수이다.
즉 문자열의 길이가 N이라고 했으므로 A,C,G,T의 배열의 길이는 N+1이 되어야 하고 A[7]은 N번째 문자열까지의 A의 누적 갯수이다.
이런식으로 각각 문자열을 하나씩 확인하면서 index까지의 누적 A,C,G,T의 갯수를 배열로 기록해논다.
그 후 예를들어 2 번째에서 5 번째 사이의 최소 값을 가지는 알파벳을 찾으려면 A[6] 에서 A[2]를 빼면된다
즉 인덱스 5까지의 A의 누적갯수에서 인덱스 1까지의 A의 누적갯수를 빼면 2번째에서 5번째까지의 총 A의 수를 알 수있다.
만약 범위내의 A의 수가 0이라면 차례대로 해당 범위의 C,G,T배열을 확인해본다.
 */
    public static int[] solution(String S, int[] P, int[] Q) {
        int[] A, C, G, T; //1,2,3,4
        A = new int[S.length() + 1];
        C = new int[S.length() + 1];
        G = new int[S.length() + 1];
        T = new int[S.length() + 1];
        int[] sequence = new int[S.length()];
        int length = S.length();
        int M = P.length;
        int[] result = new int[M];
        switch (S.charAt(0)) {
            case 'A':
                A[0] = 0;
                break;
            case 'C':
                C[0] = 0;
                break;
            case 'G':
                G[0] = 0;
                break;
            case 'T':
                T[0] = 0;
                break;
        }

        for (int i = 1; i < length + 1; i++) {
            A[i] = A[i - 1];
            C[i] = C[i - 1];
            G[i] = G[i - 1];
            T[i] = T[i - 1];
            switch (S.charAt(i - 1)) {
                case 'A':
                    A[i] = A[i - 1] + 1;
                    break;
                case 'C':
                    C[i] = C[i - 1] + 1;
                    break;
                case 'G':
                    G[i] = G[i - 1] + 1;
                    break;
                case 'T':
                    T[i] = T[i - 1] + 1;
                    break;
            }
        }

        for (int i = 0; i < M; i++) {
            if (A[Q[i] + 1] - A[P[i]] > 0) {
                result[i] = 1;
            } else if (C[Q[i] + 1] - C[P[i]] > 0) {
                result[i] = 2;
            } else if (G[Q[i] + 1] - G[P[i]] > 0) {
                result[i] = 3;
            } else if (T[Q[i] + 1] - T[P[i]] > 0) {
                result[i] = 4;
            }
        }
        return result;
    }


    //DP를 사용함
    public static int[] solution2(String S, int[] P, int[] Q) {
        // write your code in Java SE 8F
        int[] sequence = new int[S.length()];
        int length = S.length();
        int[][] dp_store = new int[100000][100000];
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

        for (int i = 0; i < rs_length; i++) {
            result[i] = dp_store[Q[i] - P[i]][P[i]];
        }

        return result;
    }

}
