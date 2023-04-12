package shop.mtcoding.finalproject.model.orders;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.finalproject.dto.orders.BuyListDto;

@Mapper
public interface OrdersRepository {
    public List<BuyListDto> findAll(int userId); 

    public Orders findById(int ordersId);

    public int insert(Orders orders);

    public int delete(int ordersId);

    public int update(@Param("ordersName") String ordersName, @Param("ordersQty") int ordersQty);

}
