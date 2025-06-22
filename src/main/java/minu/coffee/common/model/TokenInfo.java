package minu.coffee.common.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenInfo {

    private Long id; // 사용자 ID
    private String lastName;
    private String firstName;
    private String memberId;
    private String memberPassword;
    private String phoneNumber;
    private Long shopInfoId; // 매장 ID

    private Boolean isAdmin;

}
