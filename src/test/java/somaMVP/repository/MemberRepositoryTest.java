package somaMVP.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import somaMVP.domain.Member;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Transactional
    @Test
    public void testMember() {
        Member member = new Member();
        member.setUsername("test");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedId);
        assertThat(findMember.getId()).isEqualTo(savedId);
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }
}