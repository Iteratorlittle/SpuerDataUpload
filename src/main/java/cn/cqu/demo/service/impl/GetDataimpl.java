package cn.cqu.demo.service.impl;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import cn.cqu.demo.constant.ExcelConstatnt;
import cn.cqu.demo.dao.ExcelDataMapper;
import cn.cqu.demo.util.SerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class GetDataimpl {
    @Autowired
    private ExcelDataMapper excelDataMapper;
    public ArrayList<ArrayList<String>> getlimitdata(int start,int end,int tablenum){
        ArrayList<ArrayList<String>> reqlist=new ArrayList<>();
        ArrayList<byte[]> arrayList=excelDataMapper.getlimitdata(start,end,tablename(tablenum));
        for(int index=0;index<arrayList.size();index++){
            try {
                reqlist.add((ArrayList<String>)new SerializationUtil().byte2obj(arrayList.get(index)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reqlist;
    }
    public String tablename(int tablenum){
        String tablename="";
        if(ExcelConstatnt.tablename.isEmpty()){
            //tablename为空，从数据库取
            ArrayList<String> arrayList=excelDataMapper.gettablename_nopar();
            for(int index=0;index<arrayList.size();index++){
                ExcelConstatnt.tablename.put(index,arrayList.get(index));
            }
        }
        tablename=ExcelConstatnt.tablename.get(tablenum);
        return tablename;
    }
}