package 백준.트리;

import java.io.*;
import java.util.HashSet;

public class Main_5052_전화번호목록 {
    static int n;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        while(test-- > 0){
            n = Integer.parseInt(br.readLine());
            set.clear();
            for (int i = 0; i < n; i++) {
                set.add(br.readLine());
            }
            boolean sol = true;
            for(String str : set){
                String tmp = "";
                for (int i = 0; i < str.length(); i++) {
                    tmp += str.charAt(i);
                    if (set.contains(tmp) && !tmp.equals(str)) {
                        sol = false;
                        break;
                    }
                }
                if(!sol) break;
            }
            if(sol) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
    }
}
