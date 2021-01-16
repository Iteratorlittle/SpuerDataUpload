package cn.cqu.demo.service.impl;

import cn.cqu.demo.dao.UserBaseInfoMapper;
import cn.cqu.demo.model.SingleMsg;
import cn.cqu.demo.model.UserBaseInfo;
import cn.cqu.demo.service.LoginInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PointLogin")
public class PointLogin implements LoginInter {
    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;
    @Override
    public SingleMsg<String> login(UserBaseInfo userBaseInfo) {
        UserBaseInfo db_userinfo=userBaseInfoMapper.getUserBaseInfo(userBaseInfo.getUid());
        SingleMsg singleMsg=new SingleMsg();
        singleMsg.setMsg("0");
        if(db_userinfo.getPasswords().equals(userBaseInfo.getPasswords())){
            singleMsg.setMsg("1");
            singleMsg.setMsg2(db_userinfo.getTokens());
        }
        return singleMsg;
    }
}
