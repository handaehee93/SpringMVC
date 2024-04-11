package mvc.basic.web.frontcontroller.v3.controller;

import mvc.basic.domain.member.Member;
import mvc.basic.domain.member.MemberRepository;
import mvc.basic.web.frontcontroller.ModelView;
import mvc.basic.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // frontController에서 파라미터 key와 value를 map으로 전달 받아 저장한다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 컨트롤러에서는 view 경로와 데이터가 담긴 modelView 객체를 반환
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member",member);
        return modelView;
    }
}
