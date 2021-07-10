package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 음료수얼려먹기_이코테 {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int n,m;
    public static boolean dfs(int x, int y){
        if (x <= -1 || x >=n || y <= -1 || y >= m) {
            return false;
        }

        if(graph.get(x).get(y) == 0){
            graph.get(x).set(y, 1);
            dfs(x-1,y);//상
            dfs(x+1,y);//하
            dfs(x,y-1);//좌
            dfs(x,y+1);//우
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < n ; i++){
            str = br.readLine();
//            st = new StringTokenizer(str);
            graph.add(new ArrayList<>());
            for(int j = 0 ; j < m ; j++){
                graph.get(i).add(Integer.parseInt(String.valueOf(str.charAt(j))));
            }
        }

        int sol = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(dfs(i,j)){
                    sol++;
                }
            }
        }

        System.out.println(sol);


    }
}
