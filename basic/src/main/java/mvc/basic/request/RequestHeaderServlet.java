package mvc.basic.request;

import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);

    }

    // Http 요청 메시지 Start Line 출력 메서드
    private static void printStartLine(HttpServletRequest req) {
        System.out.println("--- http요청 메세지 start라인 정보를 출력 시작---");
        System.out.println("메서드 정보 = " + req.getMethod());
        System.out.println("프로토콜 정보 = " + req.getProtocol());
        System.out.println("스키마 정보 = " + req.getScheme());
        System.out.println("요청 url 정보 = " + req.getRequestURL());
        System.out.println("요청 uri 정보 = " + req.getRequestURI());
        System.out.println("쿼리 파라미터 정보 = " + req.getQueryString());
        System.out.println("https 사용 유무 = " + req.isSecure());
        System.out.println("--- http요청 메세지 start라인 정보를 출력 끝 ---");


    }

    //Header 모든 정보 출력
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        /*
         */
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": "
                        + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    // Http 요청 메시지 Header 정보 조회 메서드
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 조회 시작 ---");
        System.out.println("[Host 조회]");
        System.out.println("Host 헤더 = " + request.getServerName());
        System.out.println("Host 헤더 포트 = " + request.getServerPort());
        System.out.println("[Accept-Language 조회]");
        // 브라우저는 요청을 보낼때 Accept-Language에 원하는 언어의 우선순위를 입력해서 보내는데
        // getLoacales()를 사용하면 해당 정보를 전부 가져 올 수 있다.
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("언어 우선순위 정보 전체" +
                        locale));
        // 그 우선순위 중 가장 첫번째 것을 꺼낼려면 getLoacale()을 사용하면 된다.
        System.out.println("우선순위 언어 중 첫번째 언어 = " + request.getLocale());
        System.out.println("[cookie 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println("[Content  조회]");
        System.out.println("ContentType 조회 = " + request.getContentType());
        System.out.println("ContentLength 조회 = " + request.getContentLength());
        System.out.println("인코딩 방식 조회 = " +  request.getCharacterEncoding());
        System.out.println("--- Header 조회 끝 ---");

        // 아래와 같이 getHeader에 원하는 key 값을 넣어 꺼낼 수도 있다.
        System.out.println(request.getHeader("host"));
    }
}
