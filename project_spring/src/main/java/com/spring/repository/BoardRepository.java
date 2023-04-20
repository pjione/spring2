package com.spring.repository;

import com.spring.domain.Board;
import com.spring.exception.RemoveException;

import java.util.List;
import java.util.Map;

public interface BoardRepository {
    Board selectView(Integer bno) throws Exception;
    int delete(Map map) throws RemoveException;
    int deleteForAdmin(Integer bno) throws Exception;
    int insert(Board dto) throws Exception;
    int update(Board dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    List<Board> selectPage(Map map) throws Exception;
    List<Board> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;

  /*  int searchResultCnt(SearchCondition sc) throws Exception;
    List<Board> searchSelectPage(SearchCondition sc) throws Exception;*/
}
