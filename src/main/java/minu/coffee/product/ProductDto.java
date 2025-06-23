package minu.coffee.product;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class ProductDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductSummaryView {

        private Long id;
        private String categoryCode;
        private String categoryName;
        private String subCategoryCode;
        private String subCategoryName;
        private String productCode;
        private String productName;
        private Integer price;
        private String paymentType;

    }

    public static class GetProducts {

        @Getter
        @Setter
        public static class Request {
            @NotNull
            private Long shopId;
        }

        @Getter
        @Builder
        public static class Response {
            private List<ProductSummaryView> list;
        }

    }

}
