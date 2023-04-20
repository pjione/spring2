package com.spring.controller;

import com.spring.domain.Board;
import com.spring.domain.PageHandler;
import com.spring.exception.RemoveException;
import com.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("mode", "new");
        return "board/board";
    }
    @PostMapping("/write")
    public String write(Board board, HttpSession session, Model model, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        board.setWriter(writer);
        try {
            if(boardService.write(board)!=1){
                throw new Exception("Write failed.");
            }
            rattr.addFlashAttribute("msg", "write ok");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mode","new");
            model.addAttribute("board", board);
            model.addAttribute("msg", "write error");
            return "board/board";
        }

    }
    @PostMapping("/remove")
    public String remove(int bno, int page, int pageSize, Model model, HttpSession session, RedirectAttributes rattr) throws RemoveException {
        String writer = (String) session.getAttribute("id");
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        int rowCnt = boardService.remove(bno, writer);
        if(rowCnt!=1){
            throw new RemoveException("delete error");
        }
        rattr.addFlashAttribute("msg", "delete ok");

        return "redirect:/board/list";
    }
    @GetMapping("/read")
    public String read(@RequestParam int bno, int page, int pageSize, Model model){
        try {
            Board board = boardService.boardView(bno);
            model.addAttribute(board); //name은 타입의 소문자로 자동 지정가능
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "board/board";
    }
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpSession session, HttpServletRequest request, Model model){
        if(session.getAttribute("id")==null){
            return "redirect:/login?toURL=" +  request.getRequestURI();
        }
        Map map = new HashMap();
        map.put("offset", (page-1) * pageSize);
        map.put("pageSize", pageSize);

        try {
            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            List<Board> list = boardService.getPage(map);
            model.addAttribute("list",list);
            model.addAttribute("ph", pageHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "board/list";
    }


}
