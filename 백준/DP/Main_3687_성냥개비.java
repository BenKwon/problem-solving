package 백준.DP;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main_3687_성냥개비 {
    static int remain;
    static int[] num = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    static HashMap<Integer, String> dp = new HashMap<>();
    static ArrayList<Integer>[] canMake = new ArrayList[8];

    public static String dfsMax(int remain, boolean init) {
        if (remain == 0) return "";
        if (remain < 2) return "*";
        if(dp.containsKey(remain)) return dp.get(remain);
        String result = "";
        ArrayList<Integer> nexts;
        if (remain >= 7) nexts = canMake[7];
        else nexts = canMake[remain];
        BigInteger max = BigInteger.ZERO;
        int maxLen = 0;
        for (int next : nexts) {
            String tmp = "" + next;
            BigInteger candidate;
            try{
                tmp += dfsMax(remain - num[next], false);
                candidate = new BigInteger(tmp);
            }catch (Exception e){
                continue;
            }
            if(init){
                if (max.compareTo(candidate) == -1) {
                    maxLen = tmp.length();
                    max = candidate;
                    result = "" + candidate.toString();
                }
            }else{
                if(maxLen <= tmp.length()){
                    if (max.compareTo(candidate) == -1) {
                        maxLen = tmp.length();
                        max = candidate;
                        result = "" + candidate.toString();
                    }
                }
            }
        }
        dp.put(remain, result);
        return result;
    }
    public static String dfsMin(int remain,boolean init) {
        if(remain == 0) return "";
        if(remain < 2) return "*";
        if(dp.containsKey(remain)) return dp.get(remain);
        int index = 0;
        if(remain < 7) index = remain;
        else index = 7;
        BigInteger min = new BigInteger("9999999999999999999999999");
        String minStr = "9999999999999999999999999";
        for(int next : canMake[index]){
            if(init && next == 0) continue;
            String tmp = "" + next;
            tmp += dfsMin(remain- num[next], false);
            if(tmp.contains("*")) continue;
            BigInteger candidate = new BigInteger(tmp);
            if(tmp.length() < minStr.length()){
                minStr = tmp;
                min = candidate;
            }else if(tmp.length() == minStr.length()){
                if(candidate.compareTo(min) == -1){
                    minStr = tmp;
                    min = candidate;
                }
            }
        }
        dp.put(remain, minStr);
        return minStr;
     }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int i = 2; i <= 7; i++) {
            canMake[i] = new ArrayList<>();
            for (int n = 0; n < 10; n++) {
                if (num[n] <= i) {
                    canMake[i].add(n);
                }
            }
        }
        for (int t = 0; t < test; t++) {
            remain = Integer.parseInt(br.readLine());
            //맵 초기화
            System.out.printf("%s ",dfsMin(remain,true));
            dp.clear();
            System.out.printf("%s \n",dfsMax(remain, true));
            dp.clear();
        }
    }
}
