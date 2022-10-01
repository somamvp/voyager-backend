package somaMVP.domain.member;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "member", description = "유저 정보 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberRestController {
    public final MemberRepository memberRepository;

    @PostMapping("/login")
    public Member sign(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Long savedId = memberRepository.save(member);
        return memberRepository.findById(savedId);
    }
}
