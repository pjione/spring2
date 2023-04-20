package com.spring.service;

import com.spring.domain.User;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class UserServiceTests {
    @Setter(onMethod_ = @Autowired)
    private UserService userService;
    @Test
    public void insertUserTest(){
        log.info("회원가입 성공.");
        assert (userService.joinUser(new User("zzzzl","1234","kim","asdf@asdf.com", new Date(),"fb",new Date())));
    }
    @Test
    public void selectUserTest(){
        User user = userService.userCheck("zl");
        log.info("user : " + user);
        if(user == null){
            log.info("아이디가 존재하지 않습니다.");
        } else if(!user.getPwd().equals("1234")){
            log.info("비밀번호가 일치하지 않습니다.");
        } else{
            log.info("로그인 성공");
        }
    }
    @Test
    public void selectUserAllTest(){
        
    }

}
