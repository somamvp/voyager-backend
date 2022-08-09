package somaMVP.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

}
