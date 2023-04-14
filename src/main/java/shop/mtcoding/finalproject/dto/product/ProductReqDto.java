package shop.mtcoding.finalproject.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductReqDto {

    @Setter
    @Getter
    public static class productSameReqDto {
        private String productname;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductAddReqDto {
        private String productname;
        private Integer price;
        private Integer qty;
    }
}
