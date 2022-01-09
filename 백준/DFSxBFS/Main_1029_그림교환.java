package 백준.DFSxBFS;

import java.io.*;
import java.util.*;

public class Main_1029_그림교환 {
    static int n;
    static ArrayList<Integer>[] priceDrawing;
    static int max = Integer.MIN_VALUE;
    static int[][][] visit;
    static int calculateNumArtist(int bitmask){
        int num = 0;
        for (int i = 0; i < n; i++) {
            if(((1<<i) & bitmask) != 0) num++;
        }
        return num;
    }
    static void dfs(int artist, int price, int bitmask){
        int numOfArtist = calculateNumArtist(bitmask);
        if(max < numOfArtist) max = numOfArtist;
        if(visit[artist][price][bitmask] == 1) return;
        ArrayList<Integer> artists = priceDrawing[artist];
        for (int i = 1; i <= n; i++) {
            if((bitmask & (1<<(i - 1)))!= 0 || artists.get(i) < price) continue;
            dfs(i, artists.get(i), bitmask | (1 << (i - 1)));
        }
        visit[artist][price][bitmask] = 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        priceDrawing = new ArrayList[n + 1];
        visit =  new int[n + 1][10][(1 << n) + 1];

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            priceDrawing[i] = new ArrayList<>();
            priceDrawing[i].add(0);
            for (int j = 1; j <= n; j++) {
                priceDrawing[i].add(str.charAt(j-1) - 48);
            }
        }

        for (int i = 2; i <= n ; i++) {
            dfs(i, priceDrawing[1].get(i), 1 | (1 << (i - 1)));
        }
        System.out.println(max);
    }
}
