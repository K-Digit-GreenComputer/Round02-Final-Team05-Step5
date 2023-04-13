package shop.mtcoding.finalproject.dto.product;

import lombok.Getter;
import lombok.Setter;

public class ProductReqDto {

    @Setter
    @Getter
    public static class productSameReqDto {
        private String productname;
    }
}
