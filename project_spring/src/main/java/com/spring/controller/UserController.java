package com.spring.controller;

import com.spring.domain.User;
import com.spring.domain.UserSignup;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @Qualifier("userSignupValidator") UserSignup userSignup, BindingResult result, Model model){
        User user =  new User(userSignup.getId(), userSignup.getPwd(), userSignup.getName(),userSignup.getEmail(),
                userSignup.getBirth(),userSignup.getSns(),userSignup.getRegDate());
        if(result.hasErrors()){
            model.addAttribute("error", result.getAllErrors());
            return "/join";
        }else if(userService.userCheck(userSignup.getId())!=null){
            model.addAttribute("msg", "이미 존재하는 아이디입니다.");
            return"/join";
        }else if(userService.joinUser(user)){
            return "redirect:/login";
        }else{
            model.addAttribute("msg", "알 수 없는 에러입니다. 관리자에게 문의하세요.");
            return"/join";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String id, String pwd, boolean rememberId, String toURL, Model model, HttpSession session, HttpServletResponse response){
        User user = userService.userCheck(id);
        if(user == null || !user.getPwd().equals(pwd)){
            model.addAttribute("msg","아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/login";
        } else{
            session.setAttribute("id", id);
            if(rememberId){
                Cookie cookie = new Cookie("id", id);
                response.addCookie(cookie);
            }else{
                Cookie cookie = new Cookie("id", id);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            toURL = toURL == null || "".equals(toURL) ? "/" : toURL;
            return "redirect:" + toURL;
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
