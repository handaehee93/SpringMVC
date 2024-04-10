package mvc.basic.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.basic.domain.member.Member;
import mvc.basic.domain.member.MemberRepository;
import mvc.basic.web.frontcontroller.MyView;
import mvc.basic.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        // 모델에 데이터 저장
        request.setAttribute("members", members);

        // 만약 아래 jsp에서 member 값이 필요하면 모델에서 꺼내서 사용하면 됨
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
