package programmers.공채;

import java.util.*;

/**
 * 기본요금 기본시간 단위 시간(분) 단위시간당 요금
 * 주차 정보 가들어고 차량 번호별로 각각 주차요금을 계산하는문제
 *
 *
 */
public class 프로그래밍3 {
    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int baseTime = fees[0];
        int baseFee = fees[1];
        int time = fees[2];
        int feePerTime = fees[3];
        Map<String, ArrayList<Integer>> map =new HashMap<>();
//        Map<Integer, Integer> remain_base = new HashMap<>();
        Map<String, Integer> sol_info = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String[] recordSplit = records[i].split(" ");
            //시간 분단위로 변환
            String tInfo = recordSplit[0];
            String[] tInfoSplit = tInfo.split(":");
            int convertTime = (stoi(tInfoSplit[0]) * 60) + stoi(tInfoSplit[1]);
            String carNum = recordSplit[1];
            if(map.get(carNum) == null) {
                map.put(carNum, new ArrayList<>());
//                remain_base.put(carNum, baseTime);
            }
            map.get(carNum).add(convertTime);
        }

        Set<String> key = map.keySet();
        for (String k : key) { // k == 차량번호
            ArrayList<Integer> outInInfo = map.get(k);
            float totalTime = 0;
            for (int i = 0; i < outInInfo.size(); i++) {
                if (i + 1 < outInInfo.size()) {
                    totalTime += outInInfo.get(i + 1) - outInInfo.get(i);
                    i++;
                }else{
                    totalTime += 1439 - outInInfo.get(i); //23시 59분과 출차정보가 없는 입차정보간의 시간 차
                }
            }
            //fee 계산
//            totalTime -= baseTime;
//            System.out.printf("carNum : %s, totalTime: %f ,size : %d\n", k, totalTime,outInInfo.size());
//            System.out.printf("basefee : %d , basetime: %d , time :%d ,feePerTime :%d\n", baseFee, baseTime, time, feePerTime);
            int totalFee =baseFee;
            if(totalTime > baseTime){
//                System.out.printf("result : %f \n",(totalTime - baseTime) / time);
                totalFee += Math.ceil((totalTime - baseTime) / time) * feePerTime;
            }
            sol_info.put(k, totalFee);
        }
        ArrayList<String[]> sol = new ArrayList<>();
        for (String carNum : sol_info.keySet()) {
            sol.add(new String[]{carNum, String.valueOf(sol_info.get(carNum))});
        }
        Collections.sort(sol, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });

        answer = new int[sol.size()];
        for (int i = 0; i < sol.size(); i++) {
            answer[i] = stoi(sol.get(i)[1]);
            System.out.printf("%d ",answer[i]);
        }

        return answer;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) {
        int[] fee = {
                180, 5000, 10, 600
        };
        String[] records = {
                "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        };
        float a = 334;
        int b = 180;
//        System.out.println((a-b)/10);
        solution(fee, records);
    }
}
