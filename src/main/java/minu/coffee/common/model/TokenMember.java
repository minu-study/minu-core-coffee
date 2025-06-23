package minu.coffee.common.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TokenMember extends User {

    private final TokenInfo tokenInfo;

    public TokenMember(TokenInfo tokenInfo) {
        super(
                tokenInfo.getMemberId(),
                tokenInfo.getMemberPassword(),
                makeGrantedAuthorities(tokenInfo)
        );
        this.tokenInfo = tokenInfo;
    }

    private static List<GrantedAuthority> makeGrantedAuthorities(TokenInfo tokenInfo) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 기본 권한
        authorities.add(new SimpleGrantedAuthority("USER"));

        // 관리자 권한 추가
        if (tokenInfo.getIsAdmin() != null && tokenInfo.getIsAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
    }
}
