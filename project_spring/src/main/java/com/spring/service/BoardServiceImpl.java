package com.spring.service;

import com.spring.domain.Board;
import com.spring.exception.RemoveException;
import com.spring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    @Override
    public Board boardView(Integer bno) throws Exception {
        boardRepository.increaseViewCnt(bno);
        return  boardRepository.selectView(bno);
    }
    @Override
    public int getCount() throws Exception {
        return boardRepository.count();
    }

    @Override
    public int remove(Integer bno, String writer) throws RemoveException {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return boardRepository.delete(map);
    }

    @Override
    public int write(Board Board) throws Exception {
        return boardRepository.insert(Board);
    }

    @Override
    public List<Board> getList() throws Exception {
        return boardRepository.selectAll();
    }



    @Override
    public List<Board> getPage(Map map) throws Exception {
        return boardRepository.selectPage(map);
    }

    @Override
    public int modify(Board Board) throws Exception {
        return boardRepository.update(Board);
    }

   /* @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardRepository.searchResultCnt(sc);
    }

    @Override
    public List<Board> getSearchResultPage(SearchCondition sc) throws Exception {
        return boardRepository.searchSelectPage(sc);
    }*/
}
