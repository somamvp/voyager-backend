package somaMVP.repository;

//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
//import somaMVP.domain.member.Member;
//import somaMVP.domain.member.MemberRepository;
//
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@AutoConfigureRestDocs // rest docs 자동 설정
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    protected RestDocumentationResultHandler restDocs;
//    @Transactional
//    @Test
//    void testMember() {
//        Member member = new Member();
//        //member.setUserName("test");
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.findById(savedId);
//        assertThat(findMember.getId()).isEqualTo(savedId);
//        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
//        assertThat(findMember).isEqualTo(member);
//    }
//    @Test
//    public void BaseTimeEntity_등록 () {
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        memberRepository.save(Member.builder()
//                        .userId("jojoldu")
//                        .password("1234")
//                        .userName("jojoldu")
//                        .address("서울시 서초구 서초동")
//                        .disabledNumber("010-1234-5678")
//                        .email("jojoldu@gmail.com")
//                        .phoneNumber("010-1234-5678")
//                        .build());
//        //when
//        List<Member> postsList = memberRepository.findAll();
//
//        //then
//        Member members = postsList.get(0);
//        assertTrue(members.getCreatedDate().isAfter(now));
//        assertTrue(members.getModifiedDate().isAfter(now));
//    }
//}