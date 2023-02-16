package org.sq.web.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.sq.model.domain.Train;
import org.sq.service.ITrainService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ITrainService trainServiceImpl;


    //登录时的首页
    @RequestMapping("/")
    private String NoLoginIndexPage(HttpServletRequest request){
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("messageList",messageList);
        return "index";
    }

    //登录后的首页

    @RequestMapping("/indexPage")
    public String getUser(HttpServletRequest request){
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        request.setAttribute("messageList",messageList);
        request.setAttribute("userName",principal.getUsername());
        System.out.println(principal.getUsername());
        return "index";
    }
}
