package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 풀이
 * 입국장을 빠져 나가는 순서를 이용하여 푼다.
 * 입구장을 빠져 나가는 배열을 앞에서 부터 창구에 배치시켜가면서 푼다.
 * map을 이용하여 창구 3개를 만든다.
 * 최초에 각 창구에 들어갈수 있는 값은 0이상만 가능하다.
 * 입구장을 빠져 나가는 배열에서 한개씩 값을 가져와 각 창구중 한개에 배정 시키는데
 * 현재 창구에 들어갈 수 있는 값의 최솟값보다 배열에서 가져온 값이 클때 그 창구에 배정시킨다.
 * 만약 여러 창구에 배정이 가능하다면 그 최솟밧과 배열에서 가져온 값과의 차이가 가장 작은 창구에 배정시킨다.
 * 그 이유는 이런식으로 창구를 다음에올 숫자에 대해 더 많은 숫자를 허용하기 위해서 효율적으로 배치해야 하기 때문이다.
 *
 * 만약 배정시킬수있는 창구가 없다면 "NO"출력
 *
 */
public class PassPortControl_16288_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] people = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        s = br.readLine();
        st = new StringTokenizer(s);
        for(int i = 0 ; i < n ; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < k ;i++){
            map.put(i+1,0);
        }

        String sol ="YES";
        for(int i = 0 ; i < n ; i ++){
            int person = people[i];
            int min = 1000;
            int min_key = 1000;
            int count = 0;
            for(int key : map.keySet()){
                int under_value = map.get(key);
                if(under_value < person){
                    count++;
                    if(min > (person-under_value)){
                        min = person - under_value;
                        min_key = key;
                    }
                }
            }
            if(count == 0){
                sol = "NO";
                break;
            }else{
                map.put(min_key, person);
            }
        }
        System.out.println(sol);
    }
}
