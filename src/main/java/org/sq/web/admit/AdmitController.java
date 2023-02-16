package org.sq.web.admit;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sq.model.domain.Train;
import org.sq.service.ITrainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admit")
public class AdmitController {
    @Autowired
    private ITrainService trainServiceImpl;

    //添加成功跳转页面
    @RequestMapping("/insertManage")
    public String InsertManagePage(HttpServletRequest request,
                                   @RequestParam("train_number") String trainNumber, @RequestParam("start") String start,
                                   @RequestParam("end") String end, @RequestParam("date") String date,
                                   @RequestParam("time") String time, @RequestParam("prize") int prize) {
        int iAdd = trainServiceImpl.insertTrain(trainNumber, start, end, date, time, prize);
        request.setAttribute("add", iAdd);
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("trainMessageList", messageList);
        return "manage";
    }

    //更新成功跳转页面
    @RequestMapping("/updateManage")
    public String UpdateManagePage(HttpServletRequest request, @RequestParam("train_number") String trainNumber,
                                   @RequestParam("start") String start, @RequestParam("end") String end,
                                   @RequestParam("date") String date, @RequestParam("time") String time,
                                   @RequestParam("prize") int prize) {
        int iUpdate = trainServiceImpl.updateTrain(trainNumber, start, end, date, time, prize);
        request.setAttribute("update", iUpdate);
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("trainMessageList", messageList);
        return "manage";
    }


    //删除成功跳转页面
    @RequestMapping("/deleteManage")
    public String DeleteManagePage(HttpServletRequest request, @RequestParam("train_number") String trainNumber) {
        int iDelete = trainServiceImpl.deleteTrain(trainNumber);
        request.setAttribute("delete", iDelete);
        System.out.println(iDelete);
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("trainMessageList", messageList);
        return "manage";
    }



    //添加页面
    @RequestMapping("/addPage")
    public String addTrain() {
        return "add";
    }

    //删除页面
    @RequestMapping("/deletePage")
    public String deleteTrain(HttpServletRequest request) {
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("trainMessageList", messageList);
        return "delete";
    }

    //更新页面
    @RequestMapping("/updatePage")
    public String updateTrain(HttpServletRequest request) {
        List<Train> messageList = trainServiceImpl.getTrainMessageList();
        request.setAttribute("trainMessageList", messageList);
        return "update";
    }

}
