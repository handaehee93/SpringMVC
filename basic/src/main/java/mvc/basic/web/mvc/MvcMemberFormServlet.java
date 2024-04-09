package mvc.basic.web.mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 먼저 연결할 jsp파일의 경로를 입력한다.
        //  webapp 아래 WEB-INF를 생성하여 아래 jsp파일을 위치 시키면 url로 직접 jsp 파일에 접근 할 수 없고 반드시
        // 컨트롤러 내부에서 forward 하거나 해야 jsp파일에 접근할 수 있다. was의 규칙이다.

        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // 아래 dispatcher.forward()는 다른 컨트롤러(서블릿)나 뷰(jsp)로 이동할 수 있는 기능이다
        // forward는 서버 내부에서 일어나는 호출이다. 무슨 말이냐면 redirect와 비교해서 이해해 보면 redirect는
        // 응답이 클라이언트에 전달이 된 후, 클라이언트가 다른 경로로 다시 요청을 하는 것을 말한다.
        // redirect의 경우 새로운 경로로 다시 요청하는 것이기 때문에 url경로가 바뀌게 된다. 쉽게 말해 호출이 2번 되는 것
        // 하지만 forward의 경우 클라이언트는 한번 호출한 것이고, 그냥 서버 내부에서 발생하는 것

        dispatcher.forward(request, response);
    }

}
