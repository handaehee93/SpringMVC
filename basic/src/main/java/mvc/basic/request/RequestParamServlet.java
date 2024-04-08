package mvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getParam(req);
        //resp.getWriter().write("ok");
    }

    // Get 방식 + 쿼리 파라미터 조합으로 전송된 데이터 조회 메서드
    private static void getParam(HttpServletRequest request) {
        // 전체 파라미터 조회 ( 🔥 getParameterNames() )
        // 전달받은 쿼리 파라미터의 Key값을 순회하여 getParameter에 하나씩 넣어서 그 값을 가져 오는 것
        // request.getParameterNames().asIterator()는 컬렉션을 Enumeration 인터페이스로 바꿔주는 것이다.
        // 여기에는 파리미터의 key 값들이 들어있을 것이다.
        // forEachRemaining은 각 요소에 지정된 작업을 수행하도록 하는 것.

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("전체 파라미터 = " + paramName +
                        "=" + request.getParameter(paramName)));


        // 단일 파라미터 조회 ( 🔥 getParameter("키값 전달") )
        // getParameter의 쿼리 파라미터의 key값을 전달한다.

        String username = request.getParameter("username");
        System.out.println("개별 파라미터 값 = " + username);
        String age = request.getParameter("age");
        System.out.println("개별 파라미터 값 = " + age);

        // 복수 파라미터 조회 (🔥 getParameterValues("키값 전달")
        // 하나의 key에 여러 값을 받았을 때는 getParameterValues로 받아야 한다.
        // 만약 getParameter로 받으면 getParameterValues의 첫번째 값을 반환 한다.
        // ?username=hdh&username=hdh2&age=31 이런식으로 username이라는 key에
        // 복수의 값들을 전달할 수 있다.
        // 참고로 복수 파라미터를 전달을 했는데 getParameter로 받으면
        // getParameterValues의 첫번째 값을 반환한다.

        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("복수 파라미터 값들 =" + name);
        }
    }


}
