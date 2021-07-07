package 백준.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 공주님의정원_2457_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = stoi(br.readLine());
        int[][][] flower = new int[n][2][2];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            flower[i][0][0] = stoi(st.nextToken());
            flower[i][0][1] = stoi(st.nextToken());

            flower[i][1][0] = stoi(st.nextToken());
            flower[i][1][1] = stoi(st.nextToken());
        }

        Arrays.sort(flower, new Comparator<int[][]>() {
            @Override
            public int compare(int[][] o1, int[][] o2) {
                if(o1[0][0] == o2[0][0]){
                    if(o1[0][1] == o2[0][1]){
                        if(o1[1][0] == o2[1][0]){
                            return o2[1][1] - o1[1][1];
                        }else{
                            return o2[1][0] - o1[1][0];
                        }
                    }else{
                        return o1[0][1] - o2[0][1];
                    }
                }else{
                    return o1[0][0] - o2[0][0];
                }
            }
        });
        int point = calculate_month_date(flower[0][1]);
        int max_idx = 0;
        int tmp = 0; // 꽃 피는 날짜가 3월1일을 포함하여 3월 1일 이전인 경우의 갯수 0개라면 정답이 없음.
        for(int i = 0 ; i < n ; i++){
            if(calculate_month_date(flower[i][0]) <= 301){
                tmp++;
                if(point <= calculate_month_date(flower[i][1])){
                    point = calculate_month_date(flower[i][1]);
                    max_idx = i;
                    if(point >= 1131){
                        System.out.println(1);
                        return;
                    }
                }
            }else{
                if(point <= 301||tmp==0){
                    System.out.println(0);
                    return;
                }
                break;
            }
        }
        int idx = 0;
        int max = 0;
        int sol = 1;
        if(tmp == n){
            if(point >= 1131)
                System.out.println(1);
            else
                System.out.println(0);
            return;
        }
        for(int i = tmp ; i < n ;){
            point = calculate_month_date(flower[max_idx][1]);
            max_idx = i;
            max = calculate_month_date(flower[i][1]);
            int count = 0;
            while(calculate_month_date(flower[i][0]) <= point){
                count++;
                if(calculate_month_date(flower[i][1]) > max){
                    max = calculate_month_date(flower[i][1]);
                    max_idx = i;
                    if(max >= 1131){
                        System.out.println(++sol);
                        return;
                    }
                }
                i++;
                if(i == n){
                    if(max < 1131){
                        System.out.println(0);
                    }else System.out.println(++sol);
                    return;
                }
            }
            if(count == 0) {
                System.out.println(0);
                return;
            }
            sol++;
        }

    }

    public static int calculate_month_date(int[] day){
        return day[0]*100+day[1];
    }


    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
