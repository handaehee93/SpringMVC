package mvc.basic.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository {
    // 간단한 리포지토리 생성
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 내부에 인스턴스 생성 후 생성자를 private로 두어 더이상의 인스턴스 생성을 막고 getter함수로 해당 인스턴스를 가져오게 하여
    // 싱글톤으로 구현.
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {}

    // Member 클래스에 롬복 getter, setter가 세팅되어 있으므로
    // setId로 아이디를 member 객체에 넣고
    // 해당 아이디를 다시 꺼내 key로 하고, value를 member로 하여 storedp 넣는다.
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    // store에 id가 key로 저장되어 있으므로 해당 아이디를 넣어 value인 member 객체를 반환한다.
    public Member findById(Long id) {
        return store.get(id);
    }

    // store에 있는 모든 value를 ArrayList에 담아 리턴
    public List<Member> findAll () {
        return new ArrayList<>(store.values());
    }

    // store 초기화
    public void clearStore () {
        store.clear();
    }
}
