package 백준.Greedy;

import java.io.*;
import java.util.*;

public class Main_1931_회의실배정 {
    static class Meeting{
        int start;
        int end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        PriorityQueue<Meeting> pq = new PriorityQueue<>(new Comparator<Meeting>(){
           @Override
           public int compare(Meeting o1, Meeting o2){
                if(o1.end == o2.end){
                    return o1.start - o2.start;
                }else{
                    return o1.end - o2.end;
                }
           }
        });
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(start,end));
        }
        int possibleMeetingCount = 0;
        int curEnd = -1;
        while(!pq.isEmpty()){
            Meeting poll = pq.poll();
            if(curEnd <= poll.start){
                possibleMeetingCount++;
                curEnd = poll.end;
            }
        }
        System.out.println(possibleMeetingCount);
    }
}
