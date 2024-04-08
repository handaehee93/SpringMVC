package mvc.basic.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {
    // 테스트할 리포지토리를 가져온다.
    // 싱글톤이기 때문에 new 키워드가 아니라 getInstance로 가져온다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 매 테스트가 끝나면 리포지토리 초기화 한다.
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void saveTest () {
        // 멤버 객체를 만들고
        Member member = new Member("hdh", 30);
        // 리포지토리에 저장
        Member savedMember = memberRepository.save(member);

        // 아이디로 멤버를 찾아, 저장된 멤버와 일치하는지 확인
        Member findMember = memberRepository.findById(savedMember.getId());

        Assertions.assertThat(savedMember).isEqualTo(findMember);
    }

     @Test
    void findAllTest () {
        Member member1 = new Member("hdh1", 30);
        Member member2 = new Member("hdh2", 31);
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

         List<Member> findAllMember = memberRepository.findAll();

         Assertions.assertThat(findAllMember.size()).isEqualTo(2);
         Assertions .assertThat(findAllMember).contains(member1, member2);
     }
}
