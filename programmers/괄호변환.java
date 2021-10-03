package programmers;

import java.util.*;
class Solution {
    public String find(String w){
        if(w.length() == 0) return "";

        String[] result = depart(w);
        String u = result[0];
        String v = result[1];
        String answer = "";
        if(check_right(u)){
            answer = u + find(v);
        }else{
            answer += '(';
            answer += find(v) + ")";
            String new_u = "";
            for(int i = 1 ; i < u.length() - 1; i++){
                char next = u.charAt(i);
                if(next =='('){
                    new_u += ")";
                }else{
                    new_u += "(";
                }
            }
            answer += new_u;
        }
        return answer;
    }
    public String solution(String p) {
        String answer = "";
        answer = find(p);
        return answer;
    }
    public boolean check_right(String str){
        Stack<Character> store = new Stack<>();
        Stack<Character> check = new Stack<>();
        for(int i = 0 ; i < str.length(); i++){
            store.push(str.charAt(i));
        }
        while(!store.isEmpty()){
            char next = store.pop();
            if(check.size() >= 1){
                if(check.peek() ==')' && next == '('){
                    check.pop();
                    continue;
                }
            }
            check.push(next);
        }
        if(check.isEmpty()) return true;
        return false;
    }
    public String[] depart(String str){
        Queue<Character> q = new LinkedList<>();
        String[] result= new String[2];
        int left = 0;
        int right = 0;
        String u = "";
        String v = "";
        boolean turn_u = true;
        for(int i = 0 ; i < str.length() ;i++){
            char next = str.charAt(i);
            if(next == '('){
                left++;
            }else{
                right++;
            }
            if(left != 0 && left == right && turn_u){
                u += next;
                turn_u = false;
                continue;
            }
            if(turn_u) u += next;
            else v += next;
        }
        result[0] = u;
        result[1] = v;
        return result;
    }
}