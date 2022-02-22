package 백준.그래프이론;

import java.io.*;
import java.util.*;

public class Main_3665_최종순위 {
    static int n, m;
    static HashMap<Integer, HashSet<Integer>> parentMap = new HashMap<>();
    static HashMap<Integer, HashSet<Integer>> childMap = new HashMap<>();
    static int[] lastYear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            n = Integer.parseInt(br.readLine());
            lastYear = new int[n + 1];
            parentMap.clear();
            childMap.clear();
            int[] visit = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int next = Integer.parseInt(st.nextToken());
                lastYear[i + 1] = next;
                parentMap.put(i + 1, new HashSet<>());
                childMap.put(i + 1, new HashSet<>());
            }
            for (int i = 1; i <= n; i++) {
                int num = lastYear[i];
                for (int j = i - 1; j >= 1; j--) {
                    parentMap.get(num).add(lastYear[j]);
                }
                for (int j = i + 1; j <= n; j++) {
                    childMap.get(num).add(lastYear[j]);

                }
            }
            m = Integer.parseInt(br.readLine());
            boolean impossible = false;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int front = Integer.parseInt(st.nextToken());
                int back = Integer.parseInt(st.nextToken());
                if (childMap.get(back).contains(front) && parentMap.get(front).contains(back)) {
                    childMap.get(back).remove(front);
                    parentMap.get(front).remove(back);
                    childMap.get(front).add(back);
                    parentMap.get(back).add(front);
                    continue;
                }
                else if (childMap.get(front).contains(back) && parentMap.get(back).contains(front)) {
                    childMap.get(front).remove(back);
                    parentMap.get(back).remove(front);
                    childMap.get(back).add(front);
                    parentMap.get(front).add(back);
                    continue;
                }else{
                    impossible = true;
                }

            }
            Queue<Integer> q = new LinkedList<>();
            Iterator<Integer> keys = parentMap.keySet().iterator();
            while(keys.hasNext()){
                int key = keys.next();
                if(parentMap.get(key).isEmpty()){
                    q.add(key);
                    visit[key] =1;
                }
            }
            if(q.size() == 0 || impossible){
                bw.write("IMPOSSIBLE\n");
                continue;
            }

            ArrayList<Integer> sol = new ArrayList<>();
            while(!q.isEmpty()){
                int poll = q.poll();
                sol.add(poll);
//                System.out.println("poll = " + poll);
                Iterator<Integer> children =  childMap.get(poll).iterator();
                while(children.hasNext()){
                    int child = children.next();
                    boolean can = true;
                    if(visit[child] == 1) continue;
                    Iterator<Integer> iterator = parentMap.get(child).iterator();
                    while(iterator.hasNext()){
                        int next = iterator.next();
                        if(visit[next] == 0){
                            can = false;
                            break;
                        }
                    }
                    if(can){
                        q.add(child);
                        visit[child] = 1;
                    }
                }
            }
            if(sol.size() == n){
                for (int i : sol) {
                    bw.write(i +" ");
                }
                bw.write("\n");
            }else{
                bw.write("IMPOSSIBLE\n");
            }
        }
        bw.flush();
    }
}
