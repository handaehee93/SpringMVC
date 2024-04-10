package mvc.basic.web.frontcontroller.v3.controller;

import mvc.basic.domain.member.Member;
import mvc.basic.domain.member.MemberRepository;
import mvc.basic.web.frontcontroller.ModelView;
import mvc.basic.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
