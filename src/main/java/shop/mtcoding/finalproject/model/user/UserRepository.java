package shop.mtcoding.finalproject.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.finalproject.dto.user.RequestDto.JoinReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.LoginReqDto;

@Mapper
public interface UserRepository {
    public User login(LoginReqDto loginReqDto); 
   
    public List<User> findAll(); 

    public User findById(int id);

    public int insert(JoinReqDto joinReqDto);

    public int delete(int id);

    public int update(@Param("password") String password, @Param("email") String email, @Param("userId") int userId);

    public User findByUsername(String userename);
}
