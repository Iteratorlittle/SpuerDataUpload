package cn.cqu.demo.service.impl;

import cn.cqu.demo.util.RSAUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

@Service
public class CreateKey {
    public JSONObject CreateKey(HttpServletRequest httpServletRequest){
        String publicKeyExponent="";
        String publicKeyModulus="";
        try {
            HashMap<String, Object> map = RSAUtils.getKeys();
            RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
            httpServletRequest.getSession().setAttribute("privatekey", privateKey);
            publicKeyExponent = publicKey.getPublicExponent().toString(16);
            publicKeyModulus = publicKey.getModulus().toString(16);
        } catch (Exception e) {
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("publicexpen",publicKeyExponent);
        jsonObject.put("publicmodel",publicKeyModulus);
        return jsonObject;
    }
}
