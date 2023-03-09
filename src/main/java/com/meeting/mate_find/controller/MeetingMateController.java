package com.meeting.mate_find.controller;

import com.meeting.mate_find.vo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: meeting mate bus
 * @Author: hbHao
 * @Date: 2023/3/9 13:36
 */
@RestController
@RequestMapping("api")
public class MeetingMateController {

    @PostMapping("Blog/login")
    public String test(@RequestBody User user){
        System.out.println(user.getPassword()+":"+user.getUserName());
        return "ok";
    }


}
