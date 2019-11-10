package com.example.springweb.dao;
/*
 * @ClassName HelloProduct
 * @author: WuDeya
 * @Date: Created in 2019/11/7 23:19
 */

import java.io.Serializable;

public class HelloProduct implements Serializable {
    private String appId;
    private String appName;
    private String rangeApplication;
    private String businessLink;
    private String knowledgeType;
    private String testFile;

    public HelloProduct() {
        appId = null;
        appName = null;
        rangeApplication = null;
        businessLink = null;
        knowledgeType = null;
        testFile = null;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRangeApplication() {
        return rangeApplication;
    }

    public void setRangeApplication(String rangeApplication) {
        this.rangeApplication = rangeApplication;
    }

    public String getBusinessLink() {
        return businessLink;
    }

    public void setBusinessLink(String businessLink) {
        this.businessLink = businessLink;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getTestFile() {
        return testFile;
    }

    public void setTestFile(String testFile) {
        this.testFile = testFile;
    }

}
