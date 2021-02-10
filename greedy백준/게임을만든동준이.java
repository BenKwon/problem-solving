package greedy백준;

import java.util.Scanner;

//2847번
public class 게임을만든동준이 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int game_num = scanner.nextInt();
        int[] game_score = new int[101];
        int result = 0;
        for (int i = 0; i < game_num; i++){
            game_score[i] = scanner.nextInt();
        }
        for(int i = 2;i <= game_num; i++){
            if(game_score[game_num-i] >= game_score[game_num-i+1]){
                result += game_score[game_num-i] - game_score[game_num-i+1] + 1;
                game_score[game_num-i] = game_score[game_num-i+1] - 1;
            }
        }
        System.out.println(result);
    }
}
