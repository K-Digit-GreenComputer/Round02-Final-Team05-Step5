package shop.mtcoding.finalproject.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.finalproject.dto.orders.BuyDto;

@Mapper 
public interface ProductRepository {
    public List<Product> findAll(); 

    public Product findById(int id);

    public int insert(@Param("name") String name, @Param("price") int price, @Param("qty") int qty);

    public int delete(@Param("id") int id);

    public int update(@Param("id") int id, @Param("name") String name, @Param("price") int price,
            @Param("qty") int qty);


    public Product findByName(String productname);

    public int productQtyUpdate(BuyDto buyDto);
}
