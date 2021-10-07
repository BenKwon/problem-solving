package programmers;

import java.util.*;
class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0 ; i < skill.length() ;i++){
            map.put(skill.charAt(i),i);
        }
        for(int i = 0 ; i < skill_trees.length; i++){
            String str = skill_trees[i];
            boolean sol = true;
            int before = -1;
            int[] visit = new int[27];
            for(int j = 0 ; j < str.length() ;j++){
                char next = str.charAt(j);

                if(map.get(next) != null){
                    int idx = map.get(next);
                    if(idx < before){
                        sol = false;
                        break;
                    }else{
                        if(idx == 0) visit[0] = 1;
                        else{
                            if(visit[idx - 1] == 0){
                                sol = false;
                                break;
                            }else{
                                visit[idx] = 1;
                                before = idx;
                            }
                        }
                    }

                }
            }
            if(sol)   answer++;
        }
        return answer;
    }
}
