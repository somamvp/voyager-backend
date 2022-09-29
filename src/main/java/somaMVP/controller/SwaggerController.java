package somaMVP.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import somaMVP.domain.member.Member;
import somaMVP.domain.member.MemberDto;
import somaMVP.domain.member.MemberRepository;
import somaMVP.response.UuidResponse;

@Tag(name = "swagger", description = "게시물 API")
@RequestMapping("/api/swaggers")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SwaggerController {
    public final MemberRepository memberRepository;
//    @Operation(summary = "test hello", description = "hello api example")
//    @ApiResponse(responseCode = "200", description = "OK !!")
//    @ApiResponse(responseCode = "400", description = "BAD REQUEST !!")
//    @ApiResponse(responseCode = "404", description = "NOT FOUND !!")
    //
    @PostMapping("/http")
    public ResponseEntity<String> httpTest(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }
    @PostMapping("/uuid")
    public UuidResponse fluxTest(@RequestBody UuidResponse uuidResponse) {
        log.info("UuidResponse: {}", uuidResponse.toString());
        return uuidResponse;
    }
    @PostMapping("/sign")
    public Member sign(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Long savedId = memberRepository.save(member);
        return memberRepository.findById(savedId);
    }
}