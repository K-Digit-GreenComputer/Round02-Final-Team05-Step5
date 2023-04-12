package shop.mtcoding.finalproject.model.orders;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {
    private Integer ordersId;
    private String ordersName;
    private String ordersPrice;
    private Integer ordersQty;
    private Integer productId;
    private Integer userId;
    private Timestamp createdAt;

}
