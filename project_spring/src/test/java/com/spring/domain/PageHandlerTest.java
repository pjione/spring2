package com.spring.domain;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class PageHandlerTest {
    @Test
    public void test(){
        PageHandler pageHandler = new PageHandler(250,22);
        pageHandler.print();
        log.info(pageHandler);
        assert (pageHandler.getBeginPage() == 21);
        assertTrue(pageHandler.getEndPage() == 25);
    }
    @Test
    public void test2(){
        PageHandler pageHandler = new PageHandler(255,25);
        pageHandler.print();
        log.info(pageHandler);
        assert (pageHandler.getBeginPage() == 21);
        assertTrue(pageHandler.getEndPage() == 26);
    }
}