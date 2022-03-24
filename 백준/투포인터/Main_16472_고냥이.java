package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_16472_고냥이 {
    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String seq = br.readLine();
        int m = seq.length();
        int start = 0;
        int end = 2;
        int ans = 2;
        if (m < 2) {
            System.out.println(1);
            return;
        }
        map.put(seq.charAt(0), 1);
        if(map.containsKey(seq.charAt(1)))map.put(seq.charAt(1), 2);
        else map.put(seq.charAt(1), 1);

        while (end < m) {
            char endChar = seq.charAt(end);
            if (map.containsKey(endChar)) {
                map.put(endChar, map.get(endChar) + 1);
            } else map.put(endChar, 1);

            while (map.size() > n) {
                char startChar = seq.charAt(start);
                if (map.get(startChar) == 1) {
                    map.remove(startChar);
                } else {
                    map.put(startChar, map.get(startChar) - 1);
                }
                start++;
            }
            int count = 0;
            for (char key : map.keySet()) {
                count += map.get(key);
            }
            ans = Math.max(ans, count);
            end++;
        }

        System.out.println(ans);
    }
}
