package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundCatcher() {
        return "error";
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commonCatcher(Exception e, Model model) {
        /*model.addAttribute("e", e);*/
        return "error";
    }
    @ExceptionHandler(RemoveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String removeCatcher(Exception e, RedirectAttributes rattr){
        //rattr.addFlashAttribute("msg",  e);
        return "error";
    }
}
