package 백준.Greedy;

import java.util.*;

public class 강의실배정_11000 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] lectures = new int[N][2];
        PriorityQueue<Integer> lecture_room = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            lectures[i][0] = scanner.nextInt();
            lectures[i][1] = scanner.nextInt();
        }

        Arrays.sort(lectures, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                if(t1[0] == t2[0]){
                    return t1[1] - t2[1];
                }else{
                    return t1[0] - t2[0];
                }
            }
        });
        lecture_room.offer(lectures[0][1]);
        int curLectureStartTime = 0;
        for(int i = 1 ; i < N ; i++){
            curLectureStartTime = lectures[i][0];
            if(curLectureStartTime >= lecture_room.peek()){
                lecture_room.poll();
            }
            lecture_room.offer(lectures[i][1]);
        }
        System.out.println(lecture_room.size());
    }
}
