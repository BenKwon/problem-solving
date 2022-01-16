package 백준.DisjointSet;
import java.io.*;
import java.util.*;
public class Main_1976_여행가자 {
    static int[] arr;
    static int n,m;

    public static int getParent(int city) {
        if(arr[city] == city) return city;
        return arr[city] = getParent(arr[city]);
    }
    public static void union(int cityA, int cityB){
        cityA = getParent(cityA);
        cityB = getParent(cityB);
        if(cityA < cityB) arr[cityB] = cityA;
        else arr[cityA] = cityB;
    }

    public static boolean checkConnect(int cityA, int cityB){
        if(getParent(cityA) == getParent(cityB)) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connectInfo = Integer.parseInt(st.nextToken());
                if(connectInfo == 1){
                    union(i,j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> schedule = new ArrayList<>();
        while(st.hasMoreTokens()){
            schedule.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i < m; i++) {
            int cityA = schedule.get(i - 1);
            int cityB = schedule.get(i);
//            System.out.printf("%d  %d\n",cityA,cityB);
            if (!checkConnect(cityA, cityB)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }
}
