package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 6
 * 10 20 10 30 20 50
 */
public class 가장긴증가하는부분수열_11053_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] seq = new int[n];
        ArrayList<Integer> dp = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }


    }
}
