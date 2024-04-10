package mvc.basic.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.web.frontcontroller.MyView;

import java.io.IOException;

public interface ControllerV2 {

    // 각각의 컨트롤러들은 View로 직접 연결하는 것이 아니라 View 객체를 반환하게 된다.
    // 따라서 아래 처럼 MyView라는 View 객체를 리턴하는 메서드를 만들고, 각각의 컨트롤러에서 구현하게 한다.
    MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
