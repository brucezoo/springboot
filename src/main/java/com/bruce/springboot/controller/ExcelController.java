package com.bruce.springboot.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.bruce.springboot.model.dto.excel.AlipayCity;
import com.chinacareer.ymtd.redis.IRedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Resource
    private IRedisCache iRedisCache;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            // ReadListener不是必须的，它主要的设计是读取excel数据的后置处理(并考虑一次性读取到内存潜在的内存泄漏问题)
            EasyExcelFactory.read(file.getInputStream(), AlipayCity.class, new ReadListener<AlipayCity>() {
                @Override
                public void invoke(AlipayCity city, AnalysisContext analysisContext) {
                    String cityName = city.getName();
                    if (cityName.contains("市")) {
                        cityName = cityName.substring(0, cityName.length() - 1);
                    }
                    iRedisCache.hSet("sktd2023:city:code", cityName, city.getCode().toString());
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                }
            }).sheet().doRead();
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
