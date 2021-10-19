package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2304_창고다각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> info = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            info.add(new int[]{l, h});
        }
        info.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] greater = new int[n];
        int[] lower = new int[n];
        for (int i = 0; i < n; i++) {
            int[] cur = info.get(i);
            int great = 0;
            int low = 0;
            int min = 0;
            int max = cur[1];
            boolean greatFind = false;
            for (int j = i + 1; j < n; j++) {
                int[] next = info.get(j);
                if(next[1] < cur[1]){
                    if(next[1] >= min){
                        low = j;
                        min = next[1];
                    }
                }else{
                    if(!greatFind){
                        great = j;
                        max = next[1];
                        greatFind = true;
                    }
                }
            }
            greater[i] = great;
            lower[i] = low;
        }
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] cur = info.get(i);
            int next = 0;
            if(greater[i] != 0){
                next = greater[i];
                sum += (info.get(next)[0] - (cur[0] + 1))*cur[1];
            }else{
                next = lower[i];
                sum += (info.get(next)[0] - (cur[0] + 1))*info.get(next)[1];
            }
            sum += cur[1];
            i = --next;
        }
        sum += info.get(n-1)[1];

        System.out.println(sum);

    }
}
