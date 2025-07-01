package minu.coffee.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderDto {

    public static class ProductOrder {

        @Getter
        @Setter
        @Builder
        @ToString
        public static class Request {
            private Long productId;
            @Builder.Default
            private Integer quantity = 1;
            private String paymentType; // e.g., "CASH", "CARD", "POINT"
            private Integer discount; // Optional discount percent
            private Integer point;
            private Integer cash;
        }

    }

}
