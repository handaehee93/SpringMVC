package mvc.basic.web.frontcontroller.v4;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.web.frontcontroller.MyView;
import mvc.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import mvc.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/ v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new
                MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new
                MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new
                MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // request의 파라미터 정보를 map으로 만들고, 데이터를 저장할 수 있는 model을 만들어 각 컨트롤러에 전달한다.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); //추가

        // 컨트롤러는 파라미터 정보와 model(데이터를 저장할 수 있는 그릇)을 전달 받고 데이터를 저장한 뒤 viewName만 반환한다.
        String viewName = controller.process(paramMap, model);
        MyView view = viewResolver(viewName);

        // 프론트 컨트롤에서 전달한 model을 참조형 데이터이기 때문에 각 컨트롤러에 전달하여 컨트롤러에서 model에 데이터를 저장하면
        // 프론트 컨트롤러에서 전달한 model에 똑같이 데이터가 담기기 때문에
        // 아래와 같이 데이터가 담긴 model을 MyView에 전달할 수 있다.
        view.render(model, request, response);
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
