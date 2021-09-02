package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_A와B_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        boolean sol = false;
        while(true){
//            System.out.println(t);
            if(t.equals(s)) {
                sol = true;
                break;
            }
            if(t.length() == 1) break;
            char c = t.charAt(t.length() - 1);
            t = t.substring(0, t.length()-1);
            if (c == 'A') {
            }else{ //'B'
                StringBuilder sb = new StringBuilder(t);
                t = sb.reverse().toString();
            }
        }
        if(sol) System.out.println(1);
        else System.out.println(0);
    }
}
