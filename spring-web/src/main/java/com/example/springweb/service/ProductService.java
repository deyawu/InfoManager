package com.example.springweb.service;
/*
 * @ClassName ProductService
 * @author: WuDeya
 * @Date: Created in 2019/11/7 23:21
 */

import com.example.springweb.dao.HelloProduct;
import com.example.springweb.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;
    public List<HelloProduct> getProductList() {
        List<HelloProduct> list = productMapper.findAll();
        return list;
    }

    // 插入产品（由页面获取数据）
    public void InsertProduct(Map<String, String> params) {
        ObjectMapper objectMapper = new ObjectMapper();
        HelloProduct helloProduct = objectMapper.convertValue(params, HelloProduct.class);
        productMapper.insert(helloProduct);
    }

    // 由产品ID从数据库获取product具体信息
    public HelloProduct getOne(String id) {
        HelloProduct result = productMapper.getOne(id);
        System.out.println("get Product info: " + result);
        if(result == null) {
            result = new HelloProduct(); // 索引为空时，返回新建
        }
        return result;
    }

    // 由产品ID确定更新哪个product
    public void UpdateByID(Map<String, String> params) {
        String id = params.get("appId");
        HelloProduct temp = productMapper.getOne(id);
        if(params.get("appName") != null) {
            temp.setAppName(params.get("appName"));
        }
        if(params.get("rangeApplication") != null) {
            temp.setRangeApplication(params.get("rangeApplication"));
        }
        if(params.get("businessLink") != null) {
            temp.setBusinessLink(params.get("businessLink"));
        }
        if(params.get("knowledgeType") != null) {
            temp.setKnowledgeType(params.get("knowledgeType"));
        }
        if(params.get("testFile") != null) {
            temp.setTestFile(params.get("testFile"));
        }
        productMapper.updateById(temp);
    }

    // 根据appID删除审核信息
    public void DeleteByID(String id) {
        productMapper.deleteById(id);
    }
}
