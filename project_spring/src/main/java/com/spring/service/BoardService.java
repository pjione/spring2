package com.spring.service;

import com.spring.domain.Board;
import com.spring.exception.RemoveException;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Board boardView(Integer bno) throws Exception;
    int getCount() throws Exception;
    int remove(Integer bno, String writer) throws RemoveException;
    int write(Board board) throws Exception;
    List<Board> getList() throws Exception;
    List<Board> getPage(Map map) throws Exception;
    int modify(Board board) throws Exception;

  /*  int getSearchResultCnt(SearchCondition sc) throws Exception;
    List<Board> getSearchResultPage(SearchCondition sc) throws Exception;*/
}
