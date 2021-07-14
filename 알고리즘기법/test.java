package 알고리즘기법;

public class test {
    public static int count  = 0;

    public static void loop(){
        count++;
        if(count >  1000) return;
        loop();
    }
    public static void main(String[] args) {
        loop();
    }
}
