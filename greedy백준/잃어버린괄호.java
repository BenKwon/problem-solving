package greedy백준;

import java.util.Scanner;

//1541번
public class 잃어버린괄호 {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String math = scanner.nextLine();
        int length_math = math.length();
        StringBuilder sb = new StringBuilder();
        int tmp_i;
        int flag = 0;
        int m_flag = 0;
        String[] first = math.split("-");
        int len_split = first.length;
        int result = 0;
        String[] tmp =first[0].split("\\+");
        if(tmp.length > 1){
            for(String k : tmp){
                result += Integer.parseInt(k);
            }
        }
        else{
            result = Integer.parseInt(first[0]);
        }
        for(int i = 1; i < len_split; i++){
            tmp =first[i].split("\\+");
            if(tmp.length > 1){
                for(String k : tmp){
                    result -= Integer.parseInt(k);
                }
            }
            else{
                result -= Integer.parseInt(first[i]);
            }
        }
        System.out.println(result);
    }
}


/*
//1541
public class 잃어버린괄호 {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String math = scanner.nextLine();
//        System.out.println(math);
        int length_math = math.length();
        StringBuilder sb = new StringBuilder();
        int tmp_i;
        int flag = 0;
        int m_flag = 0;
        sb.append(math);
        for(int i = 0 ; i < length_math; i++){
            if(math.charAt(i) == '-' && flag == 0){
                sb.insert(i+1, "(");
                length_math++;
                i++;
                flag = 1;
                math = new String(sb);
                m_flag=1;

            }
            if(math.charAt(i) == '-' && flag == 1){
                sb.insert(i,")");
       //         i++;
                length_math++;
                math = new String(sb);
                flag = 0;
                m_flag=1;
            }
        }
        if(m_flag==1){
            sb.insert(math.length(), ")");
        }
        math = new String(sb);
        System.out.println(math);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        System.out.println(engine.eval(math));

    }
}

 */