package shop.mtcoding.finalproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdateReqDto {
        private String email;
        private String password;

        public User toModel(int principalId) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setId(principalId);
            return user;
        }

    }
    @Setter
    @Getter
    public static class SameUserReqDto {
        private String username;
    }
    
}
