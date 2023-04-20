package com.spring.controller;

import com.spring.domain.User;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
                        "file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
@WebAppConfiguration
public class UserControllerTests {
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //  mock이 참조할 웹어플리케이션설정을 부여
        log.info("ctx : " + ctx);
        log.info("mock : " + mockMvc);
    }

    @Test
    public void userJoinGetTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders
                        .get("/join"))
                        .andReturn());
    }
    @Test
    public void userJoinPostTest() throws Exception {
        User user = new User();
        user.setId("test1234");
        user.setPwd("password");
        user.setName("test name");
        user.setEmail("test@spring.com");
        user.setBirth(new Date());
        user.setSns("test sns");
        user.setRegDate(new Date());
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/join")
                        .param("id", user.getId())
                        .param("pwd", user.getPwd())
                        .param("name", user.getName())
                        .param("email", user.getEmail())
                        .param("birth", user.getBirth().toString())
                        .param("sns", user.getSns())
                        .param("reg_date", user.getRegDate().toString()))
                .andReturn());
    }

}
