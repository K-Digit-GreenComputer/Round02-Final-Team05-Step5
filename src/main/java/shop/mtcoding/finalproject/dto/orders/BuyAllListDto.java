package shop.mtcoding.finalproject.dto.orders;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyAllListDto {

    private String userName;
    private String ordersName;
    private String ordersPrice;
    private Integer ordersQty;
    private Timestamp createdAt;
}
