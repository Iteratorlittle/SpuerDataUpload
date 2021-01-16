package cn.cqu.demo.service.impl;

import cn.cqu.demo.dao.ExcelDataMapper;
import cn.cqu.demo.util.SerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsertDataimpl{
    @Autowired
    private ExcelDataMapper excelDataMapper;

    public void insertforlis(String tablename,ArrayList<byte[]> arrayList) throws Exception{
        Map map=new HashMap();
        map.put("tablename",tablename);
        map.put("datalist",arrayList);
        excelDataMapper.insertlist(tablename,arrayList);
    }
    public String CreateTable(List<String> list){
        //=》开辟新线程插入头行
        String result="";
        try {
            HashMap<String,byte[]> hashMap=new HashMap<>();
            hashMap.put("arrayList",new SerializationUtil().obj2byte(list));
            result=excelDataMapper.gettablename(hashMap);
            if(result==null){
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                String date = "tname"+df.format(new Date());
                result=date;
                //创建表并添加元数据记录
                excelDataMapper.createtable(date);
                excelDataMapper.adddatalit(date,"",new SerializationUtil().obj2byte(list));
                ArrayList<byte[]> arrayList=new ArrayList<>();
                arrayList.add(new SerializationUtil().obj2byte(list));
                excelDataMapper.insertlist(result,arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}