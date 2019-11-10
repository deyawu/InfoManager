package com.example.springweb.mapper;
/*
 * @ClassName ProductMapper
 * @author: WuDeya
 * @Date: Created in 2019/11/7 23:21
 */

import com.example.springweb.dao.HelloProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from appinfo")
    @Results({
            @Result(property = "appId", column = "appId"),
            @Result(property = "appName", column = "appName"),
            @Result(property = "rangeApplication", column = "rangeApplication"),
            @Result(property = "businessLink", column = "businessLink"),
            @Result(property = "knowledgeType", column = "knowledgeType"),
            @Result(property = "testFile", column = "testFile")
    })
    List<HelloProduct> findAll();

    @Insert("insert into appinfo(appId, appName, rangeApplication, businessLink, knowledgeType," +
            " testFile) values (#{appId}, #{appName}, #{rangeApplication}, " +
            "#{businessLink}, #{knowledgeType}, #{testFile})")
    void insert(HelloProduct helloProduct);

    @Select("select * from appinfo where appId = #{appId}")
    @Results({
            @Result(property = "appId", column = "appId"),
            @Result(property = "appName", column = "appName"),
            @Result(property = "rangeApplication", column = "rangeApplication"),
            @Result(property = "businessLink", column = "businessLink"),
            @Result(property = "knowledgeType", column = "knowledgeType"),
            @Result(property = "testFile", column = "testFile")
    })
    HelloProduct getOne(String id);

    @Update("update appinfo set appName = #{appName}, rangeApplication = #{rangeApplication}," +
            "businessLink = #{businessLink}, knowledgeType = #{knowledgeType}," +
            "testFile = #{testFile} where appId = #{appId}")
    void updateById(HelloProduct helloProduct);
    // 根据产品ID进行更新信息

    @Delete("delete from appinfo where appId = #{appId}")
    void deleteById(String id);
    // 根据产品ID删除产品信息
}
