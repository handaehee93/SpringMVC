package mvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyServlet", urlPatterns = "/request-body-string")
public class RequestBodyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requstBody(req);
    }

    private static void requstBody(HttpServletRequest request) throws IOException {
        // getInputStream을 통해 Content-Body의 내용을 바이트 코드로 얻을 수 있다.
        ServletInputStream inputStream = request.getInputStream();
        System.out.println("inputStream +" + inputStream);

        // 스프링이 제공하는 StreamUtils에 inputStream과 인코딩 방식을 넣어주면 바디 데이터를 꺼낼 수 있다.
        // inputStream 의 경우 바이트 코드이기 때문에 string으로 변환 해 줘야 한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
    }
}
