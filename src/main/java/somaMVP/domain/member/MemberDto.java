package somaMVP.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id; // 기본 키
    private String userId; // 유저 진짜 아이디
    @JsonIgnore
    @NotBlank
    private String password; // 추후 암호화 필요
    @NotBlank
    @Size(max = 64)
    private String userName;
    private String phoneNumber;
    @NotBlank
    private String disabledNumber; // 장애인 등록 번호
    private String email;
    private String address;

    public Member toEntity() {
        return new Member(id, userId, password, userName, phoneNumber, disabledNumber, email, address);
    }
//    추후 구현 예정
//    public Member toEntityWithPasswordEncode(PasswordEncoder bCryptPasswordEncoder) {
//        return new Member(id, userId, bCryptPasswordEncoder.encode(password), userName, phoneNumber, disabledNumber, email, address);
//    }
}
