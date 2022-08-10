package somaMVP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import somaMVP.domain.Member;
import somaMVP.domain.MemberDto;
import somaMVP.repository.MemberRepository;
import somaMVP.response.ImageResponse;
import somaMVP.response.UuidResponse;

@Tag(name = "posts", description = "게시물 API")
@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SwaggerController {
    public final MemberRepository memberRepository;
    @Operation(summary = "test hello", description = "hello api example")
    @ApiResponse(responseCode = "200", description = "OK !!")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST !!")
    @ApiResponse(responseCode = "404", description = "NOT FOUND !!")
    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    //
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }
    @PostMapping("/test")
    public UuidResponse test(@RequestBody UuidResponse uuidResponse) {
        log.info("uuid: {}, name: {} ", uuidResponse.getUuid(), uuidResponse.getName());
        return uuidResponse;
    }
    @PostMapping("/sign")
    public Member sign(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity();
        Long savedId = memberRepository.save(member);
        return memberRepository.findById(savedId);
    }
}