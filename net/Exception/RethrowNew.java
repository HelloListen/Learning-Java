
/**
 * Created by zhangbin on 15/12/22.
 */
public class RethrowNew {
    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown from f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace(System.out);
                throw new TwoException("form inner try");
            }
        } catch (TwoException e) {
            System.out.println("Caught in outer try, e.printStackTrace()");
            e.printStackTrace();
        }
    }
}

class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}

class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}
