package 백준.DisjointSet;

import java.util.*;
import java.io.*;

public class Main_17619_개구리점프 {
    static int n, q;
    static int[] parent;
    static ArrayList<Log> logs = new ArrayList<>();
    static int x1Range = -1;
    static class Log {
        int x1;
        int x2;
        int y;
        int code;

        public Log(int x1, int x2, int y, int code) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.code = code;
        }
    }

    static int getParent(int num) {
        if (parent[num] == num) return num;
        return parent[num] = getParent(parent[num]);
    }

    static void union(int num1, int num2) {
        num1 = getParent(num1);
        num2 = getParent(num2);
        if (num1 < num2) {
            parent[num2] = num1;
        } else {
            parent[num1] = num2;
        }
    }

    static boolean sameParent(int num1, int num2) {
        return getParent(num1) == getParent(num2);
    }

    public static void recursive(int seq,int nextSeq) {
        if(nextSeq == logs.size()) return;
        Log log1 = logs.get(seq);
        Log log2 = logs.get(nextSeq);
        x1Range = Math.max(x1Range, log1.x2);
        if (canGo(log1.x1, log2)) {
            union(log1.code, log2.code);
        }
        recursive(nextSeq, nextSeq + 1);
    }

    static boolean canGo(int x1, Log next) {
        int start = x1;
        int end = x1Range;
        int left = next.x1;
        int right = next.x2;
        if (left <= start && start <= right) return true;
        if (left <= end && end <= right) return true;
        if (start <= left && left <= end) return true;
        if (start <= right && right <= end) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parent[i + 1] = i + 1;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            logs.add(new Log(x1, x2, y, i + 1));
        }
        Collections.sort(logs, new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2) {
                return o1.x1 - o2.x1;
            }
        });
        recursive(0, 1);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int log1code = Integer.parseInt(st.nextToken());
            int log2code = Integer.parseInt(st.nextToken());
            if(sameParent(log1code,log2code)){
                bw.write(1 +"\n");
            }else{
                bw.write(0 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
