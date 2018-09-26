package ru.murtazali.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.murtazali.security.UserPrincipal;

@Controller
@ControllerAdvice
public class AdviceController {

    @GetMapping("/404")
    public String to404(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "error/404";
    }

    @GetMapping("/500")
    public String to500(@AuthenticationPrincipal UserPrincipal userPrincipal, ModelMap map){
        if (userPrincipal != null)
            map.addAttribute("user", userPrincipal.getUserDTO());

        return "error/500";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFoundException(NoHandlerFoundException ex){
        return "redirect:/404";
    }

    @ExceptionHandler(Exception.class)
    public String handle500(Exception ex){
        return "redirect:/500";
    }

}
