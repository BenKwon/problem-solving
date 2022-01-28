package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1083_소트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(Integer.parseInt(st.nextToken()));
        }
        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
//            System.out.println("-----------------------------");
//            System.out.println("i = " + i);
            int max = -1;
            int maxIndex = -1;
            for (int j = i + 1; j <= i + s; j++) {
                if(j >= n) break;
//                System.out.println("j = " + j);
                if(seq.get(i) < seq.get(j)){
                    //모든 수는 다르다..
                    if (max < seq.get(j)) {
                        maxIndex = j;
                        max = seq.get(j);
                    }
                }
            }
//            System.out.printf("max : %d, idx : %d\n",max,maxIndex);
            if(max > 0){
                int diff = maxIndex - i;
                s -= diff;
//                seq.set(i, seq.get(maxIndex));
                seq.remove(maxIndex);
                seq.add(i, max);
            }
//            System.out.println();
//            System.out.println("s = " + s);
//            System.out.println();
        }

        for(int k : seq){
            System.out.printf("%d ",k);
        }

    }
}
