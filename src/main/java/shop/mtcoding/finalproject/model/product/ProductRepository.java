package shop.mtcoding.finalproject.model.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.finalproject.dto.orders.BuyDto;
import shop.mtcoding.finalproject.dto.product.ProductReqDto.ProductAddReqDto;

@Mapper 
public interface ProductRepository {
    public List<Product> findAll(); 

    public Product findById(int id);

    public int insert(ProductAddReqDto productAddReqDto);

    public int delete(int id);

    public int update(@Param("id") int id, @Param("name") String name, @Param("price") int price,
            @Param("qty") int qty);


    public Product findByName(String productname);

    public int productQtyUpdate(BuyDto buyDto);
    
    public int canclePurchase(BuyDto buyDto);
}
