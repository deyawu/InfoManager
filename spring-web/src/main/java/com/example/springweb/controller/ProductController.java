package com.example.springweb.controller;

import com.example.springweb.dao.HelloProduct;
import com.example.springweb.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/*
 * @ClassName ProductController
 * @author: WuDeya
 * @Date: Created in 2019/11/7 23:21
 */
@Controller
public class ProductController {
        @Autowired
        ProductService productService;
        public final static Logger logger = LoggerFactory.getLogger(com.example.springweb.controller.ProductController.class);

        private String currentproduct;

    // TODO: 2019/11/9   需要修改回来！！！
        public ProductController() {
            currentproduct = null;
        }

        // 产品信息提交界面
        @RequestMapping(value = "/appsubmit", method = RequestMethod.GET) // 获取用户输入数据
        public String appHello(Model model){
            return "appsubmit";
        }
        // 接收产品提交信息，存入数据库
        @RequestMapping(value = "/appsubmit", method = RequestMethod.POST) // 获取用户输入数据
        public String receiveAppinfo(HelloProduct helloProduct, Model model){
            String appId = helloProduct.getAppId();
            String appName = helloProduct.getAppName();
            String rangeApplication = helloProduct.getRangeApplication();
            String businessLink = helloProduct.getBusinessLink();
            String knowledgeType = helloProduct.getKnowledgeType();
            String testFile = helloProduct.getTestFile();

            HelloProduct temp = productService.getOne(appId);
            if(temp.getAppId() == null) {   // ID可以使用
                Map<String, String> para = new HashMap<String, String>();
                para.put("appId", appId);
                para.put("appName", appName);
                para.put("rangeApplication", rangeApplication);
                para.put("businessLink", businessLink);
                para.put("knowledgeType", knowledgeType);
                para.put("testFile", testFile);
                productService.InsertProduct(para);
                model.addAttribute("alertInfo", "APP审核信息提交成功！");

                currentproduct = new String(appId);

                return "redirect:modifyProductInfo";
            }
            else {  // ID 已经被使用了
                model.addAttribute("alertInfo", "ID 已经被使用了！");
                return "appsubmit";
            }
        }

    @RequestMapping(value = "/modifyProductInfo", method = RequestMethod.GET) // 获取产品信息
    public String getProductInfo(HelloProduct helloProduct, Model model) {

            if(currentproduct != null) {
                logger.info("show app info!!!");
                logger.info("currentproductId is :" + currentproduct);
                HelloProduct one = productService.getOne(currentproduct);
                model.addAttribute("html_appId", one.getAppId());
                model.addAttribute("html_appName", one.getAppName());
                model.addAttribute("html_rangeApplication", one.getRangeApplication());
                model.addAttribute("html_businessLink", one.getBusinessLink());
                model.addAttribute("html_knowledgeType", one.getKnowledgeType());
                model.addAttribute("html_testFile", one.getTestFile());
                return "modifyProductInfo";
            }
            model.addAttribute("welcomeInfo", "empty??");

            return "redirect:welcome";
    }

    @RequestMapping(value = "/modifyProductInfo", method = RequestMethod.POST) // 获取用户输入数据
    public String updateAppInfo(HelloProduct helloProduct, Model model) {
            assert (helloProduct.getAppId().equals(currentproduct));    // 保持APP同步
        logger.info("update APP info!!!");
        Map<String, String> para = new HashMap<String, String>();
        para.put("appId", helloProduct.getAppId());
        para.put("appName", helloProduct.getAppName());
        para.put("rangeApplication", helloProduct.getRangeApplication());
        para.put("businessLink", helloProduct.getBusinessLink());
        para.put("knowledgeType", helloProduct.getKnowledgeType());
        para.put("testFile", helloProduct.getTestFile());
        logger.info(currentproduct + "   " + helloProduct.getAppId());
        productService.UpdateByID(para);
        logger.info("before name is " + helloProduct.getAppName());
        /* test  ********** */
        HelloProduct one = productService.getOne(currentproduct);
        logger.info("after name is " + one.getAppName());


        return "redirect:modifyProductInfo";// 更新修改后的页面
    }

    @RequestMapping(value = "/deleteApp") // 获取用户输入数据
    public String deleteApp(HelloProduct helloProduct, Model model) {
            logger.info("delete app info!!!");
            if(currentproduct != null) {
                productService.DeleteByID(currentproduct);
            }
            currentproduct = null;
            return "redirect:welcome"; // 返回用户主页面
    }
}
