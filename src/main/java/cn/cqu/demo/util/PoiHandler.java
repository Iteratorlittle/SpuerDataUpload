package cn.cqu.demo.util;

import cn.cqu.demo.constant.ExcelConstatnt;
import cn.cqu.demo.constant.PageDatas;
import cn.cqu.demo.service.impl.InsertDataimpl;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PoiHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    @Autowired
    private InsertDataimpl insertDataimpl;
    private ArrayList<String> list;
    public static String tablename;
    public static PageDatas pageDatas=new PageDatas();

    @Override
    public void startRow(int i) {
        list=new ArrayList<>();
    }
    @Override
    public void cell(String s, String s1, XSSFComment xssfComment) {
        list.add(s1);
    }
    @Override
    public void endRow(int i) {
        if(i==1){
            //行头行，判断是否有此表，有则插入对应表，无则创建新表插入
            tablename=insertDataimpl.CreateTable(list);
        }
        try {
            pageDatas.addtoArrayLists(new SerializationUtil().obj2byte(list));
            if(pageDatas.getArrayLists().size()== ExcelConstatnt.INSERT_COUNT){
                ExecutorService executorService1= Executors.newCachedThreadPool();
                executorService1.execute(new Runnable() {
                    @Override
                    public void run() {
                        //缓存的List达到ExcelConstatnt.INSERT_COUNT==5000时触发异步数据插入,并清空List以节约内存
                        try {
                            insertDataimpl.insertforlis(PoiHandler.tablename,PoiHandler.pageDatas.getArrayLists());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
