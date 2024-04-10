package mvc.basic.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.domain.member.Member;
import mvc.basic.domain.member.MemberRepository;
import mvc.basic.web.frontcontroller.MyView;
import mvc.basic.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);

        memberRepository.save(member);

        // 모델에 데이터 저장
        request.setAttribute("member",member );

        // 만약 아래 jsp에서 member 값이 필요하면 모델에서 꺼내서 사용하면 됨
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
