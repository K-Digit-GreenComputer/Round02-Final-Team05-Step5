package shop.mtcoding.finalproject.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.finalproject.model.user.User;

public class RequestDto {
    
    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
        private String role;
    }
    
    @Setter
    @Getter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String email;
    }

    @Setter
    @Getter
    public class UserUpdateReqDto {
        private String password;
        private String email;
    }
    @Setter
    @Getter
    public static class SameUserReqDto {
        private String username;
    }
    
}
