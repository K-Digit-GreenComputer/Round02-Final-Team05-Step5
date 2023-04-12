package shop.mtcoding.finalproject.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuyListDto {
    private Integer ordersId;
    private String ordersName;
    private String ordersPrice;
    private Integer ordersQty;
}
