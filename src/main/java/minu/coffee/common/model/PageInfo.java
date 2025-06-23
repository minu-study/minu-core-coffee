package minu.coffee.common.model;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

@AllArgsConstructor
public class PageInfo {
    private int requestPageNumber;  // 요청된 페이지 번호
    private int requestSize;        // 요청된 페이지 당 Data 수
    private int nowPageNumber;      // 현재 페이지 번호
    private int totalPageSize;      // 전체 페이지 수
    private Long totalDataSize;     // 전체 Data 수

    public static PageInfo convertResponse(Page<?> result) {
        return new PageInfo(result.getPageable().getPageNumber()+1,result.getPageable().getPageSize(), result.getNumber()+1, result.getTotalPages(), result.getTotalElements());
    }
}

