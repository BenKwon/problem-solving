package programmers;

import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int[] start_time = new int[lines.length];
        int[] end_time = new int[lines.length];

        for(int i = 0; i < lines.length;i++){
            String tmp = lines[i].split(" ")[1];
            String take = lines[i].split(" ")[2];
            take = take.substring(0, take.length()-1);
            String[] takes = take.split("\\.");
            int i_takes;
            if(takes.length == 1){
                i_takes = (1000 * Integer.parseInt(takes[0]));

            }else{ //
                i_takes = (1000 * Integer.parseInt(takes[0])) + Integer.parseInt(takes[1]);
            }
            String[] tmp2 = tmp.split("\\.");
            int ms = Integer.parseInt(tmp2[1]);
            tmp2 = tmp2[0].split(":");
            int hour = Integer.parseInt(tmp2[0]);
            int miniute = Integer.parseInt(tmp2[1]);
            int second = Integer.parseInt(tmp2[2]);
            // System.out.printf("h : %d , m : %d ,sec : %d , ms : %d\n",hour,miniute,second,ms);
            int total = (1000*60*60*hour) + (1000*60*miniute) + (1000 * second) + ms;
            end_time[i] = total;
            total = total - i_takes + 1;
            if(total < 0) total = 0;
            start_time[i] = total;

            // System.out.printf("start_time[%d] : %d , end_time[%d] : %d\n",i, start_time[i],i, end_time[i]);
        }
        int one_min = 1000 ;
        int sol = Integer.MIN_VALUE;
        for(int i = 0 ; i < start_time.length ; i++){
            //시작점 기준으로
            int limit = start_time[i] + one_min;
            int max = 1;
            for(int j = 0 ; j < start_time.length; j++){
                if(i == j) continue;
                if(start_time[j] < start_time[i]){
                    if(end_time[j] >= start_time[i]){
                        //처리율 증가
                        max++;
                    }
                }else{
                    if(start_time[j] < limit){
                        max++;
                        //처리율 증가
                    }
                }
            }
            //끝 지점 기준으로
            sol = Math.max(sol,max);
            limit = end_time[i] + one_min;
            max = 1;
            // System.out.printf("lim : %d\n",limit);
            for(int j = 0 ; j < start_time.length; j++){
                if(i == j) continue;
                if(start_time[j] < end_time[i]){
                    if(end_time[j] >= end_time[i]){
                        //처리율 증가
                        max++;
                    }
                }else{
                    if(start_time[j] < limit){
                        max++;
                        //처리율 증가
                    }
                }
            }
            // System.out.printf("끝기준 max : %d\n",max);
            sol = Math.max(sol,max);
        }
        answer = sol;
        return answer;

    }
}