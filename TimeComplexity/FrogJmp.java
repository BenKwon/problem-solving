package TimeComplexity;

public class FrogJmp {

    public static void main(String[] args) {
        System.out.println(solution(40,85,40));
    }

    public static int solution(int X, int Y, int D) {
        int tmp = (Y-X);
        int loopNum = (tmp / D);
        if((tmp%D)!=0){
            loopNum++;
        }

        return loopNum;
    }
}
