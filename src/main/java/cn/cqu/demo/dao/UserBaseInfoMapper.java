package cn.cqu.demo.dao;

import cn.cqu.demo.model.UserBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserBaseInfoMapper {
    UserBaseInfo getUserBaseInfo(@Param("uid")String uid);
}