package somaMVP.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter // 추후 삭제
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Member {
    @Id @GeneratedValue
    private Long id; // 기본 키
    private String userId; // 유저 진짜 아이디
    private String password; // 추후 암호화 필요
    private String userName;
    private String phoneNumber;
    private String disabledNumber; // 장애인 등록 번호
    private String email;
    private String address;
}
