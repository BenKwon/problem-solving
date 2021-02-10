package greedy백준;
import java.util.*;
/*
내가 푼방법
자릿수를 의미하는 인덱스를 가진 ArrayList 배열 store를 하나 만든다.
예를들어 store[0] 은 1의 자리에 있는 모든 알파벳을 모아놓은 배열을 값으로 가진다.
이 입력받은 알파벳들을 하나하나 각자 수의 자리에 맞는 곳에 store[i].add(c) 넣어준다.

n의 자리 가중치 = 10의 n제곱
1의자리 부터 무슨 알파벳들이 있는지 탐색하면서 각 알파벳들의 갯수를 센다.
각 알파벳의 갯수를 센다음 1의자리의 가중치에 갯수를 곱한후 HashMap weight를 만들고 알파벳을 key로두고 가중치를 value로하여 저장한다.
이렇게 1의자리 10의자리 100의자리...를 store활용해서 총 알파벳별 가중치를 계산하여 HashMap에 넣어둔다.
그후 Weight 해쉬맵의 key set을 value(가중치)를 기준으로 내림차순으로 정렬한다.
내림차순을 하고난 key set의 순서가 차례대로 가중치가 9부터 1씩 작아지는 가중치를 가지게 된다.
이후 이 가중치를 이용하여 주어진 알파벳에 수를 대입하여 계산을 한다.
--------------------------------
참고) HashMap의 keyset을 value순으로 정렬하는 법을 검색해서 참고하였다.
 */
//1339번
public class 단어수학 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int word_num = scanner.nextInt();
        int length;
        ArrayList<String> words = new ArrayList<>();
        String word;
        scanner.nextLine();
        for(int i = 0 ; i < word_num; i++){
            word = scanner.nextLine();
            if(i == 0 ){
                words.add(word);
            }
            else{
                length = word.length();
                int j = 0;
                while(length < words.get(j).length()){
                    j++;
                    if(j >= words.stream().count()){
                        break;
                    }
                }
                words.add(j, word);
            }
        }
        int max_len = words.get(0).length();
        HashMap<Character, Integer> map = new HashMap<>();
        ArrayList<Character>[] store = new ArrayList[8];
        for(int i = 0 ; i < 8; i++){
            store[i] = new ArrayList<>();
        }
        for(int i = 0; i < word_num; i++){
            length = words.get(i).length();
            for(int j = 0; j < length;j++){
                store[length-j-1].add(words.get(i).charAt(j));
            }
        }
        int max_value = 9;
        Map<Character, Integer> weight = new HashMap<>();
        HashMap<Character, Integer> alphabet_nums = new HashMap<>();
        for(int i = 0 ;  i < 8; i++){
            alphabet_nums = new HashMap<>();
            for(Character c : store[i]){
                if(weight.get(c) == null){
                    weight.put(c,0);
                }
                if(alphabet_nums.get(c) == null){
                    alphabet_nums.put(c, 1);
                }
                else{
                    alphabet_nums.put(c, alphabet_nums.get(c)+1);
                }
            }
            int finalI = i;
            alphabet_nums.forEach((key, value)->{
                weight.put(key, (int) (weight.get(key) + value*(Math.pow(10,finalI))));
            });
        }


        List<Character> keySetList = new ArrayList(weight.keySet());

        Collections.sort(keySetList, (o1, o2) -> (weight.get(o2).compareTo(weight.get(o1))));
        for(Character key : keySetList) {
            map.put(key,max_value--);
        }


        int result = 0;
        String s = "";
        for(int i = 0; i < word_num; i++){
            s = "";
            length = words.get(i).length();
            for(int j = 0; j < length;j++){
                s += map.get(words.get(i).charAt(j));
            }
            result += Integer.parseInt(s);
        }

        System.out.println(result);
    }
}
