package 백준.Greedy;

import java.util.Scanner;

//골드5
public class 지뢰찾기_2140 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            board[i] = line.toCharArray();
        }


        if (n == 1) {
            System.out.println(0);
            return;
        }
        //1행 기준으로 확정 지뢰 위치 찾기
        for (int i = 0; i < n; i++) {
            char bomb_info = board[0][i];
            if (i == 0) {
                if (bomb_info == '1') {
                    board[1][1] = '*';
                } else {
                    board[1][1] = '!';
                }
            } else if (i == n - 1) {
                if (bomb_info == '1') {
                    board[1][n - 2] = '*';
                } else {
                    board[1][n - 2] = '!';
                }
            } else {
                int bomb_remain = 0;
                if (Character.isDigit(bomb_info)) {
                    bomb_remain = Character.getNumericValue(bomb_info);
                }

                for (int j = -1; j < 2; j++) {
                    char check = board[1][i + j];
                    if (bomb_remain == 0 && !Character.isDigit(board[1][i + j]) && check != '*') {

                        board[1][i + j] = '!';
                        continue;
                    } else {
                        if (board[1][i + j] == '*') {
                            bomb_remain--;
                        } else if (board[1][i + j] == '#') {
                            board[1][i + j] = '*';
                            bomb_remain--;
                        }
                    }

//                    else if (board[1][i + j] == '!') {
//                        continue;
//                    }
                }
            }
        }

        //마지막 행 기준으로 확정 지뢰 찾기
        for (int i = 0; i < n; i++) {
            char bomb_info = board[n - 1][i];
            if (i == 0) {
                if (bomb_info == '1') {
                    board[n - 2][1] = '*';
                } else {
                    board[n - 2][1] = '!';
                }
            } else if (i == n - 1) {
                if (bomb_info == '1') {
                    board[n - 2][n - 2] = '*';
                } else {

                    board[n - 2][n-2] = '!';
                }
            } else {
                int bomb_remain = 0;
                if (Character.isDigit(bomb_info)) {
                    bomb_remain = Character.getNumericValue(bomb_info);
                }

                for (int j = -1; j < 2; j++) {
                    int check = board[n - 2][i + j];
                    if (bomb_remain == 0 && !Character.isDigit(board[n-2][i + j]) && check != '!') {
                        board[n - 2][i + j] = '!';
                        continue;
                    } else {
                        if (board[n - 2][i + j] == '*') {
                            bomb_remain--;
                        } else if (board[n - 2][i + j] == '#') {
                            board[n - 2][i + j] = '*';
                            bomb_remain--;
                        }
                    }

//                    else if (board[n - 2][i + j] == '!') {
//                        continue;
//                    }
                }
            }
        }

        //첫번째열 기준으로 확정 지뢰찾기
        for (int i = 1; i < n - 1; i++) {
            char bomb_info = board[i][0];
            int bomb_remain = Character.getNumericValue(bomb_info);

            for (int j = -1; j < 2; j++) {
                char check = board[i + j][1];
                if (bomb_remain == 0 && !Character.isDigit(board[i+j][1]) && check != '*') {
                    board[i + j][1] = '!';
                    continue;
                } else {
                    if (Character.isDigit(check)) {
                        continue;
                    } else if (check == '*') {
                        bomb_remain--;
                    } else if (check == '!') {
                        continue;
                    } else {
                        board[i + j][1] = '*';
                        bomb_remain--;
                    }
                }

            }
        }
        //마지막열 기준으로 확정지뢰찾기
        for (int i = 1; i < n - 1; i++) {
            char bomb_info = board[i][n - 1];
            int bomb_remain = Character.getNumericValue(bomb_info);

            for (int j = -1; j < 2; j++) {
                char check = board[i + j][n - 2];
                if (bomb_remain == 0 && !Character.isDigit(board[i+j][n-2]) && check != '*') {
                    board[i + j][n - 2] = '!';
                    continue;
                } else {
                    if (Character.isDigit(check)) {
                        continue;
                    } else if (check == '*') {
                        bomb_remain--;
                    } else if (check == '!') {
                        continue;
                    } else {
                        board[i + j][n - 2] = '*';
                        bomb_remain--;
                    }
                }

            }
        }


        int sol = 0;
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == '#' || aChar == '*') {
                    sol++;
                }
            }
        }
        System.out.println(sol);
    }
}
