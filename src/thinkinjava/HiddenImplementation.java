package thinkinjava;

import java.lang.reflect.Method;

/**
 * Created by zhangbin on 16/5/19.
 */
public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        callHiddenMathod(a, "g");
        callHiddenMathod(a, "u");
        callHiddenMathod(a, "v");
        callHiddenMathod(a, "w");
    }

    static void callHiddenMathod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
}
