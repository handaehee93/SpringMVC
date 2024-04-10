package mvc.basic.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.web.frontcontroller.MyView;
import mvc.basic.web.frontcontroller.v2.controller.MemberFormControllerV2;
import mvc.basic.web.frontcontroller.v2.controller.MemberListControllerV2;
import mvc.basic.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 이제 모든 요청은 FrontController로 들어오므로 들어올 수 있는 요청 정보를 아래 처럼 매핑해 둔다.
    public FrontControllerServletV2( ) {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // getRequestURI를 사용하면 URL에서 호스트 뒤에 붙는 부분을 가져올 수 있다.
        String requestURI = req.getRequestURI();
        // 요청 받은 URI에 매핑 되는 컨트롤러를 map에서 꺼낸다.
        ControllerV2 controller = controllerMap.get(requestURI);
        // 만약  일치하는 컨트롤러가 없으면 Not Found 응답을 보내고
        if(controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 일치하는 컨트롤러가 있으면 이전가는 달리 각각의 컨트로럴에서 view 객체를 받아와서
        // 내부에 render 메서드를 호출하여 화면을 연결한다.
        MyView view = controller.process(req, resp);
        view.render(req,resp);

    }
}
