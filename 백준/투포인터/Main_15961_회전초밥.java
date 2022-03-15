package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15961_회전초밥 {
    static int n, d, k, c;
    static int[] table;
    static HashMap<Integer, Integer> ateSushi = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        table = new int[n + k - 1];
        for (int i = 0; i < n; i++) {
            table[i] = Integer.parseInt(br.readLine());
            if (i < k) {
                if (!ateSushi.containsKey(table[i]))
                    ateSushi.put(table[i], 0);
                ateSushi.put(table[i], ateSushi.get(table[i]) + 1);
            }

        }
        for (int i = n; i < n + k - 1; i++) {
            table[i] = table[i - n];
        }
        int left = 0;
        int right = k - 1;
        int ans = 0;
//        for(int key : ateSushi.keySet()){
//            System.out.printf("%d : %d 개\n",key,ateSushi.get(key));
//        }
        while (true) {
            int num = ateSushi.size();
            if (!ateSushi.containsKey(c)) {
                num++;
            }
//            System.out.printf("left : %d, right : %d, num :%d\n",left,right,num);
            ans = Math.max(ans, num);
            if(left == n - 1) break;
            if(ateSushi.containsKey(table[right + 1]))
                ateSushi.put(table[right + 1], ateSushi.get(table[right + 1]) + 1);
            else ateSushi.put(table[right + 1], 1);
            ateSushi.put(table[left], ateSushi.get(table[left]) - 1);
            if(ateSushi.get(table[left]) == 0) ateSushi.remove(table[left]);
            left++;
            right++;
        }
        System.out.println(ans);
    }
}
