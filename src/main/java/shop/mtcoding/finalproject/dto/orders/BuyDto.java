package shop.mtcoding.finalproject.dto.orders;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.finalproject.model.orders.Orders;

    
    @Getter
    @Setter
    public class BuyDto{
        private Integer ordersId;
        private String ordersName;
        private String ordersPrice;
        private Integer ordersQty;
        private Integer productId;

        public Orders toModel(int principalId) {
            Orders orders = new Orders();
            orders.setOrdersId(ordersId);
            orders.setOrdersName(ordersName);
            orders.setOrdersQty(ordersQty);
            orders.setProductId(productId);
            orders.setOrdersPrice(ordersPrice);
            orders.setUserId(principalId);
            return orders;
        }
    }
