package 백준.구현;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1138_한줄로서기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        String sol;
        if(n == 10){
            sol = "*";
        }else{
            sol = String.valueOf(n);
        }
        for (int i = n - 1; i >= 0; i--) {
            int pass = seq[i];
            sol = sol.substring(0,pass) + (String.valueOf(i + 1)) +  sol.substring(pass,sol.length());
        }

        for (int i = 0; i < sol.length(); i++) {
            if(sol.charAt(i) != '*') bw.write(sol.charAt(i) + " ");
            else bw.write("10 ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
