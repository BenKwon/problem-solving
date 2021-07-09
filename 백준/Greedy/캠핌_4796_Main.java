package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 캠핌_4796_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> test_case = new ArrayList<>();
        while(true){
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(l == 0 && p == 0 && v ==0) break;
            else{
                test_case.add(new int[]{
                        l,p,v
                });
            }
        }
        int test_case_num = test_case.size();
        for(int t = 0 ; t < test_case_num; t++){
            int l = test_case.get(t)[0];
            int p = test_case.get(t)[1];
            int v = test_case.get(t)[2];
//            System.out.printf("l : %d , p : %d , v : %d\n", l,p,v);
            int tmp = p -l;
            long sol = 0;
            while(true){
//                System.out.println("===========");
//                System.out.println("v = " + v);
//                System.out.println("sol : " + sol);
                if(v <= l){
                    if(v<0) v = 0;
                    sol += v;
                    break;
                }else{
                    sol += l;
                    v -= l;
                }
                v -= tmp;
            }
            System.out.printf("Case %d: %d\n",t+1,sol);
        }

    }
}
