package 백준.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class No1699_powSum {
    static int cache[] = new int [110000];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=1; i<100001; i++){
            for(int j =1; j*j <=i; j++){
                if(cache[i] > cache[i- j*j]+1 || cache[i] == 0){
                    cache[i] = cache[i-j*j]+1;
                }
            }
        }
        System.out.println(cache[n]);
    }
}
