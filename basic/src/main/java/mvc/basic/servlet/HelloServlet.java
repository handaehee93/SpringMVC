package mvc.basic.servlet;


import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// WebServlet을 붙이면 해당 클래스가 서블릿으로 등록된다.
// 서블릿은 HttpServlet을 상속 받아야 하고, 그 다음 내부에 service 메서드를 만든다.
// 그러면 해당 서블릿이 호출되면 service 메서드가 호출되게 된다.
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // Http 요청이 오면 WAS 즉, 서블릿 컨테이너가 request, response 객체를 만들어서 서블릿에 전달해 준다.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // "/hello" 경로 호출시 해당 메서드가 호출 되는지 확인
        System.out.println("HelloServlet.service");

        // "/hello" 경로 호출시  WAS가 전달한 req, resp 객체 확인
        System.out.println("req = " + req); // req = org.apache.catalina.connector.RequestFacade@3202364e
        System.out.println("resp = " + resp); // resp = org.apache.catalina.connector.ResponseFacade@549f7f1d

        //  "/hello?useranme=hdh" 요청시 쿼리 파마리터 값 가져오기
        // 서블릿은 쿼리 파라미터를 getParmeter를 통해 쉽게 가져올 수 있다.
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // 이번엔 응답 메세지를 구성해 보자.
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello" + username);
    }
}
