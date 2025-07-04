package minu.coffee.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import minu.coffee.common.model.ApiResponse;
import minu.coffee.common.model.TokenInfo;
import minu.coffee.common.model.TokenMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        return servletRequestAttributes.getRequest();
    }


    public static String getClientIP() {
        try {
            HttpServletRequest request = getHttpServletRequest();
            return getClientIP(request);
        } catch (Exception e) {
            log.error("getClientIP_HttpServletRequest access error : {}", e.getMessage());
            return null;
        }
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static ResponseEntity<ApiResponse> convertResponse() {
        return ResponseEntity.ok(ApiResponse.builder().build());
    }

    public static ResponseEntity<ApiResponse> convertResponse(Object result) {
        HashMap<String, Object> convert = OBJECT_MAPPER.convertValue(result, HashMap.class);
        return ResponseEntity.ok(
                ApiResponse.builder().data(convert).build());
    }

    public static MultiValueMap<String, String> convertDtoToMap(ObjectMapper objectMapper, Object dto) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {
            });
            params.setAll(map);

            return params;
        } catch (Exception e) {
            log.error("Url Parameter 변환중 오류가 발생했습니다. requestDto={}", dto, e);
            throw new IllegalStateException("Url Parameter 변환중 오류가 발생했습니다.");
        }
    }


    public static TokenInfo getTokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof TokenMember) {
            return ((TokenMember) authentication.getPrincipal()).getTokenInfo();
        }

        throw new IllegalStateException("Invalid authentication type");
    }

}
