package mvc.basic.web.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.domain.member.MemberRepository;
import java.io.IOException;
import java.io.PrintWriter;


// 회원 가입 페이지 HTML을 제공하는 서블릿을 만든다.
@WebServlet(name="memberFormServlet" , urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    // 이전에 MemberRepository를 싱글톤으로 만들었기 때문에 new 키워드로 인스턴스를 새로 생성하는 것이 아니라
    // 메서드를 통해 생성해 둔 인스턴스를 가져 와야 한다.
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // html을 전달할 것이기 때문에 content-type과 인코딩 방식을 지정
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");

    // response.getWritrer()를 하면 writer를 가져올 수 있다.
    // 이제 /servlet/member/new-form으로 접속하면 아래 html이 화면에 그려질 것이다.
            PrintWriter w = response.getWriter();
            w.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    " <meta charset=\"UTF-8\">\n" +
                    " <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                    " username: <input type=\"text\" name=\"username\" />\n" +
                    " age: <input type=\"text\" name=\"age\" />\n" +
                    " <button type=\"submit\">전송</button>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>\n");
        }
}