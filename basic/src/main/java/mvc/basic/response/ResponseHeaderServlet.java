package mvc.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 응답 메세지의 status-line 값 입력, 상태 코드
        resp.setStatus(HttpServletResponse.SC_OK);

        // response-header 값 입력
        // setHeader를 통해 헤더 값 설정할 수 있다.
        // content-type지정, 캐시 무효화, my header처럼 내가 원하는 임의의 헤더를 넣을 수도 있다.
        // 이제 url을 통해 요청을 하고 개발자 도구에서 responseheader를 확인해 보면 아래의 값들이 들어 온것을 확인할 수 있다.
        resp.setHeader("Content-type","text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        // 아래는 내가 지정한 임의의 헤더 값
        resp.setHeader("my-header", "hello");
        resp.setHeader("your-header","world");
        
        // 아래는 응답 정보를 더 쉽게 해주는 기능을 사용하는 메서드들
        content(resp);
        cookie(resp);
        redirect(resp);

    }


    // 좀더 편하게 하는 방법도 있다.
    // 응답을 아래 처럼 더 쉽게 할 수도 있다.
    private void content (HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
    }

    // 응답 헤더에 쿠키 설정
    private void cookie (HttpServletResponse resp) {
        // 쿠키 객체를 생성해서 key,value 값을 넣는다.
        Cookie cookie = new Cookie("My-Cookie", "VeryGood");
        cookie.setMaxAge(50); // 쿠키 유효시간 600초 의미

        // 응답 메세지에 쿠키 객체를 전달한다.
        resp.addCookie(cookie);
    }

    // redirect 경로를 입력하면 응답 헤더에 Location 정보가 들어간다.
    // redirect 경로를 입력해 주면 처음에 302코드와 함께 응답 헤더에 Locatin 정보가 들어가고, 클라이언트는 해당 경로로 다시 요청을 하게 되고
    // 요청이 정상적으로 수행이 되면 200코드가 반환이 되게 되는 것.
    private void redirect (HttpServletResponse resp) throws IOException {
        // /response-header로 요청이 오면 아래 경로로 리다이렉트
        // 여기서 상태 코드는 생략해도 알아서 302코드가 반환된다.
        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.sendRedirect("/requestparam/post-form.html");
    }
}
