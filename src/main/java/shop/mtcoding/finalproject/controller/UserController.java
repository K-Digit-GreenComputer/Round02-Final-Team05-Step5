package shop.mtcoding.finalproject.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.finalproject.dto.user.RequestDto.JoinReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.LoginReqDto;
import shop.mtcoding.finalproject.handler.ex.CustomException;
import shop.mtcoding.finalproject.model.user.User;
import shop.mtcoding.finalproject.model.user.UserRepository;

@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

   
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        User principal = userRepository.login(loginReqDto);
        
        session.setAttribute("principal", principal);
        if (principal!=null) {
            return "redirect:/";
        } else {
            return "redirect:/loginForm";
        }
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }
        userRepository.insert(joinReqDto);
            return "redirect:/loginForm";
        
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
