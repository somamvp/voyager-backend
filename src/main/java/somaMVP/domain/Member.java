package somaMVP.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@ToString
@Entity
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Member {
    @Id @GeneratedValue
    private Long id; // 기본 키
    private String userId; // 유저 진짜 아이디
    private String password; // 추후 암호화 필요
    private String userName;
    private String phoneNumber;
    private Long disabledNumber; // 장애인 등록 번호
    private String email;
    private String address;
}
