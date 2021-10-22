package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] wheel = new int[6][8];
        for (int i = 1; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
//        System.out.println("-------------------");
        int k = Integer.parseInt(br.readLine());
        //확인 해야할 인덱스는 2와 6인덱스
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            boolean left = true;
            boolean right = true;
            if(wheel[index][6] == wheel[index-1][2]) left = false;
            if(wheel[index][2] == wheel[index+1][6]) right = false;
            System.out.println("--------------------------------------");
            int store;
            if(direction == 1){
                System.out.printf("%d 가 시계으로 회전\n",index);
                store = wheel[index][7];
                for(int m = 6 ; m >= 0 ; m--){
                    wheel[index][m+ 1] = wheel[index][m];
                }
                wheel[index][0] = store;
            }else{
                store = wheel[index][0];
                System.out.printf("%d 빈시계으로 회전\n",index);

                for(int m = 1 ; m < 8 ; m++){
                    wheel[index][m - 1] = wheel[index][m];
                }
                wheel[index][7] = store;
            }
            if(left){
                if(direction == -1){
                    System.out.printf("%d 가 시계으로 회전\n",index - 1);
                    store = wheel[index - 1][7];
                    for(int m = 6 ; m >= 0 ; m--){
                        wheel[index - 1][m+ 1] = wheel[index - 1][m];
                    }
                    wheel[index - 1][0] = store;
                }else{
                    System.out.printf("%d 가 반시계으로 회전\n",index - 1);
                    store = wheel[index - 1][0];
                    for(int m = 1 ; m < 8 ; m++){
                        wheel[index - 1][m - 1] = wheel[index -1][m];
                    }
                    wheel[index - 1][7] = store;
                }

            }

            if(right){
                if(direction == -1){
                    System.out.printf("%d 가 시계으로 회전\n",index +  1);
                    store = wheel[index + 1][7];
                    for(int m = 6 ; m >= 0 ; m--){
                        wheel[index + 1][m+ 1] = wheel[index + 1][m];
                    }
                    wheel[index + 1][0] = store;
                }else{
                    System.out.printf("%d 가  반 시계으로 회전\n",index +  1);
                    store = wheel[index + 1][0];
                    for(int m = 1 ; m < 8 ; m++){
                        wheel[index + 1][m - 1] = wheel[index + 1][m];
                    }
                    wheel[index + 1][7] = store;
                }

            }
        }
        int score = 1;
        int sum = 0;
        for (int i = 1; i <= 4; i++) {
            int wheelDir = wheel[i][0];
            System.out.println(wheelDir);
            if(wheelDir == 0){
            }else{
//                System.out.printf("%d 추가 \n",score);
                sum += score;
            }
            score*=2;

        }
        System.out.println(sum);



    }
}
