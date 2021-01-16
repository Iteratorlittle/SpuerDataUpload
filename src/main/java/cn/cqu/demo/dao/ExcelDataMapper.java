package cn.cqu.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ExcelDataMapper {
    void insertlist(@Param("tableName") String tableName,List<byte[]> list);
    String gettablename(HashMap<String,byte[]> hashMap);
    void createtable(@Param("tableName") String tableName);
    void adddatalit(@Param("tableName")String tableName,@Param("size")String size,@Param("datalist")byte[] datalist);
    ArrayList<byte[]> getlimitdata(@Param("start") int start, @Param("end") int end, @Param("tablename")String tablename);
    ArrayList<String> gettablename_nopar();
}