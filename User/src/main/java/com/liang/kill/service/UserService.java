package com.liang.kill.service;

import com.liang.kill.mapper.UserMapper;
import com.liang.kill.pojo.User;
import com.liang.kill.tools.ResponseResult;
import com.liang.kill.utils.CodecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ResponseResult<User> auth(HttpServletRequest request) {
        ResponseResult<User> result = new ResponseResult<User>();
        if (request.getSession().getAttribute("user") == null){
            result.setCode(-1);
            result.setData(null);
            result.setMsg("用户未登录!");
        }else{
            result.setCode(0);
            result.setData((User) request.getSession().getAttribute("user"));
            result.setMsg("校验成功!");
        }
        return result;
    }

    public ResponseResult<User> queryUserByName(String username) {
        ResponseResult<User> result = new ResponseResult<User>();
        User user = new User();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        if (user == null){
            result.setCode(-1);
            result.setData(null);
            result.setMsg("用户不存在!");
        }else{
            result.setCode(0);
            result.setData(user);
            result.setMsg("用户已存在!");
        }
        return result;
    }

    public ResponseResult<User> insertUser(String username, String password) {
        ResponseResult<User> result = new ResponseResult<User>();
        User user = new User();
        if (queryUserByName(username) != null){
            result.setCode(-1);
            result.setData(null);
            result.setMsg("添加失败，用户已存在!");
        }else{
            //生成盐
            String salt = CodecUtils.generateSalt();
            user.setSalt(salt);
            user.setCreated(new Date().toString());
            user.setUsername(username);
            //加密密码
            user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
            //保存到数据库
            userMapper.insert(user);
            //设置返回结果
            result.setCode(0);
            result.setData(user);
            result.setMsg("添加成功!");
        }
        return result;
    }

    public ResponseResult<User> login(String username, String password,HttpServletRequest request) {
        ResponseResult<User> result = new ResponseResult<User>();
        User user = queryUserByName(username).getData();
        if (user == null){
            result.setData(null);
            result.setCode(-1);
            result.setMsg("登录失败，用户不存在！");
        }else{
            //校验密码
            if (!user.getPassword().equals(CodecUtils.md5Hex(password,user.getSalt()))) {
                result.setData(null);
                result.setCode(-1);
                result.setMsg("登录失败，密码错误！");
            }else{
                result.setData(user);
                result.setCode(0);
                result.setMsg("登录成功！");
                request.getSession().setAttribute("user",user);
            }
        }
        return result;
    }
}
