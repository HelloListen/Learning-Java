package thinkinjava;

/**
 * Created by zhangbin on 16/5/19.
 */
public class HiddenC {
    public static A makeA() {
        return new C();
    }
}

class C implements A {
    @Override
    public void f() {
        System.out.print("public C.f()");
    }

    public void g() {
        System.out.print("public C.g()");
    }

    void u() {
        System.out.print("package C.u()");
    }

    protected void v() {
        System.out.print("protected C.v()");
    }

    private void w() {
        System.out.print("private C.w()");
    }
}
