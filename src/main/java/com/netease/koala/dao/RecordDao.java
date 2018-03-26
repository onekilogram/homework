package com.netease.koala.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.koala.model.Record;

public interface RecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
    
    /********************以上是自动生成的代码，以下是业务逻辑所需要*****************************/
    
    List<Record> selectAllRecord(Integer userId);

    List<Record> selectOneRecord(@Param("userid") Integer userId,@Param("itemid") Integer itemId);
    
}