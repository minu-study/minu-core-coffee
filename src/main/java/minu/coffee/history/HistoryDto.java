package minu.coffee.history;

import lombok.*;

public class HistoryDto {

    public static class SetPaymentHistory {

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @ToString
        public static class Request {
            private String paymentType;
            private Long productId;
            private Integer price;
            private Integer discount;
            private Integer quantity;
            private Integer paymentPrice;
            private Integer usePoint;
            private Integer pointBalanceAfter;
        }
    }

    public static class SetPointHistory {

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @ToString
        public static class Request {
            private Long paymentHistoryId;
            private String transactionType;
            private Integer transactionPoint;
            private Integer pointBalanceAfter;

        }
    }

}
