package minu.coffee.common.filter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    AUTH001("AUTH001", "인증에 실패했습니다."),
    AUTH002("AUTH002", "이미 존재하는 계정 정보 입니다."),

    DB001("DB001", "존재하지 않는 데이터입니다."),

    ;


    private final String code;
    private final String msg;

}
