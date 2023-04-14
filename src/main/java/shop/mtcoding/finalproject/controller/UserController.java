package shop.mtcoding.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.finalproject.dto.ResponseDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.JoinReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.LoginReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.SameUserReqDto;
import shop.mtcoding.finalproject.dto.user.RequestDto.UserUpdateReqDto;
import shop.mtcoding.finalproject.handler.ex.CustomApiException;
import shop.mtcoding.finalproject.handler.ex.CustomException;
import shop.mtcoding.finalproject.model.user.User;
import shop.mtcoding.finalproject.model.user.UserRepository;

@RequiredArgsConstructor
@Controller
public class UserController {
    
    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/user/usernameSameCheck")
    public @ResponseBody ResponseDto<?> check(String username, SameUserReqDto sameUserReqDto) {
        if (username == null || username.isEmpty()) {
            return new ResponseDto<>(-1, "유저네임이 입력되지 않았습니다.", null);
        }
        User sameUsername = userRepository.findByUsername(username);
        if (sameUsername != null) {
            return new ResponseDto<>(1, "동일한 유저네임이 존재합니다.", false);
        } else {
            return new ResponseDto<>(1, "해당 유저네임으로 등록이 가능합니다.", true);
        }
    }

   
    @DeleteMapping("/user/delete")
    public @ResponseBody ResponseDto<?> profileDelete() {
        User principal= (User)session.getAttribute("principal");
        userRepository.delete(principal.getId());
        session.invalidate();
        return new ResponseDto<>(1, "회원탈퇴성공", null);
    }
   
    @PostMapping("/user/update")
    public @ResponseBody ResponseDto<?> profileUpdate(@RequestBody UserUpdateReqDto userUpdateReqDto) {
        System.out.println("디버깅");
        System.out.println("디버깅 "+userUpdateReqDto.getPassword());
        System.out.println("디버깅 "+userUpdateReqDto.getEmail());
        User principal= (User)session.getAttribute("principal");


        userRepository.update(userUpdateReqDto.toModel(principal.getId()));
        session.invalidate();
         return new ResponseDto<>(1, "회원수정완료", null);
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        User principal= (User)session.getAttribute("principal");
        User userPs = userRepository.findById(principal.getId());

        model.addAttribute("userProfile", userPs);
        
        return "user/userUpdateForm";
    }

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
        // System.out.println(principal.getRole());
        
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
    public @ResponseBody ResponseDto<?> join(@RequestBody JoinReqDto joinReqDto) {
        System.out.println(joinReqDto.getEmail());
        System.out.println(joinReqDto.getUsername());
        System.out.println(joinReqDto.getPassword());
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomApiException("username을 작성해주세요");
            // return new ResponseDto<>(-1, "username이 입력되지 않았습니다.", null);
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomApiException("password를 작성해주세요");
            // return new ResponseDto<>(-1, "password가 입력되지 않았습니다.", null);
        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomApiException("email을 작성해주세요");
            // return new ResponseDto<>(-1, "email이 입력되지 않았습니다.", null);
        }
        if (joinReqDto.getUsername().isBlank()) {
            return new ResponseDto<>(-1, "회원가입 실패", null);
        }
        if (joinReqDto.getEmail().isBlank()) {
            return new ResponseDto<>(-1, "회원가입 실패", null);
        }
        if (joinReqDto.getPassword().isBlank()) {
            return new ResponseDto<>(-1, "회원가입 실패", null);
        }
        userRepository.insert(joinReqDto);
            return new ResponseDto<>(1, "회원가입 성공", null);
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/userListForm")
    public String userListForm(Model model) {
        List<User> getUserList = userRepository.findAll();
        System.out.println(getUserList.get(0).getUsername());
        model.addAttribute("userlist", getUserList);

        User principal = (User)session.getAttribute("principal");
        if (principal.getRole().equals("admin")) {
            return "user/userList";
        } 
        return "redirect:/";
    }

    @PostMapping("/userListForm/{userId}/delete")
    public String deleteUser(@PathVariable Integer userId) {
        userRepository.delete(userId);
        return "redirect:/userListForm";
    }
}
