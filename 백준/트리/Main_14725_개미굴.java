package 백준.트리;

import java.io.*;
import java.util.*;
public class Main_14725_개미굴 {
    static int n;
    static ArrayList<ArrayList<String>> results = new ArrayList<>();
    static HashMap<String, ArrayList<Integer>> starts = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            results.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String start = st.nextToken();
            if (!starts.containsKey(start)) {
                starts.put(start, new ArrayList<>());
            }
            starts.get(start).add(i);
            results.get(i).add(start);
            for (int j = 0; j < k - 1; j++) {
                results.get(i).add(st.nextToken());
            }
        }

        ArrayList<String> tmp = new ArrayList<>();
        for(String key : starts.keySet()){
            tmp.add(key);
        }
        Collections.sort(tmp);
        for(String key : tmp){
            solutions(key, starts.get(key), 0);
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void solutions(String food, ArrayList<Integer> idxs, int level){
        for (int i = 0; i < level; i++) {
            sb.append("--");
        }
        sb.append(food + "\n");
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for(int idx : idxs){
            ArrayList<String> result =results.get(idx);
            if(result.size() < level + 2) continue;
            String next = result.get(level + 1);
            if(!map.containsKey(next)) map.put(next, new ArrayList<>());
            map.get(next).add(idx);
        }
        ArrayList<String> tmp = new ArrayList<>();
        for(String key : map.keySet()){
            tmp.add(key);
        }
        Collections.sort(tmp);
        for(String key : tmp){
            solutions(key, map.get(key), level + 1);
        }
    }

}
