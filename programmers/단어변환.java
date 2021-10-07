package programmers;

import java.util.*;
class 단어변환 {
    static class node{
        String str;
        int count;

        public node(String str, int count){
            this.str= str;
            this.count = count;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = begin.length();
        HashMap<String, ArrayList<String>> connect_info = new HashMap<>();
        for(int i = 0 ; i < words.length ; i++){
            String word = words[i];
            connect_info.put(word, new ArrayList<>());
            for(int j = 0; j < words.length + 1 ;j++){
                if(i==j) continue;
                String other;
                if(j == words.length) other = begin;
                else other = words[j];
                int same = 0;
                for(int k = 0 ; k < n; k++){
                    if(word.charAt(k) == other.charAt(k)) same++;
                }
                if(same == n -1){
                    connect_info.get(word).add(other);
                }
            }

        }
        if(connect_info.get(target) == null) answer = 0;
        else{
            Queue<node> q = new LinkedList<>();
            HashSet<String> visit = new HashSet<>();
            q.add(new node(target, 0));
            visit.add(target);
            while(!q.isEmpty()){
                node poll = q.poll();
                if(poll.str.compareTo(begin) == 0){
                    answer = poll.count;
                    break;
                }
                if(connect_info.get(poll.str) == null) continue;
                ArrayList<String> nexts = connect_info.get(poll.str);
                for(int i = 0 ; i <  nexts.size(); i++){
                    String next = nexts.get(i);
                    System.out.println(next);
                    if(visit.contains(next)) continue;
                    q.add(new node(next, poll.count + 1));
                    visit.add(next);
                }
            }
        }
        return answer;
    }
}