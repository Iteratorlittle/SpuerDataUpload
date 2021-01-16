package cn.cqu.demo.service.impl;

import cn.cqu.demo.constant.ExcelConstatnt;
import cn.cqu.demo.constant.PageDatas;
import cn.cqu.demo.service.DecryptFile;
import cn.cqu.demo.util.SerializationUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service("XlsDec")
public class XlsDec implements DecryptFile {
    @Autowired
    private InsertDataimpl insertDataimpl;
    private static int pagenum=0;
    private String tablename;
    @Override
    public void Decrypter(InputStream InputStream) throws IOException {
        Workbook hssfWorkbook= HSSFWorkbookFactory.create(InputStream);
        for(int index=0;index<hssfWorkbook.getNumberOfSheets();index++){
            Sheet sheet=hssfWorkbook.getSheetAt(index);
            if(sheet.getLastRowNum()==-1){}else{
                pagenum=sheet.getLastRowNum()/ExcelConstatnt.INSERT_COUNT;
                if(sheet.getLastRowNum()%ExcelConstatnt.INSERT_COUNT>0){
                    pagenum+=1;
                }
                DecExcel(sheet);
            }
        }
    }
    public void DecExcel(Sheet sheet){
            for(int page_index=0;page_index<pagenum;page_index++){
                final int nowpage=page_index;
                PageDatas pageDatas=new PageDatas();
                Executor executor=Executors.newCachedThreadPool();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> rowarrayList;
                        int row_index_chil=nowpage*ExcelConstatnt.INSERT_COUNT;
                        for(int row_index=nowpage*ExcelConstatnt.INSERT_COUNT;row_index<row_index_chil+ExcelConstatnt.INSERT_COUNT;row_index++){
                            rowarrayList=new ArrayList<>();
                            if(sheet.getRow(row_index)==null){
                                break;
                            }
                            Row row=sheet.getRow(row_index);
                            for(int cell_index=0;cell_index<row.getLastCellNum();cell_index++){
                                Cell cell=row.getCell(cell_index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                                if(row.getCell(cell_index)!=null){
                                    cell.setCellType(CellType.STRING);
                                    rowarrayList.add(cell.getStringCellValue());
                                }else{
                                    rowarrayList.add("");
                                }
                            }
                            if(row_index==row.getFirstCellNum()){
                                //行头行，判断是否有此表，有则插入对应表，无则创建新表插入
                                tablename=insertDataimpl.CreateTable(rowarrayList);
                            }
                            try {
                                pageDatas.addtoArrayLists(new SerializationUtil().obj2byte(rowarrayList));
                            }catch (Exception e){}
                        }
                        //分页后插入数据
                        try {
                            while (tablename==null){}
                            //等待tablename查询结果
                            System.out.println(tablename);
                            insertDataimpl.insertforlis(tablename,pageDatas.getArrayLists());
                        }catch (Exception e){}
                    }
                });
            }
    }
}