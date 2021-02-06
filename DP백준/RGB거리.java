package DP백준;
import java.util.Scanner;

//1149번
public class RGB거리 {
    public static int num = 0;
    public static int[][] house;
    public static int[][] dp_store;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        house = new int[num][3];
        dp_store = new int[num][3];
        for (int i = 0; i < num; i++) {
            for (int k = 0; k < 3; k++) {
                house[i][k] = scanner.nextInt();
            }
        }
        dp_store[num - 1][0] = house[num - 1][0];
        dp_store[num - 1][1] = house[num - 1][1];
        dp_store[num - 1][2] = house[num - 1][2];

        System.out.print(Math.min(solution(0, 0), Math.min(solution(0, 1), solution(0, 2))));

    }

    public static int solution(int row,int col){
        if(dp_store[row][col] == 0){
            if(col == 0){
                dp_store[row][0] = house[row][0] +Math.min(solution(row + 1, 1), solution(row + 1, 2));
            }
            else if(col==1){
                dp_store[row][1] = house[row][1] + Math.min(solution(row + 1, 0), solution(row + 1, 2));

            }
            else{
                dp_store[row][2] = house[row][2] + Math.min(solution(row + 1, 0), solution(row + 1, 1));

            }

        }

        return dp_store[row][col];
    }
}


/*


 */