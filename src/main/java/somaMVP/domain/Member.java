package somaMVP.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@ToString
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String phoneNumber;
    private Long DisabledNumber;
    private String email;
    private String address;
}
