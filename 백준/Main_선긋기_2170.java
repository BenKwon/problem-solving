package 백준;

import java.io.*;
import java.util.*;
//정렬, 스위핑
public class Main_선긋기_2170 {
    static class Line{
        int start;
        int end;
        int length;
        public Line(int start, int end){
            this.start = start;
            this.end = end;
            this.length = end - start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }
        Collections.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });
        int back = lines.get(0).end;
        int lenAccum = lines.get(0).length;
        for (int i = 1; i < n; i++) {
            Line line = lines.get(i);
            int len = line.length;
            if(line.start < back && line.end < back){
                len = 0;
            }else{
                if(line.start < back) len = line.end - back;
                back = line.end;
            }
            lenAccum += len;
        }
        System.out.println(lenAccum);

    }
}
