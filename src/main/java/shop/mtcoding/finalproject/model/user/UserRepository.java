package shop.mtcoding.finalproject.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.finalproject.dto.user.RequestDto.JoinReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.LoginReqDto;

@Mapper
public interface UserRepository {
    public User login(LoginReqDto loginReqDto); 
   
    public List<User> findAll(); 

    public User findById(int id);

    public int insert(JoinReqDto joinReqDto);

    public int delete(int id);

    public int update(User user);

    public User findByUsername(String userename);

}
