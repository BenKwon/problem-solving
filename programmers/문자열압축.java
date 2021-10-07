package programmers;

class 문자열압축 {
    public int solution(String s) {
        int answer = 0;
        int n = s.length();
        answer = n;
        int min = Integer.MAX_VALUE;
        if(n == 1 ) return answer = 1;
        for(int i = 1;  i <= n/2 ;i++){
            int tmp_sum = 0;
            String before = s.substring(0, i);
            int count = 1;
            // System.out.printf(" i : %d   start : ",i);
            // System.out.printf(" %s \n",before);
            int remain = 0;
            for(int j = i ; j + i <= n ; j += i){
                String next = s.substring(j, j + i);
                remain = j + i;
                // System.out.printf(" %s \n",next);
                if(before.compareTo(next) == 0){
                    // System.out.printf("count increase\n");
                    count++;
                }else{
                    // System.out.printf("count : %d !!\n", count);
                    if(count == 1) tmp_sum += i;
                    else {
                        if(count < 10){
                            tmp_sum += i + 1;
                        }else if(count < 100){
                            tmp_sum += i + 2;

                        }else if(count < 1000){
                            tmp_sum += i + 3;
                        }else{
                            tmp_sum += i + 4;
                        }
                    }
                    count = 1;
                    before = next;
                }
                // System.out.println(" tmp sum : " + tmp_sum);
            }
            if(count == 1) tmp_sum += i;
            else {
                if(count < 10){
                    tmp_sum += i + 1;
                }else if(count < 100){
                    tmp_sum += i + 2;

                }else if(count < 1000){
                    tmp_sum += i + 3;
                }else{
                    tmp_sum += i + 4;
                }
            }
            // System.out.println("remain : " + remain);
            tmp_sum += n - remain;
            min = Math.min(min,tmp_sum);
        }
        // System.out.println(min);

        return answer = min;
    }
}