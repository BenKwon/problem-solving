package greedy백준;

import java.util.Scanner;
//1946번
public class 신입사원 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test_num = scanner.nextInt();
        int[] people_num = new int[test_num];
        int paper,interview;
        int[][][] store = new int[20][100000][2];
        for(int i = 0 ; i < test_num; i++){
            people_num[i] = scanner.nextInt();
            for(int j = 0 ; j < people_num[i] ; j++){
                paper = scanner.nextInt();
                interview = scanner.nextInt();
                int[] new_person = {paper,interview};
                store[i][paper-1] = new_person;
            }
        }
        for(int i = 0; i < test_num; i++){
            int bound = store[i][0][1];
            int result = people_num[i];
            for(int j = 0 ; j < people_num[i]; j++){
                if(bound < store[i][j][1]){
                    result--;
                }
                else{
                    bound = store[i][j][1];
                }
            }
            System.out.println(result);
        }


    }


}