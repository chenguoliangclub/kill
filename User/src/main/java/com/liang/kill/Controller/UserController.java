package com.liang.kill.Controller;

import com.liang.kill.pojo.User;
import com.liang.kill.service.UserService;
import com.liang.kill.tools.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /*
    判断用户登录状态
     */
    @RequestMapping("/auth")
    public ResponseResult<User> auth(HttpServletRequest request){
        return userService.auth(request);
    }
    /*
    根据用户名查询用户
    */
    @RequestMapping("/find")
    public ResponseResult<User> find(@RequestParam("username") String username){
        return userService.queryUserByName(username);
    }
    /*
    添加用户
    */
    @RequestMapping("/add")
    public ResponseResult<User> add(
            @RequestParam("username") String username,
            @RequestParam("password") String password)
    {
        return userService.insertUser(username,password);
    }
    /*
    用户登录
    */
    @RequestMapping("/login")
    public ResponseResult<User> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request)
    {
        return userService.login(username,password,request);
    }
}
