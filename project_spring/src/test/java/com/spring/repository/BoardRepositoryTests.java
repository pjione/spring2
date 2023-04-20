package com.spring.repository;

import com.spring.domain.Board;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardRepositoryTests {
    @Setter(onMethod_ = @Autowired)
    private BoardRepository boardRepository;
    @Test
    public void insertTestData() throws Exception {
        boardRepository.deleteAll();
        for (int i=1; i<=225; i++){
            Board board = new Board("title" + i, "no content", "asdf");
            boardRepository.insert(board);
        }

    }
    @Test
    public void testSelectView() throws Exception {
        log.info("boardRepository : " + boardRepository);
        Board board = boardRepository.selectView(34);
        log.info(board);
        assert (board.getBno().equals(34));
        assert(boardRepository!=null);
    }
    @Test
    public void countTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.count()==1);

        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.count()==2);
    }

    @Test
    public void deleteAllTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.deleteAll()==1);
        assertTrue(boardRepository.count()==0);

        board= new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.deleteAll()==2);
        assertTrue(boardRepository.count()==0);
    }

    @Test
    public void deleteTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        Integer bno = boardRepository.selectAll().get(0).getBno();
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", board.getWriter());
        assertTrue(boardRepository.delete(map)==1);
        assertTrue(boardRepository.count()==0);

        assertTrue(boardRepository.insert(board)==1);
        bno = boardRepository.selectAll().get(0).getBno();
        map.put("bno", bno);
        map.put("writer", "222");
        assertTrue(boardRepository.delete(map)==0);
        assertTrue(boardRepository.count()==1);
        map.put("writer", board.getWriter());
        assertTrue(boardRepository.delete(map)==1);
        assertTrue(boardRepository.count()==0);

        assertTrue(boardRepository.insert(board)==1);
        bno = boardRepository.selectAll().get(0).getBno();
        map.put("bno", bno+1);
        map.put("writer", board.getWriter());
        assertTrue(boardRepository.delete(map)==0);
        assertTrue(boardRepository.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        boardRepository.deleteAll();
        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);

        board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.count()==2);

        boardRepository.deleteAll();
        board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        List<Board> list = boardRepository.selectAll();
        assertTrue(list.size() == 0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);

        list = boardRepository.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(boardRepository.insert(board)==1);
        list = boardRepository.selectAll();
        assertTrue(list.size() == 2);
    }

    @Test
    public void selectTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);

        Integer bno = boardRepository.selectAll().get(0).getBno();
        board.setBno(bno);
        Board board2 = boardRepository.selectView(bno);
        log.info("asdf : " + board);
        log.info(board2);
        assertTrue(!board.equals(board2));
    }

    @Test
    public void selectPageTest() throws Exception {
        boardRepository.deleteAll();

        for (int i = 1; i <= 10; i++) {
            Board board = new Board(""+i, "no content"+i, "asdf");
            boardRepository.insert(board);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<Board> list = boardRepository.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = boardRepository.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardRepository.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void updateTest() throws Exception {
        boardRepository.deleteAll();
        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);

        Integer bno = boardRepository.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        board.setBno(bno);
        board.setTitle("yes title");
        assertTrue(boardRepository.update(board)==1);

        Board board2 = boardRepository.selectView(bno);
        System.out.println("board : " + board);
        System.out.println("board2 : " + board2);
        assertTrue(!board.equals(board2));
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        boardRepository.deleteAll();
        assertTrue(boardRepository.count()==0);

        Board board = new Board("no title", "no content", "asdf");
        assertTrue(boardRepository.insert(board)==1);
        assertTrue(boardRepository.count()==1);

        Integer bno = boardRepository.selectAll().get(0).getBno();
        assertTrue(boardRepository.increaseViewCnt(bno)==1);

        board = boardRepository.selectView(bno);
        assertTrue(board!=null);
        assertTrue(board.getView_cnt() == 1);

        assertTrue(boardRepository.increaseViewCnt(bno)==1);
        board = boardRepository.selectView(bno);
        assertTrue(board!=null);
        assertTrue(board.getView_cnt() == 2);
    }
}
