package cn.cqu.demo.service.impl;

import cn.cqu.demo.constant.PageDatas;
import cn.cqu.demo.service.DecryptFile;
import cn.cqu.demo.util.PoiHandler;
import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

@Service("XlsxDec")
public class XlsxDec implements DecryptFile {
    @Autowired
    private InsertDataimpl insertDataimpl;
    @Override
    public void Decrypter(InputStream inputStream){
        ExecutorService executorService=Executors.newCachedThreadPool();
        executorService.execute(new Runnable(){
            @Override
            public void run() {
                parse(inputStream);
                //读取完成进行不整页数据插入
                ExecutorService executorService1=Executors.newCachedThreadPool();
                executorService1.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(PoiHandler.pageDatas.getArrayLists().size()!=0){
                            //还剩余数据，执行插入
                            try {
                                insertDataimpl.insertforlis(PoiHandler.tablename,PoiHandler.pageDatas.getArrayLists());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }
    public void parse(InputStream inputStream) {
        OPCPackage pkg = null;
        InputStream sheetInputStream = null;
        try {
            pkg = OPCPackage.open(inputStream);
            XSSFReader xssfReader = new XSSFReader(pkg);
            StylesTable styles = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
            sheetInputStream = xssfReader.getSheetsData().next();
            processSheet(styles, strings, sheetInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (sheetInputStream != null) {
                try {
                    sheetInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            if (pkg != null) {
                try {
                    pkg.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }
    private void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream) throws SAXException, ParserConfigurationException, IOException {
        XMLReader sheetParser = SAXHelper.newXMLReader();
        if (new PoiHandler() != null) {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, new PoiHandler(), false));
        } else {
            sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, new PoiHandler(), false));
        }
        sheetParser.parse(new InputSource(sheetInputStream));
    }
}