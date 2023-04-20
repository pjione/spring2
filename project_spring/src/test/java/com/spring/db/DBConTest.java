package com.spring.db;


import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class DBConTest {

    @Setter(onMethod_ = @Autowired)
    private DataSource ds;
    @Setter(onMethod_ = @Autowired)
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void myBatisTest(){
        SqlSession sqlsession = sqlSessionFactory.openSession();
        Connection conn = sqlsession.getConnection();
        log.warn("sqlSession : " + sqlsession);
        log.debug("conn : " + conn);
        assert(sqlsession!=null && conn !=null);
    }
    @Test
    public void dbConnectionTest() {
        try (Connection conn = ds.getConnection()){
            log.info("conn : " + conn);
            assert(conn!=null);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }




}
