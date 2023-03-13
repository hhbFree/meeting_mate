package com.meeting.mate_find.controller;

import com.alibaba.fastjson.JSONObject;
import com.meeting.mate_find.utils.TokenUtil;
import com.meeting.mate_find.vo.Result;
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
public class UserController {

    @PostMapping("toLogin")
    public Object toLogin(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        if(user!=null){
            String token = TokenUtil.sign(user);
            jsonObject.put("token",token);
            jsonObject.put("staff",user);
            jsonObject.put("msg","登录成功");
            jsonObject.put("code",200);
        }else {
            jsonObject.put("msg","账号或密码错误");
            jsonObject.put("code",500);
        }
        return jsonObject;
    }

    @PostMapping("register")
    public Result register(@RequestBody User user){
        Result result=new Result();

        return result;
    }
}
