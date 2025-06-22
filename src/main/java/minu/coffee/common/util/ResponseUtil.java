package minu.coffee.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import minu.coffee.common.model.ApiResponse;
import minu.coffee.common.model.TokenInfo;
import minu.coffee.common.model.TokenMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;

@Slf4j
public class ResponseUtil {

    public static ResponseEntity<ApiResponse> ConvertResponse() {
        return ResponseEntity.ok(ApiResponse.builder().build());
    }

    public static ResponseEntity<ApiResponse> ConvertResponse(Object result) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        HashMap<String, Object> convert = objectMapper.convertValue(result, HashMap.class);
        return ResponseEntity.ok(
                ApiResponse.builder().data(convert).build());
    }


    public static TokenInfo getTokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof TokenMember) {
            return ((TokenMember) authentication.getPrincipal()).getTokenInfo();
        }

        throw new IllegalStateException("Invalid authentication type");
    }

}
