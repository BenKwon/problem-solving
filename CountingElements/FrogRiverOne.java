package CountingElements;

public class FrogRiverOne {
    public static void main(String[] args) {
        int A[] = {1, 3, 1, 4, 2, 3, 5, 4};
        System.out.println(solution(5, A));
    }

    public static int solution(int x, int[] A) {
        int[] road = new int[x+1]; // 0으로 리초기화된 road배열 배열이 1로 꽉차면 개구리 건널수있음
        int sum_road = 0;
        int length = A.length;
        int leave;
        for(int i = 0 ; i < length ; i++){
            leave = A[i];
            //잎사귀가 떨어진적 없는 자리에 잎사귀가 떨어질경우 
            if(road[leave] == 0){
                road[leave] = 1;
                sum_road++;
            }
            //길이 모두 만들어졌을 경우 시간 리턴
            if(sum_road == x){
                return i;
            }
        }
        //예외 상황
        return -1;
    }
}
