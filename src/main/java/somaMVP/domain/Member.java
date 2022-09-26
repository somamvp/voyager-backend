package somaMVP.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키
    private String userId; // 유저 진짜 아이디
    private String password; // 추후 암호화 필요
    private String userName;
    private String phoneNumber;
    private String disabledNumber; // 장애인 등록 번호
    private String email;
    private String address;
    @Builder
    public Member(String userId, String password, String userName, String phoneNumber, String disabledNumber, String email, String address) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.disabledNumber = disabledNumber;
        this.email = email;
        this.address = address;
    }
}
