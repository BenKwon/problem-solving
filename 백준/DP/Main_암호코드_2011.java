package 백준.DP;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.io.*;

/**
 * 실버 1이지만 푸는데 오래 걸렸다.
 * 그 이유는 누가 질문란에 00009 는 1이 나와야 된다고 잘못써놔서 이것때문에 시간을 날렸다.
 * 이 문제의 핵심은 0에 주목해야한다.
 * 0이 2번연속으로 오거나 0 앞에 1 혹은 2이외의 수가 온다면 암호를 해독할 수 없다.
 * 또한 2뒤에 6보다 큰수가 오면 해독할수없다.
 *
 * 중요한 점은 0이 나왔을때 0의 앞은 항상 1또는 2이여야하고 암호를 해독할때 0은 항상 자기 앞의 정수와
 * 함께 붙여서 해독되어야 한다는 점점 */
public class Main_암호코드_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String crypto = br.readLine();
        char[] seq = crypto.toCharArray();
        int n = seq.length;
        int[] dp = new int[n];
        dp[0] = 1;

        int start_idx = 0;

        if(seq[start_idx] == '0'){
            System.out.println(0);
            return;
        }
        if(n ==1){
            System.out.println(1);
            return;
        }
        if(seq[start_idx + 1] == '0'){
            if(seq[start_idx] >= '3'){
                System.out.println(0);
                return;
            }
        }
        dp[start_idx] = 1;
        if (seq[start_idx] == '1') {
            if(seq[start_idx+1] == '0') dp[start_idx + 1] = 1;
            else dp[start_idx + 1] = 2;
        } else if (seq[start_idx] == '2') {
            if (seq[start_idx+1] <= '6') dp[start_idx + 1] = 2;
            else dp[start_idx + 1] = 1;
            if(seq[start_idx+1] == '0') dp[start_idx + 1] = 1;

        }else{
            dp[start_idx + 1] = 1;
        }


        for (int i = start_idx + 2; i < n; i++) {
            if(seq[i] == '0'){
                if(seq[i-1] >= '3' || seq[i-1] == '0'){
                    System.out.println(0);
                    return;
                }
            }
            if (seq[i - 1] == '1') {
                if(seq[i] == '0'){
                    dp[i] = dp[i - 2]%1000000 ;
                }else{
                    dp[i] = dp[i - 1]%1000000+ dp[i - 2]%1000000 ;
                }
            } else if (seq[i - 1] == '2') {
                if(seq[i] == '0'){
                    dp[i] = dp[i - 2]%1000000 ;
                }
                else{
                    if (seq[i] > '6') dp[i] = dp[i - 1];
                    else dp[i] = dp[i - 1]%1000000 + dp[i - 2]%1000000;
                }
            }  else {
                dp[i] = dp[i - 1]%1000000;
            }

    }

        System.out.println(dp[n-1]%1000000);

    }
}
