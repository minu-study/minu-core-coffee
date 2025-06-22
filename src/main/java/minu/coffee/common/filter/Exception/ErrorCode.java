package minu.coffee.common.filter.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    AUTH001("AUTH001", "인증에 실패했습니다."),

    DB001("DB001", "존재하지 않는 데이터입니다."),

    ;


    private final String code;
    private final String msg;

}
