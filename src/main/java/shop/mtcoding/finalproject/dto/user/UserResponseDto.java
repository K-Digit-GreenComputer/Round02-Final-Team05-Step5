package shop.mtcoding.finalproject.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserResponseDto {
 
    @Setter
    @Getter
    public class UserUpdateRespDto {
        private String password;
        private String email;
    }
}
