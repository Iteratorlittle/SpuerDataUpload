package cn.cqu.demo.service.impl;

import cn.cqu.demo.model.SingleMsg;
import cn.cqu.demo.model.UserBaseInfo;
import cn.cqu.demo.service.LoginInter;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service("TokenLogin")
public class TokenLogin implements LoginInter {
    @Override
    public SingleMsg<String> login(UserBaseInfo userBaseInfo) {
        return null;
    }
}
