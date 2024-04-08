package mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requsetBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // 먼저

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        contentBodyJson(req);
    }

    private static void contentBodyJson(HttpServletRequest request) throws IOException {
        // 먼저 json 데이터를 변환하기 위해 Jackson 라이브러의 objectMapper 라는 인스턴스를 가져온다.
        ObjectMapper objectMapper = new ObjectMapper();

        // 일단 Text를 받을 때 처럼 아래와 같이 코드를 입력 후 postman을 통해 {"username" : "hdh"} 라는
        // Json 형식의 데이터를 전달하면 {"username" : "hdh"} 이게 그대로 출력이 된다.
        // 그 이유는 Json 데이터도 그냥 String이기 때문이다.
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        // 그럼 이제 전달받은 json데이터를 ConverJsonDataToObject.class의 타입으로 변환해 보자.
        // 이때 스프링 부트에서 기본으로 제공하는 Jackson이라는 라이브러리를 사용해야 한다.
        // 클래스 상단에 잭슨 라이브러이의 ObjectMapper의 인스턴스를 생성하자.
        // 그러면 readValue를 통해 json데이터를 읽을 수 있게 된다. 여기에 전달 받은 Json 데이터와 변환할 클래스 타입을 입력한다.
        // 객체로 변환 후 Getter 함수로 값을 꺼내온다.

        ConverJsonDataToObject ConverJsonDataToObject = objectMapper.readValue(messageBody, ConverJsonDataToObject.class);
        System.out.println("ConverJsonDataToObject.username = " + ConverJsonDataToObject.getUsername());
        System.out.println("ConverJsonDataToObject.age = " + ConverJsonDataToObject.getAge());
    }
}
