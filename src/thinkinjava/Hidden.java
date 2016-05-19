package thinkinjava;

/**
 * Created by zhangbin on 16/5/19.
 */
public class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();

        HiddenImplementation.callHiddenMathod(a,"f");
        HiddenImplementation.callHiddenMathod(a,"g");
        HiddenImplementation.callHiddenMathod(a,"u");
        HiddenImplementation.callHiddenMathod(a,"v");
        HiddenImplementation.callHiddenMathod(a,"w");
    }
}

class InnerA {
    private static class C implements A {
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

        protected void v(){
            System.out.print("protected C.v()");
        }

        private void w() {
            System.out.print("private C.w()");
        }

    }

    public static A makeA() {
        return new C();
    }
}
