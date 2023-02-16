package org.sq.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.sq.model.domain.Train;
import org.sq.service.ITrainService;
import org.sq.service.IUserService;
import org.sq.service.impl.UsersServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    private ITrainService trainServiceImpl;
    @Autowired
    private IUserService userServiceImpl;
    //登录时的页面
    @RequestMapping(value = "/login")
    public String LoginPage() {
        return "login";
    }

    //搜索页面
    @RequestMapping(value = "/search")
    public String SearchPage(HttpServletRequest request){
        List<Train> trainList = trainServiceImpl.getFindList();
        request.setAttribute("train",trainList);
        return "search";
    }

    //搜索结果展示页面
    @RequestMapping(value = "/displayPage")
    public String DisplayPage(HttpServletRequest request, @RequestParam("startSelect") String start,
                             @RequestParam("endSelect") String end, @RequestParam("dateSelect") String date) {
        List<Train> train = trainServiceImpl.selectTrainWithTrainMessage(start, end, date);
        if (!(train.isEmpty())){
            request.setAttribute("trainMessage", train);
            return "display";
        }else {
            request.setAttribute("msg","没有该班次,返回重新搜索");
            return "display";
        }
    }

    //修改密码页面
    @RequestMapping("/confirmManage")
    public String ConfirmManagePage() {
        return "forget";
    }

    //修改密码成功跳转后页面
    @RequestMapping("/successManage")
    public String SuccessManagePage(HttpServletRequest request,
                                    @RequestParam("userName") String userName
    ,@RequestParam("password") String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        System.out.println(encodePassword);
        int i = userServiceImpl.updatePassword(encodePassword, userName);
        request.setAttribute("success",i);
        return "manage";
    }
}
