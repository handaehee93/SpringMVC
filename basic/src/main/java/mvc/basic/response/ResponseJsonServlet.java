package mvc.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.request.ConverJsonDataToObject;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        //Content-Type: application/json 설정
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        // 응답할 데이터 객체 생성
        ConvertObjectToJsonData data = new ConvertObjectToJsonData();
        data.setUsername("hdh");
        data.setAge(30);

        // 위에서 생성한 데이터 객체를 {"username":"kim","age":20} 형태의 json 형식으로 변환
        //  객체를 문자, 즉 Json (json도 그냥 문자다) 으로 바꾸는 것
        String result = objectMapper.writeValueAsString(data);
        response.getWriter().write(result);
    }
}
