package servletsAndJSP;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by zhangbin on 16/3/1.
 */
public class SessionBindingListenerDog implements HttpSessionBindingListener {
    private String breed;

    public SessionBindingListenerDog(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        //在一个会话中时要执行的代码
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        //已经不在一个会话中时要执行的代码
    }
}
