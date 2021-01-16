package cn.cqu.demo.controller;

import cn.cqu.demo.model.SingleMsg;
import cn.cqu.demo.model.UserBaseInfo;
import cn.cqu.demo.service.LoginInter;
import cn.cqu.demo.service.impl.CreateKey;
import cn.cqu.demo.util.annotation.Decrypt;
import cn.cqu.demo.util.resultbeans.Response;
import cn.cqu.demo.util.resultbeans.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private Map<String, LoginInter> loginInterMap;

    @Autowired
    private CreateKey createKey;

    @Decrypt
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public Result<SingleMsg<String>> login(@RequestBody String userBaseInfostr){
        LoginInter loginInter;
        UserBaseInfo userBaseInfo=JSONObject.parseObject(userBaseInfostr,UserBaseInfo.class);
        if(userBaseInfo.getTokens().equals("")){
            loginInter=loginInterMap.get("PointLogin");
        }else{
            loginInter=loginInterMap.get("TokenLogin");
        }
        return Response.CreateSuccessRsp(loginInter.login(userBaseInfo));
    }
    @RequestMapping(path = "/GetRsa",method = RequestMethod.POST)
    public Result<JSONObject> GetRsa(HttpServletRequest httpServletRequest){
        return Response.CreateSuccessRsp(createKey.CreateKey(httpServletRequest));
    }
}