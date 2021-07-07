package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀문제
 */
public class 박스채우기_1493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer box_str_token = new StringTokenizer(s);
        int length = Integer.parseInt(box_str_token.nextToken());
        int width = Integer.parseInt(box_str_token.nextToken());
        int height = Integer.parseInt(box_str_token.nextToken());
        int result = length * width * height;


        int n = Integer.parseInt(br.readLine());
        int[] cube = new int[n];

    }
}
