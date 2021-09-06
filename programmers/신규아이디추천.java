package programmers;

public class 신규아이디추천 {
    public static String solution(String new_id) {
        String answer = "";
        char a = 'a';
        char z = 'A';
        int k = 'a' - 0;
        //1단계
        for(int i = 0 ; i < new_id.length(); i++){
            char next = new_id.charAt(i);
            if('A' <= next && next <= 'Z'){
                char chg = (char) (next + 32);
                new_id = new_id.substring(0,i) + chg + new_id.substring(i+1, new_id.length());
            }
        }

        //2단계
        for(int i = 0 ; i < new_id.length(); i++){
            char next = new_id.charAt(i);
            if(Character.isDigit(next))continue;
            if(('A' <= next && next <= 'Z')|| ('a' <= next && next <= 'z')) continue;
            if((next == '-')|| next == '_'|| next=='.') continue;
            new_id = new_id.substring(0,i) + new_id.substring(i+1, new_id.length());
            i--;
        }
        //3단계
        int start = -1;
        int end = -1;
        int num = 0;
        for (int i = 0; i < new_id.length(); i++) {
            char next = new_id.charAt(i);
            if(num == 0 && next == '.'){
                start = i;
                num++;
            }
            if(num > 0 && next == '.') num++;
            if(next != '.'){
                if(num >= 2){// .이 2개이상 연속되어 있음
                    end = i - 1;
                    new_id = new_id.substring(0, start) + '.' + new_id.substring(end + 1, new_id.length());
                    i = i - num + 1;
                    num = 0;
                }
                start = -1;
                end = -1;
                num = 0;
            }
        }
        if(num >= 2){// .이 2개이상 연속되어 있음
            end = new_id.length() - 1;
            new_id = new_id.substring(0, start) + '.' + new_id.substring(end + 1, new_id.length());
        }

        //4단계
        if(new_id.length()>0){
            if(new_id.charAt(0) == '.'){
                new_id = new_id.substring(1);
            }
            if(new_id.length() >0){
                if(new_id.charAt(new_id.length()-1) == '.'){
                    new_id = new_id.substring(0, new_id.length() - 1);
                }
            }

        }

        //5단계
        if(new_id.length() == 0){
            new_id = "a";
        }
//        System.out.printf("%d 단계 : %s \n",5, new_id);

        //6단계
        if(new_id.length() >= 16){
            new_id = new_id.substring(0,15);
            if(new_id.charAt(14) == '.'){
                new_id = new_id.substring(0,14);
            }
        }
//        System.out.printf("%d 단계 : %s \n",6, new_id);

        //7단계
        if(new_id.length()<=2){
            char last = new_id.charAt(new_id.length()- 1);
            while(new_id.length() < 3){
                new_id += last;
            }
        }
//        System.out.printf("%d 단계 : %s \n",7, new_id);
        return new_id;
    }
    public static void main(String[] args) {
        String a = "...!@BaT#*..y.abcdefghijklm";
        String c = "z-+.^.";
        String d ="=.=";
        String e = "123_.def";
        String f = "abcdefghijklmn.p";
//        solution(a);
//        solution(c);
//        solution(d);
//        solution(e);
        solution(f);

    }
}
