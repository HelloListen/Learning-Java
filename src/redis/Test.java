package redis;

/**
 * Created by zhangbin on 16/5/6.
 */

public class Test {
    public static void main(String[] args){
        int x = 2;
        Modify modify = new Modify();
        modify.modify(x);
        System.out.println(x);
    }
}

class Modify {
    void modify(int x) {
        x = x * 2;
    }
}