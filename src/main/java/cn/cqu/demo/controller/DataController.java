package cn.cqu.demo.controller;

import cn.cqu.demo.model.DataBean;
import cn.cqu.demo.model.SingleMsg;
import cn.cqu.demo.service.DecryptFile;
import cn.cqu.demo.service.impl.GetDataimpl;
import cn.cqu.demo.service.impl.XlsDec;
import cn.cqu.demo.service.impl.XlsxDec;
import cn.cqu.demo.util.annotation.Decrypt;
import cn.cqu.demo.util.resultbeans.Response;
import cn.cqu.demo.util.resultbeans.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class DataController {
    @Autowired
    private Map<String,DecryptFile> decryptFileMap;
    @Autowired
    private GetDataimpl getDataimpl;
    @RequestMapping(value = "/UploadFile",method = RequestMethod.POST)
    public String UploadFile(HttpServletRequest httpServletRequest) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)httpServletRequest;
        MultipartFile multipartFile;
        DecryptFile decryptFile;
        InputStream inputStream;
        String filetype;
        if(multipartHttpServletRequest.getFile("xlsfile")==null){
            //xlsx文件
            filetype="xlsxfile";
            decryptFile=decryptFileMap.get("XlsxDec");
        }else{
            //xls文件
            filetype="xlsfile";
            decryptFile=decryptFileMap.get("XlsDec");
        }
        multipartFile=multipartHttpServletRequest.getFile(filetype);
        inputStream=multipartFile.getInputStream();
        decryptFile.Decrypter(inputStream);
        return "文件上传成功";
    }
    @Decrypt
    @RequestMapping(value = "/UploadData",method = RequestMethod.POST)
    public Result<SingleMsg<String>> UploadData(@RequestBody String reqdata){
        DataBean dataBean=JSONObject.parseObject(reqdata,DataBean.class);

        return Response.CreateSuccessRsp();
    }
    @Decrypt
    @RequestMapping(value = "/GetPageData",method = RequestMethod.POST)
    public Result<ArrayList<ArrayList<String>>> GetPageData(@RequestBody String jsonObjectstr){
        JSONObject jsonObject=JSONObject.parseObject(jsonObjectstr);
        int end=0;
        if(jsonObject.getInteger("nowpage")==0){
            end=100;
        }else {
            end=jsonObject.getInteger("nowpage")*100;
        }
        return Response.CreateSuccessRsp(getDataimpl.getlimitdata(jsonObject.getInteger("nowpage"),end,jsonObject.getInteger("tablenum")));
    }
}