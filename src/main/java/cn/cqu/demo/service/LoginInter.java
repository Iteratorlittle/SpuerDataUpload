package cn.cqu.demo.service;

import cn.cqu.demo.model.SingleMsg;
import cn.cqu.demo.model.UserBaseInfo;
import com.alibaba.fastjson.JSONObject;

public interface LoginInter {
    SingleMsg<String> login(UserBaseInfo userBaseInfo);
}