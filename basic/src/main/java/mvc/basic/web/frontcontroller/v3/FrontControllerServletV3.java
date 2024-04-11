package mvc.basic.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.web.frontcontroller.ModelView;
import mvc.basic.web.frontcontroller.MyView;
import mvc.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.taglibs.standard.lang.jstl.ImplicitObjects.createParamMap;

public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new
                MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new
                MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new
                MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // request의 파라미터 key와 value를 map에 담는다.
        Map<String, String> paramMap = createParamMap(request);

        // map에 담긴 파라미터 정보를 컨트롤러에 전달한다.
        // 컨트롤러는 view 경로와 데이터가 담긴 modelView 객체를 반환하고
        ModelView modelView = controller.process(paramMap);

        // modelView에서 view 경로를 가져와 viewResolver에 넣어 view 경로를
        // 완성하고
        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        // render함수를 넣어 포워딩 한다.
        view.render(modelView.getModel(), request, response);
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