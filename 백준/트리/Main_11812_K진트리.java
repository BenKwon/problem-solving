package 백준.트리;

import java.io.*;
import java.util.*;

public class Main_11812_K진트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken()) - 1;
            long y = Long.parseLong(st.nextToken()) - 1;
            if (k == 1) {
                bw.write(Math.abs(x - y) + "\n");
                continue;
            }
            //공통 조상 찾기
            HashMap<Long, Long> parents = new HashMap<>();
            long length = 0;
            parents.put(x, length++);
            //x를 포함한 x조상 기록
            while (true) {
                long parent = x / k;
                if (x % k == 0 && x > 0) {
                    parent--;
                }
                if (!parents.containsKey(parent))
                    parents.put(parent, length++);
                if (parent == 0) break;

                x = parent;
            }
            length = 0;
            if (parents.containsKey(y)) {
                length += parents.get(y);
            } else {
                length++;
                while (true) {
                    long parent = y / k;
                    if (y % k == 0) {
                        parent--;
                    }
                    if (parents.containsKey(parent)) {
                        length += parents.get(parent);
                        break;
                    }
                    length++;
                    y = parent;
                }
            }
            bw.write(length + "\n");
        }
        bw.flush();
    }
}
