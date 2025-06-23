package minu.coffee.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import minu.coffee.common.model.ApiResponse;
import minu.coffee.common.model.TokenInfo;
import minu.coffee.common.model.TokenMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;

@Slf4j
public class CommonUtil {

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
