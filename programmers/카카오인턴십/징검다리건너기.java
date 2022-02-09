package programmers.카카오인턴십;
import java.util.*;

public class 징검다리건너기 {
    static class Solution {
        static int n ;
        public int solution(int[] stones, int k) {
            int answer = 0;
            n = stones.length;
            int low = 0;
            int high = 200000000;
            while(low <= high){
                int mid = (low + high) / 2;
                boolean result = checkCandidate(stones, mid, k);
                if(result){
                    low = mid + 1;
                    answer= answer < mid ? mid : answer;
                }else{
                    high = mid - 1;
                }
            }
            return answer ;
        }

        static boolean checkCandidate(int[] stones, int mid, int k){
            int[] copy = new int[n];
            copyArray(stones, copy, mid);
            //check if it's possible that mid can cross the bridge
            int check = 0;
            for(int i = 0 ; i< n ; i++){
                int next = copy[i];
                if(next < 0){
                    check++;
                    if(check == k) return false;
                    continue;
                }
                check = 0;
            }
            return true;
        }

        static void copyArray(int[] origin, int[] target, int mid){
            for(int i = 0 ; i < n ;i++)
                target[i] = origin[i] - mid;
        }
    }
    public static void main(String[] args) {
    }
}

