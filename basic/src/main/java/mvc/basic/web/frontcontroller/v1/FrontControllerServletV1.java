package mvc.basic.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.web.frontcontroller.v1.controller.MemberFormControllerV1;
import mvc.basic.web.frontcontroller.v1.controller.MemberListControllerV1;
import mvc.basic.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 이제 모든 요청은 FrontController로 들어오므로 들어올 수 있는 요청 정보를 아래 처럼 매핑해 둔다.
    public FrontControllerServletV1( ) {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // getRequestURI를 사용하면 URL에서 호스트 뒤에 붙는 부분을 가져올 수 있다.
        String requestURI = req.getRequestURI();
        // 요청 받은 URI에 매핑 되는 컨트롤러를 map에서 꺼낸다.
        ControllerV1 controller = controllerMap.get(requestURI);
        // 만약  일치하는 컨트롤러가 없으면 Not Found 응답을 보내고
        if(controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 일치하는 컨트롤러가 있으면 해당 컨트롤러에 request, response를 보낸다.
        controller.process(req ,resp);

    }
}
