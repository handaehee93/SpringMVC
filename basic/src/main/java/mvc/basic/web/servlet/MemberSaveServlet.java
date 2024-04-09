package mvc.basic.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.domain.member.Member;
import mvc.basic.domain.member.MemberRepository;
import java.io.IOException;
import java.io.PrintWriter;

// html form의 post 요청을 받는 서블릿
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // content-body에 담긴 데이터도 쿼리 파라미터 작성 형식과 같기 때문에 전송된 form형식의
        // 데이터를 getParameter를 통해 가져온다.
        // request.getParameter의 응답 결과는 항상 문자이기 때문에 숫자가 필요하면 변환해 줘야 한다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 전송된 form 데이터를 바탕으로 member객체를 만들고 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // Member 객체를 사용해서 화면용 html을 동적으로 만들어서 응답.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }

}
