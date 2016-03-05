package servletsAndJSP;

import com.listenzhangbin.bean.Dog;
import com.listenzhangbin.bean.Person1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangbin on 16/3/5.
 */
public class ELTest extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        Person1 p =new Person1();
        p.setName("Evan");

        Dog dog = new Dog();
        dog.setName("Spike");
        p.setDog(dog);

        request.setAttribute("person1",p);
        RequestDispatcher view = request.getRequestDispatcher("result1.jsp");
        view.forward(request,response);
    }
}
