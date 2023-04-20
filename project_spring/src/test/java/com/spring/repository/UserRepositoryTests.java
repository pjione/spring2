package com.spring.repository;

import com.spring.domain.User;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class UserRepositoryTests {
    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;
    @Test
    public void insertUserTest(){
        log.info("회원가입 성공.");
        assert (userRepository.insertUser(new User("qqzq","1234","kim","asdf@asdf.com", new Date(),"fb",new Date())) == 1);
    }
    @Test
    public void selectUserTest(){
        User user = userRepository.selectUser("qqqq");
        if(user == null){
            log.warn("아이디가 존재하지 않습니다.");
        } else if(!user.getPwd().equals("123")){
            log.warn("비밀번호가 일치하지 않습니다.");
        } else{
            log.warn("로그인 성공");
        }
    }
    @Test
    public void selectUserAllTest(){
        
    }

}
