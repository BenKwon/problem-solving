package 백준.DisjointSet;

import java.util.*;
import java.io.*;
public class Main_1717_집합의표현 {
    static int n, m;
    static int[] arr;
    static int getParent(int num){
        if(arr[num] == num) return num;
        return arr[num] = getParent(arr[num]);
    }

    static void union(int num1, int num2) {
        num1 = getParent(num1);
        num2 = getParent(num2);
        if(num1 < num2){
            arr[num2] = num1;
        }else{
            arr[num2] = num1;
        }
    }

    static boolean checkSameSet(int num1, int num2) {
        if(getParent(num1) == getParent(num2)) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for(int i = 1 ; i <= n ;i++){
            arr[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(operation == 0){
                union(a,b);
            }else{
                if(checkSameSet(a,b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
}
